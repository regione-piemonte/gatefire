package it.csi.gatefire.dbhelper.model;

import java.util.Date;

public class GatefireREventoEventoPadre extends GatefireREventoEventoPadreKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gatefire.gatefire_r_evento_evento_padre.data_creazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    private Date dataCreazione;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gatefire.gatefire_r_evento_evento_padre.data_aggiornamento
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    private Date dataAggiornamento;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gatefire.gatefire_r_evento_evento_padre.data_cancellazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    private Date dataCancellazione;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gatefire.gatefire_r_evento_evento_padre.data_creazione
     *
     * @return the value of gatefire.gatefire_r_evento_evento_padre.data_creazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public Date getDataCreazione() {
        return dataCreazione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gatefire.gatefire_r_evento_evento_padre.data_creazione
     *
     * @param dataCreazione the value for gatefire.gatefire_r_evento_evento_padre.data_creazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gatefire.gatefire_r_evento_evento_padre.data_aggiornamento
     *
     * @return the value of gatefire.gatefire_r_evento_evento_padre.data_aggiornamento
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gatefire.gatefire_r_evento_evento_padre.data_aggiornamento
     *
     * @param dataAggiornamento the value for gatefire.gatefire_r_evento_evento_padre.data_aggiornamento
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gatefire.gatefire_r_evento_evento_padre.data_cancellazione
     *
     * @return the value of gatefire.gatefire_r_evento_evento_padre.data_cancellazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public Date getDataCancellazione() {
        return dataCancellazione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gatefire.gatefire_r_evento_evento_padre.data_cancellazione
     *
     * @param dataCancellazione the value for gatefire.gatefire_r_evento_evento_padre.data_cancellazione
     *
     * @mbg.generated Wed Nov 25 11:01:29 CET 2020
     */
    public void setDataCancellazione(Date dataCancellazione) {
        this.dataCancellazione = dataCancellazione;
    }
}