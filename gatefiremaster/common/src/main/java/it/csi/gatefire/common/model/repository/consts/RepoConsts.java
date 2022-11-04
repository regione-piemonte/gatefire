/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository.consts;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.AssigningAuthority;

public class RepoConsts {

	private RepoConsts() {
		// TODO Auto-generated constructor stub
	}

	public static final String XDS_DOCUMENT_ENTRY__CLASS_CODE = "XDSDocumentEntry.classCode";
	public static final String VAL_CLASS_CODE_SCHEDA_VAC = "REF";
	public static final String VAL_CLASS_CODE_CERT_VAC = "SUM";
	public static final String XDS_DOCUMENT_ENTRY_FORMAT_CODE = "XDSDocumentEntry.classCode";
	public static final String VAL_FORMAT_CODE_PDF = "PDF";
	public static final String XDS_DOCUMENT_ENTRY_MIME_TYPE = "XDSDocumentEntry.mimeType";
	public static final String VAL_MIME_TYPE_PDF = "PDF";
	public static final String XDS_DOCUMENT_ENTRY_PATIENT_ID = "XDSDocumentEntry.patientId";
	public static final String XDS_DOCUMENT_ENTRY_UNIQUE_ID = "XDSDocumentEntry.uniqueId";

	public static final AssigningAuthority COD_FISC_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.4.3.2", "ISO");
	public static final AssigningAuthority ID_AURA_ASSIGNING_AUTH = new AssigningAuthority("2.16.840.1.113883.2.9.2.10",
			"ISO");
	public static final AssigningAuthority ID_FLS11_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.4.1.1", "ISO");
	public static final AssigningAuthority ID_STS11_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.4.1.3", "ISO");

	public static final AssigningAuthority ID_NUM_NOSOLOGICO_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.6", "ISO");
	public static final AssigningAuthority ID_EPISODIO_PS_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.7", "ISO");
	public static final AssigningAuthority ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.2", "ISO");

	public static final AssigningAuthority ID_RICETTA_DEMA_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.4.3.8", "ISO");
	public static final AssigningAuthority ID_RICETTA_CART_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.4.3.4", "ISO");
	public static final AssigningAuthority ID_RICHIESTA_CUP_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.1", "ISO");
	public static final AssigningAuthority ID_RICHIESTA_OD_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.1", "ISO");
	public static final AssigningAuthority ID_RICHIESTA_PS_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.1", "ISO");

	public static final AssigningAuthority ID_RICHIESTA_ALTRO_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.X", "ISO");
	public static final AssigningAuthority ID_IMMAGINI_RIS_ASSIGNING_AUTH = new AssigningAuthority(
			"2.16.840.1.113883.2.9.2.10.4.13.1.Y", "ISO");

	public static final String FORMAT_CODE_CODING_SCHEME = "2.16.840.1.113883.2.9.3.3.6.1.6";

	public static final String HEALTHCARE_FACILITY_CODING_SCHEME = "2.16.40.1.113883.2.9.3.3.6.1.1";

	public static final String PRACTICE_SET_CODE_CODING_SCHEME = "2.16.840.1.113883.2.9.3.3.6.1.2";

	public static final String TYPE_CODE_CODING_SCHEME = "2.16.840.1.113883.6.1";

	public static final String CODICE_CATALOGO_REGIONE_PIEMONTE_CODING_SCHEME = "2.16.840.1.113883.2.9.2.10.6.11";

	public static final String ADDRESS_TYPE_BIRTH = "N";
	public static final String ADDRESS_TYPE_LEGAL = "L";

	public static final String ID_TYPE_CODE_ADMISSION = "urn:rve:2016:admissionNumber";
	public static final String ID_TYPE_CODE_ACCESSION_RIS = "urn:eu:dedalus:x1v1:2017:studyUUID";

	/*
	 *  XDSDocumentEntry.mimeType: o PDF  XDSDocumentEntry.patientId: o Codice
	 * Fiscale  XDSDocumentEntry.sourcePatientId: Identificativi del paziente
	 * all’interno del dominio in cui è avvenuto l’evento che ha portato alla
	 * creazione di un documento e relative AssignedAuthority o 1° occorrenza:
	 * Codice Fiscale dell’assistito o 2° occorrenza: ID AURA dell’assistito (per
	 * pazienti fuori regione valorizzare con -1)
	 * 
	 * XDSDocumentEntry.typeCode: o REG-87273-9 in caso di Scheda Vaccinale o
	 * REG-82593-5 in caso di Certificato Vaccinale 
	 * DocumentEntry.sourcePatientInfo: slot che contengono gli attributi
	 * demografici del paziente, soggetto del documento. Gli slot vengono utilizzati
	 * per trasmettere differenti informazioni relative all’anagrafica del paziente.
	 * E’ obbligatorio trasmettere l’intera sestupla che permette l’identificazione
	 * univoca del paziente ovvero: nome, cognome, sesso, data di nascita e luogo di
	 * nascita (il codice fiscale è trasmesso con il metadato
	 * XDSDocumentEntry.sourcePatientId): o PID-5 (Patient Name): il nome del
	 * paziente viene trasmesso secondo il DataType XPN dell’HL7 v2.5: PID-5|<Family
	 * Name>^<Given Name> o PID-7 (Date/Time of Birth): la data di nascita del
	 * paziente viene trasmesso secondo il DataType TS (formato yyyymmdd) dell’HL7
	 * v2.5: PID-7|<Time (DTM)> o PID-8 (Administrative Sex): il sesso del paziente
	 * viene trasmesso secondo il seguente formato: PID-8|<Sex> Sono ammessi i
	 * valori: M (Male), F (Female) o PID-11 (Patient Address - Birth delivery
	 * location): il luogo di nascita del paziente viene trasmesso secondo il
	 * DataType XPN dell’HL7 v2.5: PID-11|^^<City (ST)>^<State or Province
	 * (ST)>^^<Country (ID)>^BDL^^ <Country/Parish Code (IS)> Ulteriori dati
	 * relativi all’indirizzo del paziente sono i seguenti: o PID-11 (Patient
	 * Address – Legal & Home address): gli indirizzi di residenza e domicilio del
	 * paziente vengono trasmessi in più slot, secondo il DataType XPN dell’HL7
	 * v2.5: PID-11|<Street Address (SAD)>^^<City (ST)>^<State or Province (ST)>^
	 * <Zip or Postal Code (ST)>^<Country (ID)>^<Address Type (ID)>^^
	 * <Country/Parish Code (IS)> Con Address Type:  L (Legal address): indirizzo
	 * di residenza  H (Home address): indirizzo di domicilio o PID-13 (Phone
	 * Number): i numeri di telefono ed indirizzi email vengono trasmessi in più
	 * slot, secondo il DataType XTN dell’HL7 v2.5: PID-13|<Telephone Number
	 * (ST)>^<Telecommunication Use Code (ID)^^ <Email Address (ST)> Con
	 * Telecommunication Use Code:  PRN: Numero di telefono principale  ORN:
	 * Numero di telefono secondario  WPN: Numero di telefono di lavoro  NET:
	 * Indirizzo Email o PID-26 (Citizenship): nazionalità dell’assistito secondo lo
	 * standard ISO 3166: PID-26|<Identifier (ST)>
	 */

	public static final String HEALTHCARE_FACILITY_TYPE_CODE = "healthcareFacilityTypeCode";

	public static final String CLASS_CODE = "classCode";

	public static final String TYPE_CODE = "typeCode";

	public static final String FORMAT_CODE = "formatCode";

	public static final String PRACTICE_SETTING_CODE = "practiceSettingCode";

	public static final String CONTENT_TYPE_CODE = "contentTypeCode";

	public static final String EVENT_CODE_LIST = "eventCodeList";

	public static final String CONFIDENTIALITY_CODE = "confidentialityCode";

	public static final String CATALOGO_REGIONE_PIEMONTE = "catalogoRegionePiemonte";

	public static final String FLS11 = "FLS11";
	public static final String STS11 = "STS11";

	public static final String P99 = "P99";
	public static final String P98 = "P98";
	public static final String D99 = "D99";
	public static final String ROL99 = "ROL99";

	public static final String TIPO_EPISODIO_AMBULATORIO = "O";
	public static final String TIPO_EPISODIO_RICOVERO = "I";
	public static final String TIPO_EPISODIO_PS = "E";

	public static final String CLAIM_NOME = "http://wso2.org/claims/givenname";
	public static final String CLAIM_COGNOME = "http://wso2.org/claims/lastname";
	public static final String CLAIM_CODICE_FISCALE = "http://wso2.org/claims/codiceFiscale";
	public static final String CLAIM_RUOLO = "http://wso2.org/claims/role";

	public static final String CONSENT_TYPE_ALL = "A";
	public static final String CONSENT_TYPE_SLOT = "S";
	public static final String CONSENT_TYPE_PLATFORM = "C";

	public static final String TRANSAZIONE_ITI18 = "ITI-18";
	public static final String TRANSAZIONE_ITI41 = "ITI-41";
	public static final String TRANSAZIONE_ITI41_UNDO = "ITI-41_UNDO";
	public static final String TRANSAZIONE_ITI43 = "ITI-43";
	public static final String TRANSAZIONE_ITI57 = "ITI-57";
	public static final String TRANSAZIONE_ITI62 = "ITI-62";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI18 = "WS_ADDRESS_TO_ITI-18";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI43 = "WS_ADDRESS_TO_ITI-43";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI41 = "WS_ADDRESS_TO_ITI-41";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI41_UNDO = "WS_ADDRESS_TO_ITI-41_UNDO";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI62 = "WS_ADDRESS_TO_ITI-62";
	public static final String TRANSAZIONE_WS_ADDRESS_TO_ITI57 = "WS_ADDRESS_TO_ITI-57";
	public static final String TRANSAZIONE_ASSERTION = "ASSERTION";

	public static final String SUBMISSION_SET_SOURCE_ID_PREFIX = "2.16.840.1.113883.2.9.2.10.4.5.1";
	public static final String SUBMISSION_SET_UNIQUE_ID_PREFIX = "2.16.840.1.113883.2.9.2.10.4.3";

	public static final String METADATO_TIPO_ATT_CLINICA = "tipoAttivitaClinica";

	public static final String TIPO_REPOSITORY_DEDALUS = "DEDALUS";

}
