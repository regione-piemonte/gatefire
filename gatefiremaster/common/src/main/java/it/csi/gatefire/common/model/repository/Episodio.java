/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Episodio {

	// private boolean gestioneIdEpisodio;

	@NotNull(message = "Campo obbligatorio")
	private String idEpisodio; // documententry.referenceList ID_TYPE_ENCOUNTER_ID

	private String idEpisodioOriginanteRichiesta; // documententry.referenceList obblig se
													// tipoepisodiooriginanterichiesta presente

	private String numeroNosologico; // documententry.referenceList ID_TYPE_CODE_ADMISSION
	private String numeroPassaggioPS;// documententry.referenceList Accession_number_PS
	private String idRichiestaCUP;// documententry.referenceList ORDER
	private String idRichiestaPS;// documententry.referenceList ORDER
	private String idRichiestaOrderEntry;// documententry.referenceList ORDER
	/*
	 * @NotNull(message = "Campo obbligatorio") private String tipoAzione; // TODO
	 * dove? private String recuperoPregresso; // TODO dove?
	 */
	@NotNull(message = "Campo obbligatorio")
	private Date dataInizio;// documententry.serviceStartTime

	private Date dataFine;// documententry.serviceStopTime

	@NotNull(message = "Campo obbligatorio")
	private String codiceLuogoAccettazione; // documententry.extrametadata

	private String codiceLuogoDimissione; // documententry.extrametadata

	private String codiceLuogoCP; // documententry.extrametadata OPZ

	@NotNull(message = "Campo obbligatorio")
	private String tipoEpisodio; // documententry.extrametadata

	private String tipoEpisodioOriginanteRichiesta; // documententry.extrametadata

	public String getIdEpisodio() {
		return idEpisodio;
	}

	public void setIdEpisodio(String idEpisodio) {
		this.idEpisodio = idEpisodio;
	}

	public String getIdEpisodioOriginanteRichiesta() {
		return idEpisodioOriginanteRichiesta;
	}

	public void setIdEpisodioOriginanteRichiesta(String idEpisodioOriginanteRichiesta) {
		this.idEpisodioOriginanteRichiesta = idEpisodioOriginanteRichiesta;
	}

	public String getNumeroNosologico() {
		return numeroNosologico;
	}

	public void setNumeroNosologico(String numeroNosologico) {
		this.numeroNosologico = numeroNosologico;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getCodiceLuogoAccettazione() {
		return codiceLuogoAccettazione;
	}

	public void setCodiceLuogoAccettazione(String codiceLuogoAccettazione) {
		this.codiceLuogoAccettazione = codiceLuogoAccettazione;
	}

	public String getCodiceLuogoDimissione() {
		return codiceLuogoDimissione;
	}

	public void setCodiceLuogoDimissione(String codiceLuogoDimissione) {
		this.codiceLuogoDimissione = codiceLuogoDimissione;
	}

	public String getCodiceLuogoCP() {
		return codiceLuogoCP;
	}

	public void setCodiceLuogoCP(String codiceLuogoCP) {
		this.codiceLuogoCP = codiceLuogoCP;
	}

	public String getTipoEpisodio() {
		return tipoEpisodio;
	}

	public void setTipoEpisodio(String tipoEpisodio) {
		this.tipoEpisodio = tipoEpisodio;
	}

	public String getTipoEpisodioOriginanteRichiesta() {
		return tipoEpisodioOriginanteRichiesta;
	}

	public void setTipoEpisodioOriginanteRichiesta(String tipoEpisodioOriginanteRichiesta) {
		this.tipoEpisodioOriginanteRichiesta = tipoEpisodioOriginanteRichiesta;
	}

	public String getNumeroPassaggioPS() {
		return numeroPassaggioPS;
	}

	public void setNumeroPassaggioPS(String numeroPassaggioPS) {
		this.numeroPassaggioPS = numeroPassaggioPS;
	}

	public String getIdRichiestaCUP() {
		return idRichiestaCUP;
	}

	public void setIdRichiestaCUP(String idRichiestaCUP) {
		this.idRichiestaCUP = idRichiestaCUP;
	}

	public String getIdRichiestaPS() {
		return idRichiestaPS;
	}

	public void setIdRichiestaPS(String idRichiestaPS) {
		this.idRichiestaPS = idRichiestaPS;
	}

	public String getIdRichiestaOrderEntry() {
		return idRichiestaOrderEntry;
	}

	public void setIdRichiestaOrderEntry(String idRichiestaOrderEntry) {
		this.idRichiestaOrderEntry = idRichiestaOrderEntry;
	}

}
