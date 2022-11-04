/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.transform.ebxml;

import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp.toHL7;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_AUTHOR_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_CLASS_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_CONFIDENTIALITY_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_EVENT_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_FORMAT_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_LIMITED_METADATA_CLASS_NODE;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_LOCALIZED_STRING_PATIENT_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_LOCALIZED_STRING_UNIQUE_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_PATIENT_ID_EXTERNAL_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_PRACTICE_SETTING_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_TYPE_CODE_CLASS_SCHEME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.DOC_ENTRY_UNIQUE_ID_EXTERNAL_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_CREATION_TIME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_DOCUMENT_AVAILABILITY;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_HASH;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_LANGUAGE_CODE;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_LEGAL_AUTHENTICATOR;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_REFERENCE_ID_LIST;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_REPOSITORY_UNIQUE_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_SERVICE_START_TIME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_SERVICE_STOP_TIME;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_SIZE;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_SOURCE_PATIENT_ID;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_SOURCE_PATIENT_INFO;
import static org.openehealth.ipf.commons.ihe.xds.core.metadata.Vocabulary.SLOT_NAME_URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLExtrinsicObject;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLFactory;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLObjectLibrary;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLSlot;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentAvailability;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntryType;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Hl7v2Based;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.PatientInfo;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Person;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.ReferenceId;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.AuthorTransformer;
import org.openehealth.ipf.commons.ihe.xds.core.transform.ebxml.CodeTransformer;

/**
 * Transforms between a {@link DocumentEntry} and its ebXML representation.
 * 
 * @author Jens Riemschneider
 */
public class DocumentEntryTransformer extends
		it.csi.gatefire.repository.xds.transform.ebxml.XDSMetaClassTransformer<EbXMLExtrinsicObject, DocumentEntry> {
	private final AuthorTransformer authorTransformer;
	private final CodeTransformer codeTransformer;

	private final PatientInfoTransformer patientInfoTransformer = new PatientInfoTransformer();

	private static final String[] customSlots = { "firmatoDigitalmente", "dataOraFirmaDocumento",
			"documentoAnnullativo", "codiceLuogoAccettazione", "codiceLuogoiDimissione", "codiceLuogoCP",
			"legalAuthenticatorTime", "codiceCentroPrelievi", "codiceCentroPrelievi", "tipoFirma", "codicePIN",
			"codiceDocumentoScaricabile", "dataDisponibilitaReferto", "pagatoTicket", "importoTicketDaPagare",
			"importoTicketPagato", "privacyDocumento", "oscuraScaricoCittadino", "scaricabileDalCittadino",
			"soggettoALeggiSpeciali", "invioFSE", "invioCLS", "tipoEpisodio", "codiceApplicativo", "tokenConservazione",
			"tipoEpisodioOriginanteRichiesta", "tipoAttivitaClinica", "brancaFSE" };

	/**
	 * Constructs the transformer
	 * 
	 * @param factory factory for version independent ebXML objects.
	 */
	public DocumentEntryTransformer(EbXMLFactory factory) {
		super(DOC_ENTRY_PATIENT_ID_EXTERNAL_ID, DOC_ENTRY_LOCALIZED_STRING_PATIENT_ID, DOC_ENTRY_UNIQUE_ID_EXTERNAL_ID,
				DOC_ENTRY_LOCALIZED_STRING_UNIQUE_ID, DOC_ENTRY_LIMITED_METADATA_CLASS_NODE, factory);

		authorTransformer = new AuthorTransformer(factory);
		codeTransformer = new CodeTransformer(factory);
	}

	@Override
	protected EbXMLExtrinsicObject createEbXMLInstance(String id, EbXMLObjectLibrary objectLibrary) {
		return factory.createExtrinsic(id, objectLibrary);
	}

	@Override
	protected it.csi.gatefire.common.model.repository.DocumentEntry createMetaClassInstance() {
		return new it.csi.gatefire.common.model.repository.DocumentEntry();
	}

	@Override
	protected void addAttributesFromEbXML(DocumentEntry docEntry, EbXMLExtrinsicObject extrinsic) {
		super.addAttributesFromEbXML(docEntry, extrinsic);
		docEntry.setAvailabilityStatus(extrinsic.getStatus());
		docEntry.setMimeType(extrinsic.getMimeType());
		docEntry.setType(DocumentEntryType.valueOfUuid(extrinsic.getObjectType()));
		docEntry.setHomeCommunityId(extrinsic.getHome());
	}

	@Override
	protected void addAttributes(DocumentEntry metaData, EbXMLExtrinsicObject ebXML, EbXMLObjectLibrary objectLibrary) {
		super.addAttributes(metaData, ebXML, objectLibrary);
		ebXML.setStatus(metaData.getAvailabilityStatus());
		ebXML.setMimeType(metaData.getMimeType());
		ebXML.setObjectType(DocumentEntryType.toUuid(metaData.getType()));
		ebXML.setHome(metaData.getHomeCommunityId());
	}

	@Override
	protected void addSlotsFromEbXML(DocumentEntry docEntry, EbXMLExtrinsicObject extrinsic) {

		super.addSlotsFromEbXML(docEntry, extrinsic);

		docEntry.setCreationTime(extrinsic.getSingleSlotValue(SLOT_NAME_CREATION_TIME));
		docEntry.setHash(extrinsic.getSingleSlotValue(SLOT_NAME_HASH));
		docEntry.setLanguageCode(extrinsic.getSingleSlotValue(SLOT_NAME_LANGUAGE_CODE));
		docEntry.setServiceStartTime(extrinsic.getSingleSlotValue(SLOT_NAME_SERVICE_START_TIME));
		docEntry.setServiceStopTime(extrinsic.getSingleSlotValue(SLOT_NAME_SERVICE_STOP_TIME));
		docEntry.setRepositoryUniqueId(extrinsic.getSingleSlotValue(SLOT_NAME_REPOSITORY_UNIQUE_ID));
		docEntry.setUri(extrinsic.getSingleSlotValue(SLOT_NAME_URI));
		docEntry.setDocumentAvailability(
				DocumentAvailability.valueOfOpcode(extrinsic.getSingleSlotValue(SLOT_NAME_DOCUMENT_AVAILABILITY)));

		var size = extrinsic.getSingleSlotValue(SLOT_NAME_SIZE);
		docEntry.setSize(size != null ? Long.parseLong(size) : null);

		var hl7LegalAuthenticator = extrinsic.getSingleSlotValue(SLOT_NAME_LEGAL_AUTHENTICATOR);
		docEntry.setLegalAuthenticator(Hl7v2Based.parse(hl7LegalAuthenticator, Person.class));

		var sourcePatient = extrinsic.getSingleSlotValue(SLOT_NAME_SOURCE_PATIENT_ID);
		docEntry.setSourcePatientId(Hl7v2Based.parse(sourcePatient, Identifiable.class));
		if (docEntry instanceof it.csi.gatefire.common.model.repository.DocumentEntry) {

			List<String> list = extrinsic.getSlotValues(SLOT_NAME_SOURCE_PATIENT_ID);

			List<Identifiable> patientList = new ArrayList<>();

			for (String string : list) {
				patientList.add(Hl7v2Based.parse(string, Identifiable.class));
			}
			((it.csi.gatefire.common.model.repository.DocumentEntry) docEntry).setSourcePatientIdList(patientList);

		}

		var slotValues = extrinsic.getSlotValues(SLOT_NAME_SOURCE_PATIENT_INFO);
		PatientInfo patientInfo = patientInfoTransformer.fromHL7(slotValues);
		docEntry.setSourcePatientInfo(patientInfo);
		((it.csi.gatefire.common.model.repository.DocumentEntry) docEntry)
				.setExtSourcePatientInfo(patientInfoTransformer.fromHL7ext(slotValues));

		for (var referenceIdValue : extrinsic.getSlotValues(SLOT_NAME_REFERENCE_ID_LIST)) {
			docEntry.getReferenceIdList().add(Hl7v2Based.parse(referenceIdValue, ReferenceId.class));
		}
		Map<String, List<String>> map = docEntry.getExtraMetadata();
		if (map == null) {
			map = new HashMap<>();
		}
		List<String> customSlotList = Arrays.asList(customSlots);
		for (EbXMLSlot slot : extrinsic.getSlots()) {
			int index = customSlotList.indexOf(slot.getName());
			if (index != -1) {
				map.put(slot.getName(), slot.getValueList());
			}
		}
		docEntry.setExtraMetadata(map);
	}

	@Override
	protected void addSlots(DocumentEntry docEntry, EbXMLExtrinsicObject extrinsic, EbXMLObjectLibrary objectLibrary) {
		super.addSlots(docEntry, extrinsic, objectLibrary);

		extrinsic.addSlot(SLOT_NAME_CREATION_TIME, toHL7(docEntry.getCreationTime()));
		extrinsic.addSlot(SLOT_NAME_HASH, docEntry.getHash());
		extrinsic.addSlot(SLOT_NAME_LANGUAGE_CODE, docEntry.getLanguageCode());
		extrinsic.addSlot(SLOT_NAME_SERVICE_START_TIME, toHL7(docEntry.getServiceStartTime()));
		extrinsic.addSlot(SLOT_NAME_SERVICE_STOP_TIME, toHL7(docEntry.getServiceStopTime()));
		extrinsic.addSlot(SLOT_NAME_REPOSITORY_UNIQUE_ID, docEntry.getRepositoryUniqueId());
		extrinsic.addSlot(SLOT_NAME_URI, docEntry.getUri());
		extrinsic.addSlot(SLOT_NAME_DOCUMENT_AVAILABILITY,
				DocumentAvailability.toFullQualifiedOpcode(docEntry.getDocumentAvailability()));

		var size = docEntry.getSize();
		extrinsic.addSlot(SLOT_NAME_SIZE, size != null ? size.toString() : null);

		var hl7LegalAuthenticator = Hl7v2Based.render(docEntry.getLegalAuthenticator());
		extrinsic.addSlot(SLOT_NAME_LEGAL_AUTHENTICATOR, hl7LegalAuthenticator);

		if (docEntry instanceof it.csi.gatefire.common.model.repository.DocumentEntry) {
			var slotValues = patientInfoTransformer.toHL7(
					((it.csi.gatefire.common.model.repository.DocumentEntry) docEntry).getExtSourcePatientInfo());
			extrinsic.addSlot(SLOT_NAME_SOURCE_PATIENT_INFO, slotValues.toArray(new String[0]));
		} else {
			var slotValues = (new org.openehealth.ipf.commons.ihe.xds.core.transform.hl7.PatientInfoTransformer())
					.toHL7(docEntry.getSourcePatientInfo());
			extrinsic.addSlot(SLOT_NAME_SOURCE_PATIENT_INFO, slotValues.toArray(new String[0]));
		}
		if (!docEntry.getReferenceIdList().isEmpty()) {
			var referenceIdValues = new String[docEntry.getReferenceIdList().size()];
			for (var i = 0; i < docEntry.getReferenceIdList().size(); ++i) {
				referenceIdValues[i] = Hl7v2Based.render(docEntry.getReferenceIdList().get(i));
			}
			extrinsic.addSlot(SLOT_NAME_REFERENCE_ID_LIST, referenceIdValues);
		}

		if (docEntry instanceof it.csi.gatefire.common.model.repository.DocumentEntry) {

			List<String> targetCollection = new ArrayList<>();
			for (var source : ((it.csi.gatefire.common.model.repository.DocumentEntry) docEntry)
					.getSourcePatientIdList()) {
				var target = Hl7v2Based.render(source);
				if (source != null) {
					targetCollection.add(target);
				}
			}

			var array = new String[targetCollection.size()];
			extrinsic.addSlot(SLOT_NAME_SOURCE_PATIENT_ID, targetCollection.toArray(array));

		} else {
			var sourcePatient = Hl7v2Based.render(docEntry.getSourcePatientId());
			extrinsic.addSlot(SLOT_NAME_SOURCE_PATIENT_ID, sourcePatient);
		}
	}

	@Override
	protected void addClassificationsFromEbXML(DocumentEntry docEntry, EbXMLExtrinsicObject extrinsic) {
		super.addClassificationsFromEbXML(docEntry, extrinsic);

		for (var author : extrinsic.getClassifications(DOC_ENTRY_AUTHOR_CLASS_SCHEME)) {
			docEntry.getAuthors().add(authorTransformer.fromEbXML(author));
		}

		var classCode = extrinsic.getSingleClassification(DOC_ENTRY_CLASS_CODE_CLASS_SCHEME);
		docEntry.setClassCode(codeTransformer.fromEbXML(classCode));

		var formatCode = extrinsic.getSingleClassification(DOC_ENTRY_FORMAT_CODE_CLASS_SCHEME);
		docEntry.setFormatCode(codeTransformer.fromEbXML(formatCode));

		var healthcareFacility = extrinsic
				.getSingleClassification(DOC_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE_CLASS_SCHEME);
		docEntry.setHealthcareFacilityTypeCode(codeTransformer.fromEbXML(healthcareFacility));

		var practiceSetting = extrinsic.getSingleClassification(DOC_ENTRY_PRACTICE_SETTING_CODE_CLASS_SCHEME);
		docEntry.setPracticeSettingCode(codeTransformer.fromEbXML(practiceSetting));

		var typeCode = extrinsic.getSingleClassification(DOC_ENTRY_TYPE_CODE_CLASS_SCHEME);
		docEntry.setTypeCode(codeTransformer.fromEbXML(typeCode));

		var confidentialityCodes = docEntry.getConfidentialityCodes();
		confidentialityCodes.addAll(extrinsic.getClassifications(DOC_ENTRY_CONFIDENTIALITY_CODE_CLASS_SCHEME).stream()
				.map(codeTransformer::fromEbXML).collect(Collectors.toList()));

		var eventCodeList = docEntry.getEventCodeList();
		eventCodeList.addAll(extrinsic.getClassifications(DOC_ENTRY_EVENT_CODE_CLASS_SCHEME).stream()
				.map(codeTransformer::fromEbXML).collect(Collectors.toList()));

	}

	@Override
	protected void addClassifications(DocumentEntry docEntry, EbXMLExtrinsicObject extrinsic,
			EbXMLObjectLibrary objectLibrary) {
		super.addClassifications(docEntry, extrinsic, objectLibrary);

		for (var author : docEntry.getAuthors()) {
			var authorClasification = authorTransformer.toEbXML(author, objectLibrary);
			extrinsic.addClassification(authorClasification, DOC_ENTRY_AUTHOR_CLASS_SCHEME);
		}

		var classCode = codeTransformer.toEbXML(docEntry.getClassCode(), objectLibrary);
		extrinsic.addClassification(classCode, DOC_ENTRY_CLASS_CODE_CLASS_SCHEME);

		var formatCode = codeTransformer.toEbXML(docEntry.getFormatCode(), objectLibrary);
		extrinsic.addClassification(formatCode, DOC_ENTRY_FORMAT_CODE_CLASS_SCHEME);

		var healthcareFacility = codeTransformer.toEbXML(docEntry.getHealthcareFacilityTypeCode(), objectLibrary);
		extrinsic.addClassification(healthcareFacility, DOC_ENTRY_HEALTHCARE_FACILITY_TYPE_CODE_CLASS_SCHEME);

		var practiceSetting = codeTransformer.toEbXML(docEntry.getPracticeSettingCode(), objectLibrary);
		extrinsic.addClassification(practiceSetting, DOC_ENTRY_PRACTICE_SETTING_CODE_CLASS_SCHEME);

		var typeCode = codeTransformer.toEbXML(docEntry.getTypeCode(), objectLibrary);
		extrinsic.addClassification(typeCode, DOC_ENTRY_TYPE_CODE_CLASS_SCHEME);

		for (var confCode : docEntry.getConfidentialityCodes()) {
			var conf = codeTransformer.toEbXML(confCode, objectLibrary);
			extrinsic.addClassification(conf, DOC_ENTRY_CONFIDENTIALITY_CODE_CLASS_SCHEME);
		}

		for (var eventCode : docEntry.getEventCodeList()) {
			var event = codeTransformer.toEbXML(eventCode, objectLibrary);
			extrinsic.addClassification(event, DOC_ENTRY_EVENT_CODE_CLASS_SCHEME);
		}
	}
}
