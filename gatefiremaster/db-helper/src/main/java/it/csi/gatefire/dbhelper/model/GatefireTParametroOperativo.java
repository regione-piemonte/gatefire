/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.model;

import java.util.Date;

public class GatefireTParametroOperativo {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.id
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.parametro
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String parametro;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.codice_ca
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String codiceCa;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.codice_funzione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String codiceFunzione;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.codice_applicazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String codiceApplicazione;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.valore
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String valore;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.descrizione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private String descrizione;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.data_inizio_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Date dataInizioValidita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.data_fine_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Date dataFineValidita;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.data_creazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Date dataCreazione;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.data_aggiornamento
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Date dataAggiornamento;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column gatefire.gatefire_t_parametro_operativo.data_cancellazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	private Date dataCancellazione;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.id
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.id
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.id
	 * @param id  the value for gatefire.gatefire_t_parametro_operativo.id
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.parametro
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.parametro
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.parametro
	 * @param parametro  the value for gatefire.gatefire_t_parametro_operativo.parametro
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.codice_ca
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.codice_ca
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getCodiceCa() {
		return codiceCa;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.codice_ca
	 * @param codiceCa  the value for gatefire.gatefire_t_parametro_operativo.codice_ca
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setCodiceCa(String codiceCa) {
		this.codiceCa = codiceCa;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.codice_funzione
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.codice_funzione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getCodiceFunzione() {
		return codiceFunzione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.codice_funzione
	 * @param codiceFunzione  the value for gatefire.gatefire_t_parametro_operativo.codice_funzione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setCodiceFunzione(String codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.codice_applicazione
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.codice_applicazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.codice_applicazione
	 * @param codiceApplicazione  the value for gatefire.gatefire_t_parametro_operativo.codice_applicazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.valore
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.valore
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.valore
	 * @param valore  the value for gatefire.gatefire_t_parametro_operativo.valore
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.descrizione
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.descrizione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.descrizione
	 * @param descrizione  the value for gatefire.gatefire_t_parametro_operativo.descrizione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.data_inizio_validita
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.data_inizio_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.data_inizio_validita
	 * @param dataInizioValidita  the value for gatefire.gatefire_t_parametro_operativo.data_inizio_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.data_fine_validita
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.data_fine_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.data_fine_validita
	 * @param dataFineValidita  the value for gatefire.gatefire_t_parametro_operativo.data_fine_validita
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.data_creazione
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.data_creazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Date getDataCreazione() {
		return dataCreazione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.data_creazione
	 * @param dataCreazione  the value for gatefire.gatefire_t_parametro_operativo.data_creazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.data_aggiornamento
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.data_aggiornamento
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.data_aggiornamento
	 * @param dataAggiornamento  the value for gatefire.gatefire_t_parametro_operativo.data_aggiornamento
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column gatefire.gatefire_t_parametro_operativo.data_cancellazione
	 * @return  the value of gatefire.gatefire_t_parametro_operativo.data_cancellazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public Date getDataCancellazione() {
		return dataCancellazione;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column gatefire.gatefire_t_parametro_operativo.data_cancellazione
	 * @param dataCancellazione  the value for gatefire.gatefire_t_parametro_operativo.data_cancellazione
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	public void setDataCancellazione(Date dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}

	@Override
	public String toString() {
		return "GatefireTParametroOperativo [id=" + id + ", parametro=" + parametro + ", codiceCa=" + codiceCa
				+ ", codiceFunzione=" + codiceFunzione + ", codiceApplicazione=" + codiceApplicazione + ", valore="
				+ valore + ", descrizione=" + descrizione + ", dataCreazione=" + dataCreazione + ", dataAggiornamento="
				+ dataAggiornamento + "]";
	}
}