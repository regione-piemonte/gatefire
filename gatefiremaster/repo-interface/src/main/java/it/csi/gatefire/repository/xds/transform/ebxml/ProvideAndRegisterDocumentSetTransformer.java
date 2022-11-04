package it.csi.gatefire.repository.xds.transform.ebxml;

import static org.apache.commons.lang3.Validate.notNull;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.FOLDER_CLASS_NODE;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_HOME_COMMUNITY_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SUBMISSION_SET_CLASS_NODE;

import javax.activation.DataHandler;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLObjectLibrary;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLProvideAndRegisterDocumentSetRequest;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Document;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntryType;
import org.openehealth.ipf.commons.ihe.xds.core.requests.ProvideAndRegisterDocumentSet;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.AssociationTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.FolderTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.SubmissionSetTransformer;

/**
 * Transforms between a {@link ProvideAndRegisterDocumentSet} and its ebXML
 * representation.
 * 
 * @author Jens Riemschneider
 */
public class ProvideAndRegisterDocumentSetTransformer {
	private final EbXMLFactory factory;
	private final SubmissionSetTransformer submissionSetTransformer;
	private final DocumentEntryTransformer documentEntryTransformer;
	private final FolderTransformer folderTransformer;
	private final AssociationTransformer associationTransformer;

	/**
	 * Constructs the transformer
	 * 
	 * @param factory factory for version independent ebXML objects.
	 */
	public ProvideAndRegisterDocumentSetTransformer(EbXMLFactory factory) {
		notNull(factory, "factory cannot be null");
		this.factory = factory;

		submissionSetTransformer = new SubmissionSetTransformer(factory);
		documentEntryTransformer = new DocumentEntryTransformer(factory);
		folderTransformer = new FolderTransformer(factory);
		associationTransformer = new AssociationTransformer(factory);
	}

	/**
	 * Transforms a request into its ebXML representation.
	 * 
	 * @param request the request. Can be <code>null</code>.
	 * @return the ebXML representation. <code>null</code> if the input was
	 *         <code>null</code>.
	 */
	public EbXMLProvideAndRegisterDocumentSetRequest toEbXML(ProvideAndRegisterDocumentSet request) {
		if (request == null) {
			return null;
		}

		var library = factory.createObjectLibrary();
		var ebXML = factory.createProvideAndRegisterDocumentSetRequest(library);

		for (var doc : request.getDocuments()) {
			var docEntry = doc.getDocumentEntry();
			if (docEntry != null) {
				ebXML.addExtrinsicObject(documentEntryTransformer.toEbXML(docEntry, library));
				ebXML.addDocument(docEntry.getEntryUuid(), doc.getContent(DataHandler.class));
			}
		}

		for (var folder : request.getFolders()) {
			ebXML.addRegistryPackage(folderTransformer.toEbXML(folder, library));
			addClassification(ebXML, folder.getEntryUuid(), FOLDER_CLASS_NODE, library);
		}

		var submissionSet = request.getSubmissionSet();
		ebXML.addRegistryPackage(submissionSetTransformer.toEbXML(submissionSet, library));
		var entryUUID = submissionSet != null ? submissionSet.getEntryUuid() : null;
		addClassification(ebXML, entryUUID, SUBMISSION_SET_CLASS_NODE, library);

		for (var association : request.getAssociations()) {
			ebXML.addAssociation(associationTransformer.toEbXML(association, library));
		}

		if (request.getTargetHomeCommunityId() != null) {
			ebXML.addSlot(SLOT_NAME_HOME_COMMUNITY_ID, request.getTargetHomeCommunityId());
		}

		return ebXML;
	}

	/**
	 * Transforms an ebXML representation or a request.
	 * 
	 * @param ebXML the ebXML representation. Can be <code>null</code>.
	 * @return the request. <code>null</code> if the input was <code>null</code>.
	 */
	public ProvideAndRegisterDocumentSet fromEbXML(EbXMLProvideAndRegisterDocumentSetRequest ebXML) {
		if (ebXML == null) {
			return null;
		}

		var request = new ProvideAndRegisterDocumentSet();

		var documents = ebXML.getDocuments();
		for (var extrinsic : ebXML.getExtrinsicObjects(DocumentEntryType.STABLE.getUuid())) {
			var docEntry = documentEntryTransformer.fromEbXML(extrinsic);
			if (docEntry != null) {
				var document = new Document();
				document.setDocumentEntry(docEntry);
				if (docEntry.getEntryUuid() != null) {
					var id = docEntry.getEntryUuid();
					var data = documents.get(id);
					document.setContent(DataHandler.class, data);
				}
				request.getDocuments().add(document);
			}
		}

		for (var regPackage : ebXML.getRegistryPackages(FOLDER_CLASS_NODE)) {
			request.getFolders().add(folderTransformer.fromEbXML(regPackage));
		}

		var regPackages = ebXML.getRegistryPackages(SUBMISSION_SET_CLASS_NODE);
		if (regPackages.size() > 0) {
			request.setSubmissionSet(submissionSetTransformer.fromEbXML(regPackages.get(0)));
		}

		for (var association : ebXML.getAssociations()) {
			request.getAssociations().add(associationTransformer.fromEbXML(association));
		}

		request.setTargetHomeCommunityId(ebXML.getSingleSlotValue(SLOT_NAME_HOME_COMMUNITY_ID));

		return request;
	}

	private void addClassification(EbXMLProvideAndRegisterDocumentSetRequest ebXML, String classified, String node,
			EbXMLObjectLibrary library) {
		var classification = factory.createClassification(library);
		classification.setClassifiedObject(classified);
		classification.setClassificationNode(node);
		classification.assignUniqueId();
		ebXML.addClassification(classification);
	}
}
