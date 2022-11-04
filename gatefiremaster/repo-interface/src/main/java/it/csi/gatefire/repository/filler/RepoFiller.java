/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.filler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.Address;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Author;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.CXiAssigningAuthority;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Code;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Hl7v2Based;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.LocalizedString;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Name;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Organization;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Person;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.ReferenceId;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.XpnName;
import org.springframework.util.StringUtils;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.datatype.XPN;
import it.csi.gatefire.common.model.ITIMetadata;
import it.csi.gatefire.common.model.repository.ConfidentialityCode;
import it.csi.gatefire.common.model.repository.Documento;
import it.csi.gatefire.common.model.repository.Episodio;
import it.csi.gatefire.common.model.repository.Indirizzo;
import it.csi.gatefire.common.model.repository.Medico;
import it.csi.gatefire.common.model.repository.Paziente;
import it.csi.gatefire.common.model.repository.Persona;
import it.csi.gatefire.common.model.repository.Prestazione;
import it.csi.gatefire.common.model.repository.Richiesta;
import it.csi.gatefire.common.model.repository.TipoFirma;
import it.csi.gatefire.common.model.repository.Utente;
import it.csi.gatefire.common.model.repository.consts.RepoConsts;
import it.csi.gatefire.common.util.DateUtils;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;

public class RepoFiller {

	private RepoFiller() {
		super();

	}

	public static Person fillPerson(Persona persona, String ruolo, boolean degree) {

		Person person = new Person();
		if (persona != null) {
			Identifiable id = new Identifiable();
			id.setId(persona.getCodiceFiscale());

			id.setAssigningAuthority(RepoConsts.COD_FISC_ASSIGNING_AUTH);

			var name = new XpnName();
			name.setFamilyName(persona.getCognome());
			name.setGivenName(persona.getNome());

			name.setSecondAndFurtherGivenNames(persona.getSecondoNome());
			name.setPrefix(persona.getPrefisso());
			if (ruolo != null && degree) {
				name.setDegree(ruolo);
			}

			person.setName(name);
			person.setId(id);

		}

		return person;
	}

	public static List<Author> fillAuthors(List<Medico> medici, String codiceAzienda, String codiceStruttura,
			String assettoOrganizzativo, Map<String, List<GatefireDXdscode>> map, boolean degree) {
		List<Author> list = new ArrayList<>();
		for (Medico medico : medici) {

			list.add(fillAuthor(medico, codiceAzienda, codiceStruttura, assettoOrganizzativo, map, degree));

		}

		return list;
	}

	public static Author fillAuthor(Medico medico, String codiceAzienda, String codiceStruttura,
			String assettoOrganizzativo, Map<String, List<GatefireDXdscode>> map, boolean degree) {

		if (medico != null) {

			Person person = fillPerson(medico, medico.getRuolo().toString(), degree);

			Identifiable ruolo = new Identifiable(medico.getRuolo().toString());
			Author author = new Author();
			author.setAuthorPerson(person);
			author.getAuthorRole().add(ruolo);

			List<GatefireDXdscode> listFl11 = map.get(RepoConsts.FLS11);
			Organization mainOrg = fillOrganization(codiceAzienda, listFl11, true);

			author.getAuthorInstitution().add(mainOrg);

			if (codiceStruttura != null) {
				List<GatefireDXdscode> listSTS11 = map.get(RepoConsts.STS11);
				Organization secondOrg = fillOrganization(codiceStruttura, listSTS11, false);
				author.getAuthorInstitution().add(secondOrg);
			}

			Code code = fillCode(assettoOrganizzativo, map.get(RepoConsts.PRACTICE_SETTING_CODE));
			if (code != null) {
				author.getAuthorSpecialty().add(new Identifiable(code.getDisplayName().getValue()));
			}
			return author;
		}
		return null;
	}

	public static Author fillAuthor(Richiesta richiesta, String assettoOrganizzativo,
			Map<String, List<GatefireDXdscode>> map, boolean degree) {

		Utente utente = richiesta.getUtente();
		Person person = fillPerson(utente, utente.getRuolo().toString(), degree);
		Identifiable ruolo = new Identifiable(utente.getRuolo().toString());

		Author author = new Author();
		author.setAuthorPerson(person);
		author.getAuthorRole().add(ruolo);
		List<GatefireDXdscode> listFl11 = map.get(RepoConsts.FLS11);
		Organization mainOrg = fillOrganization(richiesta.getCodiceAzienda(), listFl11, true);

		author.getAuthorInstitution().add(mainOrg);
		if (utente.getCodiceStruttura() != null) {
			List<GatefireDXdscode> listSTS11 = map.get(RepoConsts.STS11);
			Organization secondOrg = fillOrganization(utente.getCodiceStruttura(), listSTS11, false);
			author.getAuthorInstitution().add(secondOrg);
		}

		Code code = fillCode(assettoOrganizzativo, map.get(RepoConsts.PRACTICE_SETTING_CODE));
		if (code != null) {
			author.getAuthorSpecialty().add(new Identifiable(code.getDisplayName().getValue()));
		}

		return author;
	}

	public static SubmissionSet fillSubmissionSet(ITIMetadata itiMetadata, Map<String, List<GatefireDXdscode>> map,
			GatefireDApplicazione applicazione) {

		SubmissionSet ss = new SubmissionSet();
		Documento doc = itiMetadata.getDocumento();
		Richiesta richiesta = itiMetadata.getRichiesta();
		String path = applicazione.getSenderInstitution() + applicazione.getFolderIdentificationCode();
		String submissionSetUniqueId = RepoConsts.SUBMISSION_SET_UNIQUE_ID_PREFIX + "." + path
				+ DateUtils.getCurrentTimeStamp();

		String submissionSetsourceId = RepoConsts.SUBMISSION_SET_SOURCE_ID_PREFIX + "." + path;

		ss.setUniqueId(submissionSetUniqueId);
		ss.setSourceId(submissionSetsourceId);
		if (itiMetadata.getPaziente() != null) {
			Identifiable cf = new Identifiable(itiMetadata.getPaziente().getCodiceFiscale(),
					RepoConsts.COD_FISC_ASSIGNING_AUTH);
			ss.setPatientId(cf);
		}

		ss.setEntryUuid("SubmissionSet01");

		Author mainAuthor = fillAuthor(richiesta, doc.getAssettoOrganizzativo(), map, false);

		ss.getAuthors().add(mainAuthor);

		ss.setContentTypeCode(fillCode(doc.getTipoAttivitaClinica(), map.get(RepoConsts.CONTENT_TYPE_CODE)));
		ss.setSubmissionTime(DateUtils.getDataPerXds(new Date()));
		return ss;
	}

	public static SubmissionSet fillSubmissionSet(Richiesta richiesta, Identifiable patientid,
			String assettoOrganizzativo, String tipoAttivitaClinica, Map<String, List<GatefireDXdscode>> map,
			GatefireDApplicazione applicazione) {

		SubmissionSet ss = new SubmissionSet();

		String path = applicazione.getSenderInstitution() + applicazione.getFolderIdentificationCode();
		String submissionSetUniqueId = RepoConsts.SUBMISSION_SET_UNIQUE_ID_PREFIX + "." + path
				+ DateUtils.getCurrentTimeStamp();

		String submissionSetsourceId = RepoConsts.SUBMISSION_SET_SOURCE_ID_PREFIX + "." + path;

		ss.setUniqueId(submissionSetUniqueId);
		ss.setSourceId(submissionSetsourceId);

		ss.setPatientId(patientid);

		ss.setEntryUuid("SubmissionSet01");

		Author mainAuthor = fillAuthor(richiesta, assettoOrganizzativo, map, false);

		ss.getAuthors().add(mainAuthor);

		ss.setContentTypeCode(fillCode(tipoAttivitaClinica, map.get(RepoConsts.CONTENT_TYPE_CODE)));
		ss.setSubmissionTime(DateUtils.getDataPerXds(new Date()));
		return ss;
	}

	public static it.csi.gatefire.common.model.repository.DocumentEntry fillDocumentEntry(ITIMetadata itiMetadata,
			Map<String, List<GatefireDXdscode>> map, GatefireDRepository repository,
			GatefireDApplicazione applicazione) {

		String consentType = null;
		boolean gestioneIdEpisodio = false;

		if (repository.getGestioneIdEpisodio() != null) {
			gestioneIdEpisodio = repository.getGestioneIdEpisodio();
		}

		Documento doc = itiMetadata.getDocumento();
		Richiesta richiesta = itiMetadata.getRichiesta();
		Episodio episodio = itiMetadata.getEpisodio();
		Paziente paziente = itiMetadata.getPaziente();

		it.csi.gatefire.common.model.repository.DocumentEntry de = new it.csi.gatefire.common.model.repository.DocumentEntry();

		de.setEntryUuid("Document01");

		de.setLanguageCode("it-IT");

		de.setUniqueId(doc.getUniqueId());

		de.setHealthcareFacilityTypeCode(
				fillCode(doc.getTipologiaStrutturaProdDoc(), map.get(RepoConsts.HEALTHCARE_FACILITY_TYPE_CODE)));
		de.setClassCode(fillCode(doc.getTipologiaDocumentoAlto(), map.get(RepoConsts.CLASS_CODE)));
		de.setTypeCode(fillCode(doc.getTipologiaDocumentoMedio(), map.get(RepoConsts.TYPE_CODE)));
		de.setFormatCode(fillCode(doc.getFormatCode(), map.get(RepoConsts.FORMAT_CODE)));
		de.setPracticeSettingCode(fillCode(doc.getAssettoOrganizzativo(), map.get(RepoConsts.PRACTICE_SETTING_CODE)));
		de.setMimeType(doc.getMimeType());

		List<Author> mediciRedattore = fillAuthors(doc.getMedicoRedattore(), richiesta.getCodiceAzienda(),
				richiesta.getUtente().getCodiceStruttura(), doc.getAssettoOrganizzativo(), map, false);
		Author medicoValidatore = fillAuthor(doc.getMedicoValidatore(), richiesta.getCodiceAzienda(), null,
				doc.getAssettoOrganizzativo(), map, true);

		if (medicoValidatore != null) {

			de.setLegalAuthenticator(medicoValidatore.getAuthorPerson());

		}
		if (!mediciRedattore.isEmpty()) {

			de.getAuthors().addAll(mediciRedattore);
		}

		List<String> nre = doc.getNre(); // documententry.referenceList idtypecode.UniqueId_reference

		if (nre != null) {
			for (String idnre : nre) {
				ReferenceId ref = new ReferenceId(idnre,
						new CXiAssigningAuthority("", RepoConsts.ID_RICETTA_DEMA_ASSIGNING_AUTH.getUniversalId(),
								RepoConsts.ID_RICETTA_DEMA_ASSIGNING_AUTH.getUniversalIdType()),
						ReferenceId.ID_TYPE_CODE_UNIQUE_ID);
				de.getReferenceIdList().add(ref);
			}
		}

		List<String> accNumbList = doc.getAccessionNumber();
		if (accNumbList != null) {
			for (String accessionNumber : accNumbList) {
				ReferenceId ref = new ReferenceId(accessionNumber,
						new CXiAssigningAuthority("", RepoConsts.ID_IMMAGINI_RIS_ASSIGNING_AUTH.getUniversalId(),
								RepoConsts.ID_IMMAGINI_RIS_ASSIGNING_AUTH.getUniversalIdType()),
						RepoConsts.ID_TYPE_CODE_ACCESSION_RIS);
				de.getReferenceIdList().add(ref);
			}

		}

		var extraM = new HashMap<String, List<String>>();

		de.setExtraMetadata(extraM);
		Boolean firmatoDigitalmente = doc.getFirmatoDigitalmente(); // documentEntry.extraData
		if (firmatoDigitalmente != null) {
			de.getExtraMetadata().put("firmatoDigitalmente", Arrays.asList(firmatoDigitalmente.toString()));
		}

		boolean slot = (consentType == null) || RepoConsts.CONSENT_TYPE_SLOT.equalsIgnoreCase(consentType)
				|| RepoConsts.CONSENT_TYPE_ALL.equalsIgnoreCase(consentType);

		boolean consentPlatform = (consentType == null)
				|| RepoConsts.CONSENT_TYPE_PLATFORM.equalsIgnoreCase(consentType)
				|| RepoConsts.CONSENT_TYPE_ALL.equalsIgnoreCase(consentType);

		Date dataOraFirmaDocumento = doc.getDataOraFirmaDocumento(); // documentEntry.extraData
																		// documentEntry.legalAuthenticatorTime
		if (dataOraFirmaDocumento != null) {

			if (slot) {
				de.getExtraMetadata().put("dataOraFirmaDocumento",
						Arrays.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataOraFirmaDocumento))));
			}
			if (medicoValidatore != null && consentPlatform) {

				de.getExtraMetadata().put("legalAuthenticatorTime",
						Arrays.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataOraFirmaDocumento))));
			}

		}
		TipoFirma tipoFirma = doc.getTipoFirma();// documentEntry.extraData
		if (tipoFirma != null) {
			de.getExtraMetadata().put("tipoFirma", Arrays.asList(tipoFirma.getTipo()));

		}
		String codicePin = doc.getCodicePin();// documentEntry.extraData
		if (StringUtils.hasLength(codicePin)) {
			de.getExtraMetadata().put("codicePIN", Arrays.asList(codicePin));

		}

		String codiceDocumentoScaricabile = doc.getCodiceDocumentoScaricabile();// documentEntry.extraData
		if (StringUtils.hasLength(codiceDocumentoScaricabile)) {
			de.getExtraMetadata().put("codiceDocumentoScaricabile", Arrays.asList(codiceDocumentoScaricabile));

		}

		if (richiesta.getUtente() != null) {
			String codiceMatricola = richiesta.getUtente().getCodiceMatricola();

			if (StringUtils.hasLength(codiceMatricola)) {
				if (!RepoConsts.TIPO_REPOSITORY_DEDALUS.equalsIgnoreCase(repository.getTipoRepository())) {
					de.getExtraMetadata().put("codiceMatricola", Arrays.asList(codiceMatricola));
				}
			}
		}

		Date dataDisponibilitaReferto = doc.getDataDisponibilitaReferto(); // documentEntry.extraData
		// documentEntry.legalAuthenticatorTime
		if (dataDisponibilitaReferto != null) {
			de.getExtraMetadata().put("dataDisponibilitaReferto",
					Arrays.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataDisponibilitaReferto))));
		}

		String pagatoTicket = doc.getPagatoTicket();// documentEntry.extraData
		if (StringUtils.hasLength(pagatoTicket)) {
			de.getExtraMetadata().put("pagatoTicket", Arrays.asList(pagatoTicket));

		}
		String importoTicketDaPagare = doc.getImportoTicketDaPagare();// documentEntry.extraData
		if (StringUtils.hasLength(importoTicketDaPagare)) {
			de.getExtraMetadata().put("importoTicketDaPagare", Arrays.asList(importoTicketDaPagare));

		}
		String importoTicketPagato = doc.getImportoTicketPagato();// documentEntry.extraData
		if (importoTicketPagato != null) {
			de.getExtraMetadata().put("importoTicketPagato", Arrays.asList(importoTicketPagato));

		}
		String privacyDocumento = doc.getPrivacyDocumento();
		String oscuraScaricoCittadino = doc.getOscuraScaricoCittadino();
		Boolean scaricabileDalCittadino = doc.getScaricabileDalCittadino();
		Boolean soggettoALeggiSpeciali = doc.getSoggettoALeggiSpeciali();
		Boolean oscuramentoDocDSE = doc.getOscuramentoDocDSE();

		String livelloRiservatezza = doc.getLivelloRiservatezza();

		if (livelloRiservatezza != null) {
			de.getConfidentialityCodes().add(fillCode(livelloRiservatezza, map.get(RepoConsts.CONFIDENTIALITY_CODE)));
		}

		if (consentPlatform) {

			if ("1".equalsIgnoreCase(privacyDocumento)) {
				de.getEventCodeList().add(fillCode(RepoConsts.P99, map.get(RepoConsts.EVENT_CODE_LIST)));
			}

			if ("S".equalsIgnoreCase(oscuraScaricoCittadino)) {
				de.getEventCodeList().add(fillCode(RepoConsts.P98, map.get(RepoConsts.EVENT_CODE_LIST)));
			}

			if (oscuramentoDocDSE != null && oscuramentoDocDSE.booleanValue()) {
				de.getEventCodeList().add(fillCode(RepoConsts.D99, map.get(RepoConsts.EVENT_CODE_LIST)));
			}
			/*
			 * if ("M".equalsIgnoreCase(oscuraScaricoCittadino)) {
			 * 
			 * de.getEventCodeList().add(fillCode(RepoConsts.P98,
			 * map.get(RepoConsts.EVENT_CODE_LIST))); }
			 */
			if (scaricabileDalCittadino != null && !scaricabileDalCittadino.booleanValue()) {

				de.getEventCodeList().add(fillCode(RepoConsts.ROL99, map.get(RepoConsts.EVENT_CODE_LIST)));

			}

		}

		if (slot) {
			if (StringUtils.hasLength(privacyDocumento)) {
				de.getExtraMetadata().put("privacyDocumento", Arrays.asList(privacyDocumento));
			}

			if (StringUtils.hasLength(oscuraScaricoCittadino)) {
				de.getExtraMetadata().put("oscuraScaricoCittadino", Arrays.asList(oscuraScaricoCittadino));
			}

			if (scaricabileDalCittadino != null) {
				de.getExtraMetadata().put("scaricabileDalCittadino", Arrays.asList(scaricabileDalCittadino.toString()));
			}
		}

		if (soggettoALeggiSpeciali != null) {
			if (slot) {
				de.getExtraMetadata().put("soggettoALeggiSpeciali", Arrays.asList(soggettoALeggiSpeciali.toString()));
			}
			if (soggettoALeggiSpeciali.booleanValue()) {
				if (!ConfidentialityCode.V.toString().equalsIgnoreCase(livelloRiservatezza)) {
					de.getConfidentialityCodes()
							.add(fillCode(ConfidentialityCode.V.toString(), map.get(RepoConsts.CONFIDENTIALITY_CODE)));
				}
			}
		}

		String invioCLS = doc.getInvioCLS();// documentEntry.extraData
		if (invioCLS != null) {
			de.getExtraMetadata().put("invioCLS", Arrays.asList(invioCLS));

		}
		String invioFSE = doc.getInvioFSE();// documentEntry.extraData
		if (invioFSE != null) {
			de.getExtraMetadata().put("invioFSE", Arrays.asList(invioFSE));

		}

		/*
		 * String identificativoRepository = doc.getIdentificativoRepository();//
		 * documentEntry.extraData if (importoTicketPagato != null) {
		 * de.getExtraMetadata().put("identificativoRepository",
		 * Arrays.asList(identificativoRepository));
		 * 
		 * }
		 */

		de.getExtraMetadata().put("tipoAttivitaClinica", Arrays.asList(doc.getTipoAttivitaClinica()));

		de.getExtraMetadata().put("codiceApplicativo", Arrays.asList(richiesta.getCodiceApplicativo()));

		boolean orderAdded = false;
		boolean idEpisodioAdded = false;
		String tipoEpisodio = episodio.getTipoEpisodio();
		if (StringUtils.hasLength(tipoEpisodio)) {
			de.getExtraMetadata().put("tipoEpisodio", Arrays.asList(tipoEpisodio));

			ReferenceId ref = null;

			if (RepoConsts.TIPO_EPISODIO_AMBULATORIO.equalsIgnoreCase(tipoEpisodio)) {
				String idRichiestaCUP = episodio.getIdRichiestaCUP(); // documententry.referenceList

				if (StringUtils.hasLength(idRichiestaCUP)) {

					CXiAssigningAuthority cxi = new CXiAssigningAuthority("",
							RepoConsts.ID_RICHIESTA_CUP_ASSIGNING_AUTH.getUniversalId(),
							RepoConsts.ID_RICHIESTA_CUP_ASSIGNING_AUTH.getUniversalIdType());
					ref = new ReferenceId(idRichiestaCUP, cxi, ReferenceId.ID_TYPE_CODE_ORDER);
					de.getReferenceIdList().add(ref);
					orderAdded = true;

					if (gestioneIdEpisodio) {
						String idEpisodio = Hl7v2Based.render(ref);
						ref = new ReferenceId(idEpisodio,
								new CXiAssigningAuthority("",
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_ENCOUNTER_ID);
						de.getReferenceIdList().add(ref);
						idEpisodioAdded = true;
					}

				}
			}

			else if (RepoConsts.TIPO_EPISODIO_RICOVERO.equalsIgnoreCase(tipoEpisodio)) {
				String numeroNosologico = episodio.getNumeroNosologico(); // documententry.referenceList
																			// ID_TYPE_CODE_ADMISSION
				if (StringUtils.hasLength(numeroNosologico)) {

					ref = new ReferenceId(numeroNosologico,
							new CXiAssigningAuthority("", RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalIdType()),
							RepoConsts.ID_TYPE_CODE_ADMISSION);
					de.getReferenceIdList().add(ref);

					if (!gestioneIdEpisodio) {
						ref = new ReferenceId(numeroNosologico,
								new CXiAssigningAuthority("",
										RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ORDER);
						de.getReferenceIdList().add(ref);
					}

				}
				String idRichiestaOrderEntry = episodio.getIdRichiestaOrderEntry(); // documententry.referenceList
				if (StringUtils.hasLength(idRichiestaOrderEntry)) {
					ref = new ReferenceId(idRichiestaOrderEntry,
							new CXiAssigningAuthority("", RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_CODE_ORDER);
					de.getReferenceIdList().add(ref);

					orderAdded = true;
				}

				if (gestioneIdEpisodio) {
					String idEpisodio = Hl7v2Based.render(ref);
					ref = new ReferenceId(idEpisodio,
							new CXiAssigningAuthority("",
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_ENCOUNTER_ID);
					de.getReferenceIdList().add(ref);
					idEpisodioAdded = true;
				}

			} else if (RepoConsts.TIPO_EPISODIO_PS.equalsIgnoreCase(tipoEpisodio)) {

				String numeroPassaggioPS = episodio.getNumeroPassaggioPS();// documententry.referenceList
				if (StringUtils.hasLength(numeroPassaggioPS)) { // Accession_number_PS
					ref = new ReferenceId(numeroPassaggioPS,
							new CXiAssigningAuthority("", RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_CODE_ACCESSION);
					de.getReferenceIdList().add(ref);

					if (!gestioneIdEpisodio) {
						ref = new ReferenceId(numeroPassaggioPS,
								new CXiAssigningAuthority("",
										RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ORDER);
						de.getReferenceIdList().add(ref);
					}

				}

				String idRichiestaPS = episodio.getIdRichiestaPS();
				if (StringUtils.hasLength(idRichiestaPS)) {
					ref = new ReferenceId(idRichiestaPS,
							new CXiAssigningAuthority("", RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_CODE_ORDER);
					de.getReferenceIdList().add(ref);
					orderAdded = true;
				}

				if (gestioneIdEpisodio) {
					String idEpisodio = Hl7v2Based.render(ref);
					ref = new ReferenceId(idEpisodio,
							new CXiAssigningAuthority("",
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_ENCOUNTER_ID);
					de.getReferenceIdList().add(ref);
					idEpisodioAdded = true;
				}

			}

		}

		if (!orderAdded) {

			ReferenceId ref = new ReferenceId(episodio.getIdEpisodio(),
					new CXiAssigningAuthority("",
							RepoConsts.ID_RICHIESTA_ALTRO_ASSIGNING_AUTH.getUniversalId().replace("X",
									applicazione.getIdSistemaRegionale()),
							RepoConsts.ID_RICHIESTA_ALTRO_ASSIGNING_AUTH.getUniversalIdType()),
					ReferenceId.ID_TYPE_CODE_ORDER);
			de.getReferenceIdList().add(ref);
			if (!idEpisodioAdded) {

				String idEpisodio = applicazione.getIdSistemaRegionale() + "-" + episodio.getIdEpisodio();
				ref = new ReferenceId(idEpisodio,
						new CXiAssigningAuthority("", RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
								RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
						ReferenceId.ID_TYPE_ENCOUNTER_ID);
				de.getReferenceIdList().add(ref);

			}
		}

		String tipoEpisodioOriginanteRichiesta = episodio.getTipoEpisodioOriginanteRichiesta();
		if (StringUtils.hasLength(tipoEpisodioOriginanteRichiesta)) {
			de.getExtraMetadata().put("tipoEpisodioOriginanteRichiesta",
					Arrays.asList(tipoEpisodioOriginanteRichiesta));

			String idEpisodioOriginanteRichiesta = episodio.getIdEpisodioOriginanteRichiesta();
			if (StringUtils.hasLength(idEpisodioOriginanteRichiesta)) {
				if (RepoConsts.TIPO_EPISODIO_RICOVERO.equalsIgnoreCase(tipoEpisodioOriginanteRichiesta)) {

					ReferenceId ref = new ReferenceId(idEpisodioOriginanteRichiesta,
							new CXiAssigningAuthority("", RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalIdType()),
							RepoConsts.ID_TYPE_CODE_ADMISSION);
					de.getReferenceIdList().add(ref);

				} else if (RepoConsts.TIPO_EPISODIO_PS.equalsIgnoreCase(tipoEpisodioOriginanteRichiesta)) {

					ReferenceId ref = new ReferenceId(idEpisodioOriginanteRichiesta,
							new CXiAssigningAuthority("", RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_CODE_ACCESSION);
					de.getReferenceIdList().add(ref);

				}
			}
		}

		Date dataInizio = episodio.getDataInizio();// documententry.serviceStartTime
		if (dataInizio != null) {
			de.setServiceStartTime(DateUtils.getDataPerXds(dataInizio));

			de.setCreationTime(DateUtils.getDataPerXds(dataInizio));
		}

		Date dataFine = episodio.getDataFine();// documententry.serviceStopTime

		if (dataFine != null) {
			de.setServiceStopTime(DateUtils.getDataPerXds(dataFine));
		}

		List<String> codiciAccettazione = new ArrayList<>();

		String codiceLuogoAccettazione = episodio.getCodiceLuogoAccettazione();
		if (StringUtils.hasLength(codiceLuogoAccettazione)) {
			codiciAccettazione.add(codiceLuogoAccettazione);
		}

		if (!codiciAccettazione.isEmpty()) {
			de.getExtraMetadata().put("codiceLuogoAccettazione", codiciAccettazione);
		}

		String codiceLuogoDimissione = episodio.getCodiceLuogoDimissione(); // documententry.extrametadata
		if (StringUtils.hasLength(codiceLuogoDimissione)) {

			codiciAccettazione = new ArrayList<>();
			codiciAccettazione.add(codiceLuogoDimissione);

			de.getExtraMetadata().put("codiceLuogoDimissione", codiciAccettazione);

		}
		String codiceLuogoCP = episodio.getCodiceLuogoCP(); // documententry.extrametadata OPZ
		if (StringUtils.hasLength(codiceLuogoCP)) {
			de.getExtraMetadata().put("codiceLuogoCP", Arrays.asList(codiceLuogoAccettazione));

		}

		Identifiable cf = new Identifiable(paziente.getCodiceFiscale(), RepoConsts.COD_FISC_ASSIGNING_AUTH);
		de.setSourcePatientId(cf);
		de.setPatientId(cf);
		List<Identifiable> list = new ArrayList<>();
		list.add(cf);
		if (paziente.getIdAura() != null) {
			Identifiable idAura = new Identifiable(paziente.getIdAura(), RepoConsts.ID_AURA_ASSIGNING_AUTH);
			list.add(idAura);
		}
		de.setSourcePatientIdList(list);

		it.csi.gatefire.common.model.repository.PatientInfo patientInfo = fillPatientInfo(paziente);
		de.setExtSourcePatientInfo(patientInfo);

		// PRESTAZIONI
		if (richiesta.getPrestazione() != null && !richiesta.getPrestazione().isEmpty()) {
			de.getExtraMetadata().put("brancaFSE",
					Arrays.asList(richiesta.getPrestazione().get(0).getCodiceBrancaRegionale()));

			for (Prestazione prestazione : richiesta.getPrestazione()) {

				Code code = fillCode(prestazione.getCodiceCatalogoRegionalePrestazione(),
						map.get(RepoConsts.CATALOGO_REGIONE_PIEMONTE));
				if (code == null) {
					code = new Code(prestazione.getCodiceCatalogoRegionalePrestazione(),
							new LocalizedString(prestazione.getCodiceCatalogoRegionalePrestazione()),
							RepoConsts.CODICE_CATALOGO_REGIONE_PIEMONTE_CODING_SCHEME);
				}

				de.getEventCodeList().add(code);
			}
		}

		return de;

	}

	public static void updateDocumentEntry(it.csi.gatefire.common.model.repository.DocumentEntry de,
			ITIMetadata itiMetadata, Map<String, List<GatefireDXdscode>> map, GatefireDRepository repository,
			GatefireDApplicazione applicazione) {

		String consentType = repository.getGestioneConsensi();
		boolean gestioneIdEpisodio = false;

		if (repository.getGestioneIdEpisodio() != null) {
			gestioneIdEpisodio = repository.getGestioneIdEpisodio();
		}

		Documento doc = itiMetadata.getDocumento();
		Richiesta richiesta = itiMetadata.getRichiesta();
		Episodio episodio = itiMetadata.getEpisodio();
		Paziente paziente = itiMetadata.getPaziente();

		de.setEntryUuid("Document01");

		if (doc != null) {

			Author medicoValidatore = null;
			if (doc.getTipologiaStrutturaProdDoc() != null) {
				de.setHealthcareFacilityTypeCode(fillCode(doc.getTipologiaStrutturaProdDoc(),
						map.get(RepoConsts.HEALTHCARE_FACILITY_TYPE_CODE)));
			}

			if (doc.getTipologiaDocumentoAlto() != null) {
				de.setClassCode(fillCode(doc.getTipologiaDocumentoAlto(), map.get(RepoConsts.CLASS_CODE)));
			}
			if (doc.getTipologiaDocumentoMedio() != null) {
				de.setTypeCode(fillCode(doc.getTipologiaDocumentoMedio(), map.get(RepoConsts.TYPE_CODE)));
			}
			if (doc.getFormatCode() != null) {
				de.setFormatCode(fillCode(doc.getFormatCode(), map.get(RepoConsts.FORMAT_CODE)));
			}
			if (doc.getAssettoOrganizzativo() != null) {
				de.setPracticeSettingCode(
						fillCode(doc.getAssettoOrganizzativo(), map.get(RepoConsts.PRACTICE_SETTING_CODE)));
			}
			if (doc.getMimeType() != null) {
				de.setMimeType(doc.getMimeType());
			}
			if (richiesta != null && StringUtils.hasLength(richiesta.getCodiceAzienda())
					&& StringUtils.hasLength(doc.getAssettoOrganizzativo())) {

				if (doc.getMedicoRedattore() != null && richiesta.getUtente() != null) {
					List<Author> mediciRedattore = fillAuthors(doc.getMedicoRedattore(), richiesta.getCodiceAzienda(),
							richiesta.getUtente().getCodiceStruttura(), doc.getAssettoOrganizzativo(), map, false);
					if (!mediciRedattore.isEmpty()) {

						de.getAuthors().addAll(mediciRedattore);
					}
				}
				if (doc.getMedicoValidatore() != null) {
					medicoValidatore = fillAuthor(doc.getMedicoValidatore(), richiesta.getCodiceAzienda(), null,
							doc.getAssettoOrganizzativo(), map, true);

					if (medicoValidatore != null) {

						de.setLegalAuthenticator(medicoValidatore.getAuthorPerson());

					}
				}

			}

			List<String> nre = doc.getNre(); // documententry.referenceList idtypecode.UniqueId_reference

			if (nre != null) {
				for (String idnre : nre) {
					ReferenceId ref = new ReferenceId(idnre,
							new CXiAssigningAuthority("", RepoConsts.ID_RICETTA_DEMA_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_RICETTA_DEMA_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_CODE_UNIQUE_ID);

					if (!de.getReferenceIdList().contains(ref)) {
						de.getReferenceIdList().add(ref);
					}
				}
			}

			List<String> accNumbList = doc.getAccessionNumber();
			if (accNumbList != null) {
				for (String accessionNumber : accNumbList) {
					ReferenceId ref = new ReferenceId(accessionNumber,
							new CXiAssigningAuthority("", RepoConsts.ID_IMMAGINI_RIS_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_IMMAGINI_RIS_ASSIGNING_AUTH.getUniversalIdType()),
							RepoConsts.ID_TYPE_CODE_ACCESSION_RIS);
					if (!de.getReferenceIdList().contains(ref)) {
						de.getReferenceIdList().add(ref);
					}
				}

			}

			var extraM = de.getExtraMetadata();

			if (extraM == null) {
				extraM = new HashMap<String, List<String>>();
				de.setExtraMetadata(extraM);
			}

			Boolean firmatoDigitalmente = doc.getFirmatoDigitalmente(); // documentEntry.extraData
			if (firmatoDigitalmente != null) {
				de.getExtraMetadata().put("firmatoDigitalmente", Arrays.asList(firmatoDigitalmente.toString()));
			}

			if (richiesta.getUtente() != null) {
				String codiceMatricola = richiesta.getUtente().getCodiceMatricola();

				if (StringUtils.hasLength(codiceMatricola)) {
					if (!RepoConsts.TIPO_REPOSITORY_DEDALUS.equalsIgnoreCase(repository.getTipoRepository())) {
						de.getExtraMetadata().put("codiceMatricola", Arrays.asList(codiceMatricola));
					}
				}
			}

			boolean slot = (consentType == null) || RepoConsts.CONSENT_TYPE_SLOT.equalsIgnoreCase(consentType)
					|| RepoConsts.CONSENT_TYPE_ALL.equalsIgnoreCase(consentType);

			boolean consentPlatform = (consentType == null)
					|| RepoConsts.CONSENT_TYPE_PLATFORM.equalsIgnoreCase(consentType)
					|| RepoConsts.CONSENT_TYPE_ALL.equalsIgnoreCase(consentType);

			Date dataOraFirmaDocumento = doc.getDataOraFirmaDocumento(); // documentEntry.extraData
																			// documentEntry.legalAuthenticatorTime
			if (dataOraFirmaDocumento != null) {

				if (slot) {
					de.getExtraMetadata().put("dataOraFirmaDocumento", Arrays
							.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataOraFirmaDocumento))));
				}
				if (medicoValidatore != null && consentPlatform) {

					de.getExtraMetadata().put("legalAuthenticatorTime", Arrays
							.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataOraFirmaDocumento))));
				}

			}

			TipoFirma tipoFirma = doc.getTipoFirma();// documentEntry.extraData
			if (tipoFirma != null) {
				de.getExtraMetadata().put("tipoFirma", Arrays.asList(tipoFirma.getTipo()));

			}
			String codicePin = doc.getCodicePin();// documentEntry.extraData
			if (codicePin != null) {
				de.getExtraMetadata().put("codicePIN", Arrays.asList(codicePin));

			}

			String codiceDocumentoScaricabile = doc.getCodiceDocumentoScaricabile();// documentEntry.extraData
			if (codiceDocumentoScaricabile != null) {
				de.getExtraMetadata().put("codiceDocumentoScaricabile", Arrays.asList(codiceDocumentoScaricabile));

			}

			Date dataDisponibilitaReferto = doc.getDataDisponibilitaReferto(); // documentEntry.extraData
			// documentEntry.legalAuthenticatorTime
			if (dataDisponibilitaReferto != null) {
				de.getExtraMetadata().put("dataDisponibilitaReferto", Arrays
						.asList(DateUtils.getDataPerXdsNotimeZone(DateUtils.dateToUTC(dataDisponibilitaReferto))));
			}

			String pagatoTicket = doc.getPagatoTicket();// documentEntry.extraData
			if (StringUtils.hasLength(pagatoTicket)) {
				de.getExtraMetadata().put("pagatoTicket", Arrays.asList(pagatoTicket));

			}
			String importoTicketDaPagare = doc.getImportoTicketDaPagare();// documentEntry.extraData
			if (StringUtils.hasLength(importoTicketDaPagare)) {
				de.getExtraMetadata().put("importoTicketDaPagare", Arrays.asList(importoTicketDaPagare));

			}
			String importoTicketPagato = doc.getImportoTicketPagato();// documentEntry.extraData
			if (importoTicketPagato != null) {
				de.getExtraMetadata().put("importoTicketPagato", Arrays.asList(importoTicketPagato));

			}

			String privacyDocumento = doc.getPrivacyDocumento();
			String oscuraScaricoCittadino = doc.getOscuraScaricoCittadino();
			Boolean scaricabileDalCittadino = doc.getScaricabileDalCittadino();
			Boolean soggettoALeggiSpeciali = doc.getSoggettoALeggiSpeciali();
			Boolean oscuramentoDocDSE = doc.getOscuramentoDocDSE();

			String livelloRiservatezza = doc.getLivelloRiservatezza();

			if (livelloRiservatezza != null) {
				de.getConfidentialityCodes().clear();
				de.getConfidentialityCodes()
						.add(fillCode(livelloRiservatezza, map.get(RepoConsts.CONFIDENTIALITY_CODE)));
			}

			if (consentPlatform) {

				if ("1".equalsIgnoreCase(privacyDocumento)) {
					de.getEventCodeList().add(fillCode(RepoConsts.P99, map.get(RepoConsts.EVENT_CODE_LIST)));
				} else if ("0".equalsIgnoreCase(privacyDocumento)) {
					Code code = fillCode(RepoConsts.P99, map.get(RepoConsts.EVENT_CODE_LIST));
					int index = de.getEventCodeList().indexOf(code);
					if (index != -1) {
						de.getEventCodeList().remove(index);
					}

				}

				if ("S".equalsIgnoreCase(oscuraScaricoCittadino)) {
					de.getEventCodeList().add(fillCode(RepoConsts.P98, map.get(RepoConsts.EVENT_CODE_LIST)));
				} else if ("N".equalsIgnoreCase(oscuraScaricoCittadino)) {
					Code code = fillCode(RepoConsts.P98, map.get(RepoConsts.EVENT_CODE_LIST));
					int index = de.getEventCodeList().indexOf(code);
					if (index != -1) {
						de.getEventCodeList().remove(index);
					}
				}

				if (oscuramentoDocDSE != null) {
					if (oscuramentoDocDSE.booleanValue()) {
						de.getEventCodeList().add(fillCode(RepoConsts.D99, map.get(RepoConsts.EVENT_CODE_LIST)));
					} else {
						Code code = fillCode(RepoConsts.D99, map.get(RepoConsts.EVENT_CODE_LIST));
						int index = de.getEventCodeList().indexOf(code);
						if (index != -1) {
							de.getEventCodeList().remove(index);
						}
					}
				}

				if (scaricabileDalCittadino != null) {
					if (!scaricabileDalCittadino.booleanValue()) {
						de.getEventCodeList().add(fillCode(RepoConsts.ROL99, map.get(RepoConsts.EVENT_CODE_LIST)));
					} else {
						Code code = fillCode(RepoConsts.ROL99, map.get(RepoConsts.EVENT_CODE_LIST));
						int index = de.getEventCodeList().indexOf(code);
						if (index != -1) {
							de.getEventCodeList().remove(index);
						}
					}

				}

			}

			if (slot) {
				if (StringUtils.hasLength(privacyDocumento)) {
					de.getExtraMetadata().put("privacyDocumento", Arrays.asList(privacyDocumento));
				}

				if (StringUtils.hasLength(oscuraScaricoCittadino)) {
					de.getExtraMetadata().put("oscuraScaricoCittadino", Arrays.asList(oscuraScaricoCittadino));
				}

				if (scaricabileDalCittadino != null) {
					de.getExtraMetadata().put("scaricabileDalCittadino",
							Arrays.asList(scaricabileDalCittadino.toString()));
				}
			}

			if (soggettoALeggiSpeciali != null) {
				if (slot) {
					de.getExtraMetadata().put("soggettoALeggiSpeciali",
							Arrays.asList(soggettoALeggiSpeciali.toString()));
				}
				if (soggettoALeggiSpeciali.booleanValue()) {
					if (!ConfidentialityCode.V.toString().equalsIgnoreCase(livelloRiservatezza)) {
						de.getConfidentialityCodes().add(
								fillCode(ConfidentialityCode.V.toString(), map.get(RepoConsts.CONFIDENTIALITY_CODE)));
					}
				}
			}

			String invioCLS = doc.getInvioCLS();// documentEntry.extraData
			if (invioCLS != null) {
				de.getExtraMetadata().put("invioCLS", Arrays.asList(invioCLS));

			}
			String invioFSE = doc.getInvioFSE();// documentEntry.extraData
			if (invioFSE != null) {
				de.getExtraMetadata().put("invioFSE", Arrays.asList(invioFSE));

			}
			if (doc.getTipoAttivitaClinica() != null) {
				de.getExtraMetadata().put("tipoAttivitaClinica", Arrays.asList(doc.getTipoAttivitaClinica()));
			}
			if (richiesta != null && richiesta.getCodiceApplicativo() != null) {
				de.getExtraMetadata().put("codiceApplicativo", Arrays.asList(richiesta.getCodiceApplicativo()));
			}
		}

		if (episodio != null) {
			boolean orderAdded = false;
			boolean idEpisodioAdded = false;
			String tipoEpisodio = episodio.getTipoEpisodio();
			if (StringUtils.hasLength(tipoEpisodio)) {
				de.getExtraMetadata().put("tipoEpisodio", Arrays.asList(tipoEpisodio));

				ReferenceId ref = null;

				if (RepoConsts.TIPO_EPISODIO_AMBULATORIO.equalsIgnoreCase(tipoEpisodio)) {
					String idRichiestaCUP = episodio.getIdRichiestaCUP(); // documententry.referenceList

					if (StringUtils.hasLength(idRichiestaCUP)) {

						CXiAssigningAuthority cxi = new CXiAssigningAuthority("",
								RepoConsts.ID_RICHIESTA_CUP_ASSIGNING_AUTH.getUniversalId(),
								RepoConsts.ID_RICHIESTA_CUP_ASSIGNING_AUTH.getUniversalIdType());
						ref = new ReferenceId(idRichiestaCUP, cxi, ReferenceId.ID_TYPE_CODE_ORDER);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}
						orderAdded = true;

						if (gestioneIdEpisodio) {
							String idEpisodio = Hl7v2Based.render(ref);
							ref = new ReferenceId(idEpisodio,
									new CXiAssigningAuthority("",
											RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
											RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
									ReferenceId.ID_TYPE_ENCOUNTER_ID);
							if (!de.getReferenceIdList().contains(ref)) {
								de.getReferenceIdList().add(ref);
							}
							idEpisodioAdded = true;
						}

					}
				}

				else if (RepoConsts.TIPO_EPISODIO_RICOVERO.equalsIgnoreCase(tipoEpisodio)) {
					String numeroNosologico = episodio.getNumeroNosologico(); // documententry.referenceList
																				// ID_TYPE_CODE_ADMISSION
					if (StringUtils.hasLength(numeroNosologico)) {

						ref = new ReferenceId(numeroNosologico,
								new CXiAssigningAuthority("",
										RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalIdType()),
								RepoConsts.ID_TYPE_CODE_ADMISSION);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}

						if (!gestioneIdEpisodio) {
							ref = new ReferenceId(numeroNosologico,
									new CXiAssigningAuthority("",
											RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalId(),
											RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalIdType()),
									ReferenceId.ID_TYPE_CODE_ORDER);
							if (!de.getReferenceIdList().contains(ref)) {
								de.getReferenceIdList().add(ref);
							}
						}

					}
					String idRichiestaOrderEntry = episodio.getIdRichiestaOrderEntry(); // documententry.referenceList
					if (StringUtils.hasLength(idRichiestaOrderEntry)) {
						ref = new ReferenceId(idRichiestaOrderEntry,
								new CXiAssigningAuthority("",
										RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_RICHIESTA_OD_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ORDER);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}

						orderAdded = true;
					}

					if (gestioneIdEpisodio) {
						String idEpisodio = Hl7v2Based.render(ref);
						ref = new ReferenceId(idEpisodio,
								new CXiAssigningAuthority("",
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_ENCOUNTER_ID);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}
						idEpisodioAdded = true;
					}

				} else if (RepoConsts.TIPO_EPISODIO_PS.equalsIgnoreCase(tipoEpisodio)) {

					String numeroPassaggioPS = episodio.getNumeroPassaggioPS();// documententry.referenceList
					if (StringUtils.hasLength(numeroPassaggioPS)) { // Accession_number_PS
						ref = new ReferenceId(numeroPassaggioPS,
								new CXiAssigningAuthority("", RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ACCESSION);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}

						if (!gestioneIdEpisodio) {
							ref = new ReferenceId(numeroPassaggioPS,
									new CXiAssigningAuthority("",
											RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalId(),
											RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalIdType()),
									ReferenceId.ID_TYPE_CODE_ORDER);
							if (!de.getReferenceIdList().contains(ref)) {
								de.getReferenceIdList().add(ref);
							}
						}

					}

					String idRichiestaPS = episodio.getIdRichiestaPS();
					if (StringUtils.hasLength(idRichiestaPS)) {
						ref = new ReferenceId(idRichiestaPS,
								new CXiAssigningAuthority("",
										RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_RICHIESTA_PS_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ORDER);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}
						orderAdded = true;
					}

					if (gestioneIdEpisodio) {
						String idEpisodio = Hl7v2Based.render(ref);
						ref = new ReferenceId(idEpisodio,
								new CXiAssigningAuthority("",
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_ENCOUNTER_ID);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}
						idEpisodioAdded = true;
					}

				}

			}

			if (!orderAdded) {

				ReferenceId ref = new ReferenceId(episodio.getIdEpisodio(),
						new CXiAssigningAuthority("",
								RepoConsts.ID_RICHIESTA_ALTRO_ASSIGNING_AUTH.getUniversalId().replace("X",
										applicazione.getIdSistemaRegionale()),
								RepoConsts.ID_RICHIESTA_ALTRO_ASSIGNING_AUTH.getUniversalIdType()),
						ReferenceId.ID_TYPE_CODE_ORDER);
				if (!de.getReferenceIdList().contains(ref)) {
					de.getReferenceIdList().add(ref);
				}
				if (!idEpisodioAdded) {

					String idEpisodio = applicazione.getIdSistemaRegionale() + "-" + episodio.getIdEpisodio();
					ref = new ReferenceId(idEpisodio,
							new CXiAssigningAuthority("",
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalId(),
									RepoConsts.ID_SESSIONE_IMPIANTO_ASSIGNING_AUTH.getUniversalIdType()),
							ReferenceId.ID_TYPE_ENCOUNTER_ID);
					if (!de.getReferenceIdList().contains(ref)) {
						de.getReferenceIdList().add(ref);
					}

				}
			}

			String tipoEpisodioOriginanteRichiesta = episodio.getTipoEpisodioOriginanteRichiesta();
			if (StringUtils.hasLength(tipoEpisodioOriginanteRichiesta)) {
				de.getExtraMetadata().put("tipoEpisodioOriginanteRichiesta",
						Arrays.asList(tipoEpisodioOriginanteRichiesta));

				String idEpisodioOriginanteRichiesta = episodio.getIdEpisodioOriginanteRichiesta();
				if (StringUtils.hasLength(idEpisodioOriginanteRichiesta)) {
					if (RepoConsts.TIPO_EPISODIO_RICOVERO.equalsIgnoreCase(tipoEpisodioOriginanteRichiesta)) {

						ReferenceId ref = new ReferenceId(idEpisodioOriginanteRichiesta,
								new CXiAssigningAuthority("",
										RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_NUM_NOSOLOGICO_ASSIGNING_AUTH.getUniversalIdType()),
								RepoConsts.ID_TYPE_CODE_ADMISSION);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}

					} else if (RepoConsts.TIPO_EPISODIO_PS.equalsIgnoreCase(tipoEpisodioOriginanteRichiesta)) {

						ReferenceId ref = new ReferenceId(idEpisodioOriginanteRichiesta,
								new CXiAssigningAuthority("", RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalId(),
										RepoConsts.ID_EPISODIO_PS_ASSIGNING_AUTH.getUniversalIdType()),
								ReferenceId.ID_TYPE_CODE_ACCESSION);
						if (!de.getReferenceIdList().contains(ref)) {
							de.getReferenceIdList().add(ref);
						}

					}
				}
			}

			Date dataInizio = episodio.getDataInizio();// documententry.serviceStartTime
			if (dataInizio != null) {
				de.setServiceStartTime(DateUtils.getDataPerXds(dataInizio));

				de.setCreationTime(DateUtils.getDataPerXds(dataInizio));
			}

			Date dataFine = episodio.getDataFine();// documententry.serviceStopTime

			if (dataFine != null) {
				de.setServiceStopTime(DateUtils.getDataPerXds(dataFine));
			}

			List<String> codiciAccettazione = new ArrayList<>();

			String codiceLuogoAccettazione = episodio.getCodiceLuogoAccettazione();
			if (StringUtils.hasLength(codiceLuogoAccettazione)) {
				codiciAccettazione.add(codiceLuogoAccettazione);
			}

			if (!codiciAccettazione.isEmpty()) {
				de.getExtraMetadata().put("codiceLuogoAccettazione", codiciAccettazione);
			}

			String codiceLuogoDimissione = episodio.getCodiceLuogoDimissione(); // documententry.extrametadata
			if (StringUtils.hasLength(codiceLuogoDimissione)) {

				codiciAccettazione = new ArrayList<>();
				codiciAccettazione.add(codiceLuogoDimissione);

				de.getExtraMetadata().put("codiceLuogoDimissione", codiciAccettazione);

			}
			String codiceLuogoCP = episodio.getCodiceLuogoCP(); // documententry.extrametadata OPZ
			if (StringUtils.hasLength(codiceLuogoCP)) {
				de.getExtraMetadata().put("codiceLuogoCP", Arrays.asList(codiceLuogoAccettazione));

			}
		}

		if (paziente != null) {
			/*
			 * Identifiable cf = new Identifiable(paziente.getCodiceFiscale(),
			 * RepoConsts.COD_FISC_ASSIGNING_AUTH); de.setSourcePatientId(cf);
			 * de.setPatientId(cf); List<Identifiable> list = new ArrayList<>();
			 * list.add(cf); if (paziente.getIdAura() != null) { Identifiable idAura = new
			 * Identifiable(paziente.getIdAura(), RepoConsts.ID_AURA_ASSIGNING_AUTH);
			 * list.add(idAura); } de.setSourcePatientIdList(list);
			 * 
			 * it.csi.gatefire.common.model.repository.PatientInfo patientInfo =
			 * fillPatientInfo(paziente); de.setExtSourcePatientInfo(patientInfo);
			 */
		}
		if (richiesta != null) {
			// PRESTAZIONI
			if (richiesta.getPrestazione() != null && !richiesta.getPrestazione().isEmpty()) {
				de.getExtraMetadata().put("brancaFSE",
						Arrays.asList(richiesta.getPrestazione().get(0).getCodiceBrancaRegionale()));

				for (Prestazione prestazione : richiesta.getPrestazione()) {

					Code code = fillCode(prestazione.getCodiceCatalogoRegionalePrestazione(),
							map.get(RepoConsts.CATALOGO_REGIONE_PIEMONTE));
					if (code == null) {
						code = new Code(prestazione.getCodiceCatalogoRegionalePrestazione(),
								new LocalizedString(prestazione.getCodiceCatalogoRegionalePrestazione()),
								RepoConsts.CODICE_CATALOGO_REGIONE_PIEMONTE_CODING_SCHEME);
					}

					de.getEventCodeList().add(code);
				}
			}
		}
	}

	public static Code fillCode(String code, List<GatefireDXdscode> list) {
		for (GatefireDXdscode xdscode : list) {
			if (xdscode.getCode().equalsIgnoreCase(code)) {

				return new Code(code, new LocalizedString(xdscode.getDisplayName()), xdscode.getCodingScheme());
			}
		}

		return null;

	}

	public static Organization fillOrganization(String code, List<GatefireDXdscode> list, boolean fls11) {
		if (list != null) {
			for (GatefireDXdscode xdscode : list) {
				if (xdscode.getCode().equalsIgnoreCase(code)) {
					if (fls11) {
						return new Organization(xdscode.getDisplayName(), code, RepoConsts.ID_FLS11_ASSIGNING_AUTH);
					} else {
						return new Organization(xdscode.getDisplayName(), code, RepoConsts.ID_STS11_ASSIGNING_AUTH);
					}
				}
			}
		}
		if (!fls11) {
			return new Organization(code, code, RepoConsts.ID_STS11_ASSIGNING_AUTH);
		}
		return null;
	}

	public static it.csi.gatefire.common.model.repository.PatientInfo fillPatientInfo(Paziente paziente) {

		it.csi.gatefire.common.model.repository.PatientInfo patientInfo = new it.csi.gatefire.common.model.repository.PatientInfo();

		if (paziente.getIdAura() != null) {
			Identifiable idAura = new Identifiable(paziente.getIdAura(), RepoConsts.ID_AURA_ASSIGNING_AUTH);

			patientInfo.getIds().add(idAura);
		}
		Identifiable cf = new Identifiable(paziente.getCodiceFiscale(), RepoConsts.COD_FISC_ASSIGNING_AUTH);

		patientInfo.getIds().add(cf);

		Name<XPN> name = new XpnName();
		name.setGivenName(paziente.getNome());
		name.setFamilyName(paziente.getCognome());
		if (StringUtils.hasLength(paziente.getPrefisso())) {
			name.setPrefix(paziente.getPrefisso());
		}
		if (StringUtils.hasLength(paziente.getSecondoNome())) {
			name.setSecondAndFurtherGivenNames(paziente.getSecondoNome());
		}

		patientInfo.getNames().add(name);

		patientInfo.setDateOfBirth(DateUtils.getDataPerXdsShort(paziente.getDataDiNascita()));
		patientInfo.setGender(paziente.getSesso());
		patientInfo.setBirthCountry(paziente.getCodiceNazioneDiNascita());

		Indirizzo indirizzoResidenza = paziente.getIndirizzoResidenza();
		if (indirizzoResidenza != null) {
			Address addressRes = new Address();
			addressRes.setStreetAddress(indirizzoResidenza.getIndirizzo());
			addressRes.setCity(indirizzoResidenza.getComune());
			addressRes.setCountry(indirizzoResidenza.getCodiceNazione());
			addressRes.setCountyParishCode(indirizzoResidenza.getCodIstatComune());

			try {
				addressRes.getHapiObject().getAddressType().setValue("L");

			} catch (DataTypeException e) {

				e.printStackTrace();
			}
			patientInfo.getAddresses().add(addressRes);
		}

		Address addressNascita = new Address();

		addressNascita.setCity(paziente.getComuneDiNascita());
		addressNascita.setCountry(paziente.getCodiceNazioneDiNascita());
		addressNascita.setCountyParishCode(paziente.getCodIstatComuneDiNascita());

		try {
			addressNascita.getHapiObject().getAddressType().setValue("N");

		} catch (DataTypeException e) {

			e.printStackTrace();
		}

		patientInfo.getAddresses().add(addressNascita);
		return patientInfo;
	}

}
