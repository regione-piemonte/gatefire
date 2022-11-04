/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Documento {

	@NotNull(message = "Campo obbligatorio")
	private String uniqueId;

	@NotNull(message = "Campo obbligatorio")
	private String tipologiaStrutturaProdDoc; // -->documententry.healthcareFacilityTypeCode

	@NotNull(message = "Campo obbligatorio")
	private List<Medico> medicoRedattore;// documententry.author

	private Medico medicoValidatore; // documententry.legalauthenticator

	@NotNull(message = "Campo obbligatorio")
	private String tipologiaDocumentoAlto; // documentEntry.classCode

	@NotNull(message = "Campo obbligatorio")
	private String tipologiaDocumentoMedio;// documentEntry.typeCode

	@NotNull(message = "Campo obbligatorio")
	private String mimeType; // documentEntry. mimetype

	@NotNull(message = "Campo obbligatorio")
	private String formatCode; // documententry.formatCode

	@NotNull(message = "Campo obbligatorio")
	private String assettoOrganizzativo; // documentEntry. PracticeSettingCode

	private List<String> nre; // documententry.referenceList idtypecode.UniqueId_reference

	private List<String> accessionNumber; // documententry.referenceList idtypecode.Accession_number_RIS

	@NotNull(message = "Campo obbligatorio")
	private String invioCLS; // 0 1 documentEntry.extraData (conservazionesostitutiva)

	@NotNull(message = "Campo obbligatorio")
	private String invioFSE; // 0 1 documentEntry.extraData (conservazionesostitutiva)

	private String pagatoTicket;// documentEntry.extraData obbligatorio se invioFSE = 1

	@NotNull(message = "Campo obbligatorio")
	private Date dataOraFirmaDocumento; // documentEntry.extraData documentEntry.legalAuthenticatorTime

	private String importoTicketDaPagare;// documentEntry.extraData
	private String importoTicketPagato;// documentEntry.extraData

	private Boolean firmatoDigitalmente; // documentEntry.extraData
	private TipoFirma tipoFirma;// documentEntry.extraData

	private String codiceDocumentoScaricabile;// documentEntry.extraData

	private Date dataDisponibilitaReferto;// documentEntry.extraData

	private String privacyDocumento; // 0 1 documentEntry.extraData oppure 1 eventcodelist : P99

	private String oscuraScaricoCittadino;// documentEntry.extraData S eventCodeList: ROL99, M eventCodeList: P98

	@NotNull(message = "Campo obbligatorio")
	private Boolean soggettoALeggiSpeciali;// documentEntry.extraData ? confidentialitycode.V

	@NotNull(message = "Campo obbligatorio")
	private Boolean scaricabileDalCittadino;// documentEntry.extraData false ROL99

	private String codicePin;// documentEntry.extraData obbligatorio se scaricabiledacittadino=true

	// private String identificativoRepository; // documentEntry.repositoryUniqueId

	@NotNull(message = "Campo obbligatorio")
	private String livelloRiservatezza; // documentEntry.repositoryUniconfidentialitycode

	@NotNull(message = "Campo obbligatorio")
	private String tipoAttivitaClinica; // submissionSet.contentTypeCode;

	private Boolean oscuramentoDocDSE; // D99

	/*
	 * private String codiceBrancaRegionale;
	 * 
	 * 
	 * 
	 * <prestazione> 0..N <codiceBrancaRegionale>? </codiceBrancaRegionale> 1..1
	 * <codiceCatalogoRegionalePrestazione>? </codiceCatalogoRegionalePrestazione>
	 * 1..1 <dataOra>?</dataOra> 0..1 <quantita>?</quantita> 0..1 </prestazione>
	 * 
	 */

	public String getUniqueId() {
		return uniqueId;
	}

	public String getLivelloRiservatezza() {
		return livelloRiservatezza;
	}

	public void setLivelloRiservatezza(String livelloRiservatezza) {
		this.livelloRiservatezza = livelloRiservatezza;
	}

	public String getTipologiaStrutturaProdDoc() {
		return tipologiaStrutturaProdDoc;
	}

	public void setTipologiaStrutturaProdDoc(String tipologiaStrutturaProdDoc) {
		this.tipologiaStrutturaProdDoc = tipologiaStrutturaProdDoc;
	}

	public List<Medico> getMedicoRedattore() {
		return medicoRedattore;
	}

	public void setMedicoRedattore(List<Medico> medicoRedattore) {
		this.medicoRedattore = medicoRedattore;
	}

	public Medico getMedicoValidatore() {
		return medicoValidatore;
	}

	public void setMedicoValidatore(Medico medicoValidatore) {
		this.medicoValidatore = medicoValidatore;
	}

	public String getTipologiaDocumentoAlto() {
		return tipologiaDocumentoAlto;
	}

	public void setTipologiaDocumentoAlto(String tipologiaDocumentoAlto) {
		this.tipologiaDocumentoAlto = tipologiaDocumentoAlto;
	}

	public String getTipologiaDocumentoMedio() {
		return tipologiaDocumentoMedio;
	}

	public void setTipologiaDocumentoMedio(String tipologiaDocumentoMedio) {
		this.tipologiaDocumentoMedio = tipologiaDocumentoMedio;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}

	public String getAssettoOrganizzativo() {
		return assettoOrganizzativo;
	}

	public void setAssettoOrganizzativo(String assettoOrganizzativo) {
		this.assettoOrganizzativo = assettoOrganizzativo;
	}

	public List<String> getNre() {
		return nre;
	}

	public void setNre(List<String> nre) {
		this.nre = nre;
	}

	public List<String> getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(List<String> accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public Boolean getFirmatoDigitalmente() {
		return firmatoDigitalmente;
	}

	public void setFirmatoDigitalmente(Boolean firmatoDigitalmente) {
		this.firmatoDigitalmente = firmatoDigitalmente;
	}

	public Date getDataOraFirmaDocumento() {
		return dataOraFirmaDocumento;
	}

	public void setDataOraFirmaDocumento(Date dataOraFirmaDocumento) {
		this.dataOraFirmaDocumento = dataOraFirmaDocumento;
	}

	public TipoFirma getTipoFirma() {
		return tipoFirma;
	}

	public void setTipoFirma(TipoFirma tipoFirma) {
		this.tipoFirma = tipoFirma;
	}

	public String getCodicePin() {
		return codicePin;
	}

	public void setCodicePin(String codicePin) {
		this.codicePin = codicePin;
	}

	public String getCodiceDocumentoScaricabile() {
		return codiceDocumentoScaricabile;
	}

	public void setCodiceDocumentoScaricabile(String codiceDocumentoScaricabile) {
		this.codiceDocumentoScaricabile = codiceDocumentoScaricabile;
	}

	public Date getDataDisponibilitaReferto() {
		return dataDisponibilitaReferto;
	}

	public void setDataDisponibilitaReferto(Date dataDisponibilitaReferto) {
		this.dataDisponibilitaReferto = dataDisponibilitaReferto;
	}

	public String getPagatoTicket() {
		return pagatoTicket;
	}

	public void setPagatoTicket(String pagatoTicket) {
		this.pagatoTicket = pagatoTicket;
	}

	public String getImportoTicketDaPagare() {
		return importoTicketDaPagare;
	}

	public void setImportoTicketDaPagare(String importoTicketDaPagare) {
		this.importoTicketDaPagare = importoTicketDaPagare;
	}

	public String getImportoTicketPagato() {
		return importoTicketPagato;
	}

	public void setImportoTicketPagato(String importoTicketPagato) {
		this.importoTicketPagato = importoTicketPagato;
	}

	public String getPrivacyDocumento() {
		return privacyDocumento;
	}

	public void setPrivacyDocumento(String privacyDocumento) {
		this.privacyDocumento = privacyDocumento;
	}

	public String getOscuraScaricoCittadino() {
		return oscuraScaricoCittadino;
	}

	public void setOscuraScaricoCittadino(String oscuraScaricoCittadino) {
		this.oscuraScaricoCittadino = oscuraScaricoCittadino;
	}

	public Boolean getScaricabileDalCittadino() {
		return scaricabileDalCittadino;
	}

	public void setScaricabileDalCittadino(Boolean scaricabileDalCittadino) {
		this.scaricabileDalCittadino = scaricabileDalCittadino;
	}

	public Boolean getSoggettoALeggiSpeciali() {
		return soggettoALeggiSpeciali;
	}

	public void setSoggettoALeggiSpeciali(Boolean soggettoALeggiSpeciali) {
		this.soggettoALeggiSpeciali = soggettoALeggiSpeciali;
	}

	public String getInvioCLS() {
		return invioCLS;
	}

	public void setInvioCLS(String invioCLS) {
		this.invioCLS = invioCLS;
	}

	public String getInvioFSE() {
		return invioFSE;
	}

	public void setInvioFSE(String invioFSE) {
		this.invioFSE = invioFSE;
	}

	/*
	 * public String getIdentificativoRepository() { return
	 * identificativoRepository; }
	 * 
	 * public void setIdentificativoRepository(String identificativoRepository) {
	 * this.identificativoRepository = identificativoRepository; }
	 */
	public String getTipoAttivitaClinica() {
		return tipoAttivitaClinica;
	}

	public void setTipoAttivitaClinica(String tipoAttivitaClinica) {
		this.tipoAttivitaClinica = tipoAttivitaClinica;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Boolean getOscuramentoDocDSE() {
		return oscuramentoDocDSE;
	}

	public void setOscuramentoDocDSE(Boolean oscuramentoDocDSE) {
		this.oscuramentoDocDSE = oscuramentoDocDSE;
	}

}
