/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.transform.ebxml;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAssociation;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLClassification;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLExtrinsicObject;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLNonconstructiveDocumentSetRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLObjectLibrary;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLProvideAndRegisterDocumentSetRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLQueryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryError;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryPackage;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRegistryResponse;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRemoveMetadataRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRetrieveDocumentSetResponse;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLRetrieveImagingDocumentSetRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLSubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAdhocQueryRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLAssociation30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLClassification30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLExtrinsicObject30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLNonconstructiveDocumentSetRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLProvideAndRegisterDocumentSetRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLQueryResponse30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRegistryError30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRegistryPackage30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRegistryResponse30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRemoveMetadataRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRetrieveDocumentSetResponse30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLRetrieveImagingDocumentSetRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.EbXMLSubmitObjectsRequest30;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.ProvideAndRegisterDocumentSetRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RemoveDocumentsRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.SubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.xdsi.RetrieveImagingDocumentSetRequestType;

public class GatefireEbXmlFactory implements EbXMLFactory {
	/**
	 * The factory to create objects of the query namespace.
	 */
	public final static org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.ObjectFactory QUERY_FACTORY = new org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.query.ObjectFactory();

	/**
	 * The factory to create objects of the rim namespace.
	 */
	public final static org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectFactory RIM_FACTORY = new org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rim.ObjectFactory();

	/**
	 * The factory to create objects of the rs namespace.
	 */
	public final static org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.ObjectFactory RS_FACTORY = new org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.ObjectFactory();

	/**
	 * The factory to create objects of the lcm namespace.
	 */
	public final static org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.ObjectFactory LCM_FACTORY = new org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.ObjectFactory();

	@Override
	public EbXMLClassification createClassification(EbXMLObjectLibrary objectLibrary) {
		return new EbXMLClassification30(RIM_FACTORY.createClassificationType());
	}

	@Override
	public EbXMLExtrinsicObject createExtrinsic(String id, EbXMLObjectLibrary objectLibrary) {
		var extrinsicObjectType = RIM_FACTORY.createExtrinsicObjectType();
		extrinsicObjectType.setId(id);
		objectLibrary.put(id, extrinsicObjectType);
		return new EbXMLExtrinsicObject30(extrinsicObjectType, objectLibrary);
	}

	@Override
	public EbXMLRegistryPackage createRegistryPackage(String id, EbXMLObjectLibrary objectLibrary) {
		var registryPackageType = RIM_FACTORY.createRegistryPackageType();
		registryPackageType.setId(id);
		objectLibrary.put(id, registryPackageType);
		return new EbXMLRegistryPackage30(registryPackageType, objectLibrary);
	}

	@Override
	public EbXMLAssociation createAssociation(String id, EbXMLObjectLibrary objectLibrary) {
		var association = RIM_FACTORY.createAssociationType1();
		association.setId(id);
		objectLibrary.put(id, association);
		return new EbXMLAssociation30(association, objectLibrary);
	}

	@Override
	public EbXMLSubmitObjectsRequest createSubmitObjectsRequest() {
		var request = LCM_FACTORY.createSubmitObjectsRequest();
		request.setRegistryObjectList(RIM_FACTORY.createRegistryObjectListType());
		//request.setRequestSlotList(RIM_FACTORY.createSlotListType());
		return new EbXMLSubmitObjectsRequest30(request, createObjectLibrary());
	}

	@Override
	public EbXMLProvideAndRegisterDocumentSetRequest createProvideAndRegisterDocumentSetRequest(
			EbXMLObjectLibrary objectLibrary) {
		var request = new ProvideAndRegisterDocumentSetRequestType();
		request.setSubmitObjectsRequest((SubmitObjectsRequest) createSubmitObjectsRequest().getInternal());
		return new EbXMLProvideAndRegisterDocumentSetRequest30(request, objectLibrary);
	}

	@Override
	public EbXMLNonconstructiveDocumentSetRequest createRetrieveDocumentSetRequest() {
		return new EbXMLNonconstructiveDocumentSetRequest30<>(new RetrieveDocumentSetRequestType());
	}

	@Override
	public EbXMLNonconstructiveDocumentSetRequest createRemoveDocumentsRequest() {
		return new EbXMLNonconstructiveDocumentSetRequest30<>(new RemoveDocumentsRequestType());
	}

	@Override
	public EbXMLRetrieveImagingDocumentSetRequest createRetrieveImagingDocumentSetRequest() {
		return new EbXMLRetrieveImagingDocumentSetRequest30(new RetrieveImagingDocumentSetRequestType());
	}

	@Override
	public EbXMLObjectLibrary createObjectLibrary() {
		return new EbXMLObjectLibrary();
	}

	@Override
	public EbXMLRegistryResponse createRegistryResponse() {
		return new EbXMLRegistryResponse30(RS_FACTORY.createRegistryResponseType());
	}

	@Override
	public EbXMLRetrieveDocumentSetResponse createRetrieveDocumentSetResponse() {
		var response = new RetrieveDocumentSetResponseType();
		response.setRegistryResponse(RS_FACTORY.createRegistryResponseType());
		return new EbXMLRetrieveDocumentSetResponse30(response);
	}

	@Override
	public EbXMLAdhocQueryRequest createAdhocQueryRequest() {
		var request = QUERY_FACTORY.createAdhocQueryRequest();

		var responseOption = QUERY_FACTORY.createResponseOptionType();
		responseOption.setReturnComposedObjects(true);
		request.setResponseOption(responseOption);

		var query = RIM_FACTORY.createAdhocQueryType();
		request.setAdhocQuery(query);

		return new EbXMLAdhocQueryRequest30(request);
	}

	@Override
	public EbXMLQueryResponse createAdhocQueryResponse(EbXMLObjectLibrary objectLibrary, boolean returnsObjectRefs) {
		var response = QUERY_FACTORY.createAdhocQueryResponse();
		response.setRegistryObjectList(RIM_FACTORY.createRegistryObjectListType());
		return new EbXMLQueryResponse30(response, objectLibrary);
	}

	@Override
	public EbXMLRegistryError createRegistryError() {
		var registryError = RS_FACTORY.createRegistryError();
		return new EbXMLRegistryError30(registryError);
	}

	@Override
	public EbXMLRemoveMetadataRequest createRemoveMetadataRequest() {
		var removeObjectsRequest = LCM_FACTORY.createRemoveObjectsRequest();
		return new EbXMLRemoveMetadataRequest30(removeObjectsRequest);
	}
}
