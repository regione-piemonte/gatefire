/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.consts;

public class GatefireConsts {

	private GatefireConsts() {
		super();
	}

	public static final String SISTEMA_GATEWAY = "GW";

	public static final String CODICE_OPERAZIONE_CA = "CA";
	public static final String CODICE_OPERAZIONE_REPOSITORY = "REP";

	/* funzione */
	public static final String FUNZ_FIRMA_REMOTA = "FIRMA_REMOTA";
	public static final String FUNZ_FIRMA_AUTOMATICA = "FIRMA_AUTOMATICA";

	public static final String FUNZ_FIRMA_PADES_REMOTA = "FIRMA_PADES_REMOTA";
	public static final String FUNZ_FIRMA_PADES_AUTOMATICA = "FIRMA_PADES_AUTOMATICA";
	public static final String FUNZ_FIRMA_PADES_MASSIVA_REMOTA = "FIRMA_PADES_MASSIVA_REMOTA";
	public static final String FUNZ_FIRMA_PADES_MASSIVA_AUTOMATICA = "FIRMA_PADES_MASSIVA_AUTOMATICA";

	public static final String FUNZ_FIRMA_CADES = "FIRMA_CADES";
	public static final String FUNZ_FIRMA_CADES_MASSIVA = "FIRMA_CADES_MASSIVA";
	public static final String FUNZ_FIRMA_CADES_CONG = "FIRMA_CADES_CONG";
	public static final String FUNZ_FIRMA_VERIFICA = "FIRMA_VERIFICA";
	public static final String FUNZ_MARCA_TEMPORALE = "MARCA_TEMPORALE";
	public static final String FUNZ_MARCA_VERIFICA = "MARCA_VERIFICA";
	public static final String FUNZ_VERIFICA_CERT_UTENTE = "VERIFICA_CERT_UTENTE";
	public static final String FUNZ_RAGGIUNGIBILITA_CA = "RAGGIUNGIBILITA_CA";
	public static final String FUNZ_SESSIONE_APERTURA = "SESSIONE_APERTURA";
	public static final String FUNZ_SESSIONE_CHIUSURA = "SESSIONE_CHIUSURA";
	public static final String FUNZ_ARCHIVIA_FILE = "ARCHIVIA_FILE";
	public static final String FUNZ_RICHIESTA_OTP = "RICHIESTA_OTP";

	/* TIPO EVT */
	public static final String EVT_TIPO_FIRMA_PADES_REMOTA = "FIRMA_PADES_REMOTA";
	public static final String EVT_TIPO_FIRMA_PADES_AUTOMATICA = "FIRMA_PADES_AUTOMATICA";
	public static final String EVT_TIPO_FIRMA_PADES_MASSIVA_REMOTA = "FIRMA_PADES_MASSIVA_REMOTA";
	public static final String EVT_TIPO_FIRMA_PADES_MASSIVA_AUTOMATICA = "FIRMA_PADES_MASSIVA_AUTOMATICA";

	public static final String EVT_TIPO_FIRMA_CADES = "FIRMA_CADES";
	public static final String EVT_TIPO_FIRMA_CADES_MASSIVA = "FIRMA_CADES_MASSIVA";
	public static final String EVT_TIPO_FIRMA_CADES_CONG = "FIRMA_CADES_CONG";
	public static final String EVT_TIPO_FIRMA_VERIFICA = "FIRMA_VERIFICA";
	public static final String EVT_TIPO_MARCA_TEMPORALE = "MARCA_TEMPORALE";
	public static final String EVT_TIPO_MARCA_VERIFICA = "MARCA_VERIFICA";
	public static final String EVT_TIPO_VERIFICA_CERT_UTENTE = "VERIFICA_CERT_UTENTE";
	public static final String EVT_TIPO_RAGGIUNGIBILITA_CA = "RAGGIUNGIBILITA_CA";
	public static final String EVT_TIPO_SESSIONE_APERTURA = "SESSIONE_APERTURA";
	public static final String EVT_TIPO_SESSIONE_CHIUSURA = "SESSIONE_CHIUSURA";
	public static final String EVT_TIPO_RICHIESTA_OTP = "RICHIESTA_OTP";
	public static final String EVT_TIPO_ARCHIVIA_FILE = "ARCHIVIA_FILE";
	public static final String EVT_TIPO_RECUPERA_FILE = "RECUPERA_FILE";
	public static final String EVT_TIPO_MODIFICA_METADATI = "MODIFICA_METADATI";
	public static final String EVT_TIPO_CANCELLA_FILE_ARCH = "CANCELLA_FILE_ARCH";
	public static final String EVT_TIPO_ANNULLA_FILE_ARCH = "ANNULLA_FILE_ARCH";
	public static final String EVT_TIPO_SOSTITUISCI_FILE_ARCH = "SOSTITUISCI_FILE_ARCH";
	public static final String EVT_TIPO_RECUPERA_METADATI = "RECUPERA_METADATI";

	/* STATO EVT */
	public static final String EVT_STATO_INIZIO_ELAB = "INIZIO";
	public static final String EVT_STATO_FINE_ELAB = "FINE";

	public static final String EVT_STATO_ERRORE = "ERRORE";

	/* STATO EVT_LOG */
	public static final String EVT_LOG_STATO_INIZIO_ELAB = "INIZIO";
	public static final String EVT_LOG_STATO_FINE_ELAB = "FINE";

	public static final String EVT_LOG_STATO_ERRORE = "ERRORE";

	/* TIPO PASSO EVT LOG */
	public static final String EVT_LOG_TIPO_PASSO_SERVIZIO_INIZIO = "SERVIZIO_INIZIO";
	public static final String EVT_LOG_TIPO_PASSO_SERVIZIO_FINE = "SERVIZIO_FINE";
	public static final String EVT_LOG_TIPO_PASSO_FIRMA_DIGITALE = "FIRMA_DIGITALE";
	public static final String EVT_LOG_TIPO_PASSO_MARCA = "MARCA";
	public static final String EVT_LOG_TIPO_PASSO_ARCHIVIA = "ARCHIVIA";
	public static final String EVT_LOG_TIPO_PASSO_VERIFICA = "VERIFICA";
	public static final String EVT_LOG_TIPO_PASSO_SESSIONE = "SESSIONE";
	public static final String EVT_LOG_TIPO_PASSO_CONTATTA = "CONTATTA";
	public static final String EVT_LOG_TIPO_PASSO_VERIFICA_PARAMETRI = "VERIFICA_PARAMETRI";
	public static final String EVT_LOG_TIPO_PASSO_RICHIESTA_OTP = "RICHIESTA_OTP";

	public static final String EVT_LOG_TIPO_PASSO_RECUPERA_FILE = "RECUPERA_FILE";
	public static final String EVT_LOG_TIPO_PASSO_INQUIRY_REPOSITORY = "INQUIRY_REPOSITORY";
	public static final String EVT_LOG_TIPO_PASSO_MODIFICA_METADATI="MODIFICA_METADATI";
	public static final String EVT_LOG_TIPO_PASSO_CANCELLA_FILE = "CANCELLA_FILE";

	public static final String TIPO_USO_PARAM_STRUTTURALE = "S";

	public static String getDescrStatoEvento(String statoEvento) {

		if (EVT_STATO_INIZIO_ELAB.equals(statoEvento)) {
			return "Inizio Elaborazione";
		} else if (EVT_STATO_FINE_ELAB.equals(statoEvento)) {
			return "Fine Elaborazione";
		} else if (EVT_STATO_ERRORE.equals(statoEvento)) {
			return "In Errore";
		}
		return null;
	}

}
