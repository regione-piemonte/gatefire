/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.transform.ebxml;

import static org.apache.commons.lang3.Validate.notNull;

import javax.activation.DataHandler;

/*
 * Copyright 2009 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLObjectLibrary;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLQueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntryType;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary;
import org.openehealth.ipf.commons.ihe.xds.core.responses.QueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.AssociationTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.FolderTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.SubmissionSetTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.responses.ErrorInfoListTransformer;

/**
 * Transforms between {@link QueryResponse} and the {@link EbXMLQueryResponse}
 * representation.
 *
 * @author Jens Riemschneider
 */
public class QueryResponseTransformer {
	private final EbXMLFactory factory;
	private final SubmissionSetTransformer submissionSetTransformer;
	private final it.csi.gatefire.repository.xds.transform.ebxml.DocumentEntryTransformer documentEntryTransformer;
	private final FolderTransformer folderTransformer;
	private final AssociationTransformer associationTransformer;
	private final ErrorInfoListTransformer errorInfoListTransformer;

	/**
	 * Constructs the transformer.
	 *
	 * @param factory the factory for ebXML objects.
	 */
	public QueryResponseTransformer(EbXMLFactory factory) {
		notNull(factory, "factory cannot be null");
		this.factory = factory;

		submissionSetTransformer = new SubmissionSetTransformer(factory);
		documentEntryTransformer = new it.csi.gatefire.repository.xds.transform.ebxml.DocumentEntryTransformer(factory);
		folderTransformer = new FolderTransformer(factory);
		associationTransformer = new AssociationTransformer(factory);
		errorInfoListTransformer = new ErrorInfoListTransformer(factory);
	}

	/**
	 * Transforms a {@link QueryResponse} to a {@link EbXMLQueryResponse}.
	 *
	 * @param response the response. Can be <code>null</code>.
	 * @return the ebXML representation. <code>null</code> if the input was
	 *         <code>null</code>.
	 */
	public EbXMLQueryResponse toEbXML(QueryResponse response) {
		if (response == null) {
			return null;
		}

		var library = factory.createObjectLibrary();
		var ebXML = factory.createAdhocQueryResponse(library, !response.getReferences().isEmpty());
		ebXML.setStatus(response.getStatus());

		if (!response.getErrors().isEmpty()) {
			ebXML.setErrors(errorInfoListTransformer.toEbXML(response.getErrors()));
		}

		for (var docEntry : response.getDocumentEntries()) {
			var extrinsic = documentEntryTransformer.toEbXML(docEntry, library);
			for (var document : response.getDocuments()) {
				if ((document != null) && (document.getDocumentEntry() == docEntry)) {
					extrinsic.setDataHandler(document.getContent(DataHandler.class));
					break;
				}
			}
			ebXML.addExtrinsicObject(extrinsic);
		}

		for (var folder : response.getFolders()) {
			ebXML.addRegistryPackage(folderTransformer.toEbXML(folder, library));
			addClassification(ebXML, folder.getEntryUuid(), Vocabulary.FOLDER_CLASS_NODE, library);
		}

		for (var set : response.getSubmissionSets()) {
			ebXML.addRegistryPackage(submissionSetTransformer.toEbXML(set, library));
			addClassification(ebXML, set.getEntryUuid(), Vocabulary.SUBMISSION_SET_CLASS_NODE, library);
		}

		for (var association : response.getAssociations()) {
			ebXML.addAssociation(associationTransformer.toEbXML(association, library));
		}

		response.getReferences().forEach(ebXML::addReference);

		return ebXML;
	}

	/**
	 * Transforms a {@link EbXMLQueryResponse} to a {@link QueryResponse}.
	 *
	 * @param ebXML the ebXML representation. Can be <code>null</code>.
	 * @return the response. <code>null</code> if the input was <code>null</code>.
	 */
	public QueryResponse fromEbXML(EbXMLQueryResponse ebXML) {
		if (ebXML == null) {
			return null;
		}

		var response = new QueryResponse();
		response.setStatus(ebXML.getStatus());

		if (!ebXML.getErrors().isEmpty()) {
			response.setErrors(errorInfoListTransformer.fromEbXML(ebXML.getErrors()));
		}

		var foundNonObjRefs = false;

		for (var extrinsic : ebXML.getExtrinsicObjects(DocumentEntryType.STABLE_OR_ON_DEMAND)) {
			var documentEntry = documentEntryTransformer.fromEbXML(extrinsic);
			response.getDocumentEntries().add(documentEntry);
			if (extrinsic.getDataHandler() != null) {
				response.getDocuments().add(new Document(documentEntry, extrinsic.getDataHandler()));
			}
			foundNonObjRefs = true;
		}

		for (var regPackage : ebXML.getRegistryPackages(Vocabulary.FOLDER_CLASS_NODE)) {
			response.getFolders().add(folderTransformer.fromEbXML(regPackage));
			foundNonObjRefs = true;
		}

		for (var regPackage : ebXML.getRegistryPackages(Vocabulary.SUBMISSION_SET_CLASS_NODE)) {
			response.getSubmissionSets().add(submissionSetTransformer.fromEbXML(regPackage));
			foundNonObjRefs = true;
		}

		for (var association : ebXML.getAssociations()) {
			response.getAssociations().add(associationTransformer.fromEbXML(association));
			foundNonObjRefs = true;
		}

		if (!foundNonObjRefs) {
			var standardLibrary = factory.createObjectLibrary();
			ebXML.getReferences().stream().filter(ref -> standardLibrary.getById(ref.getId()) == null)
					.forEach(ref -> response.getReferences().add(ref));
		}

		return response;
	}

	private void addClassification(EbXMLQueryResponse ebXML, String classified, String node,
			EbXMLObjectLibrary library) {
		var classification = factory.createClassification(library);
		classification.setClassifiedObject(classified);
		classification.setClassificationNode(node);
		classification.assignUniqueId();
		ebXML.addClassification(classification);
	}
}
