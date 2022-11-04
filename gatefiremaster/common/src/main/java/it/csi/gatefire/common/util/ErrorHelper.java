package it.csi.gatefire.common.util;

public class ErrorHelper {

	private ErrorHelper() {
		super();

	}

	public static final String ERRORE_GENERICO = "500";
	public static final String OK = "0";
	public static final String ERROR_VALIDAZIONE = "200";
	public static final String ERRORE_CREDENZIALI = "204";
	public static final String ERRORE_FIRMA = "208";
	public static final String ERRORE_MARCA = "209";
	public static final String ERRORE_VERIFICA_FIRMA = "212";
	public static final String ERRORE_VERIFICA_MARCA = "213";
	public static final String ERRORE_APERTURA_SESSIONE = "214";
	public static final String ERRORE_CHIUSURA_SESSIONE = "215";
	
	public static final String ERRORE_CONNESSIONE_DB = "100";
	
	
	
	public static final String ERRORE_OTP = "230";

	public static final String ERRORE_REPOSITORY = "210";

	// public static final int ERROR_SALVATAGGIO_FILE = -20;

	public static String decodeError(String errorCode) {

		if (ERRORE_GENERICO.equalsIgnoreCase(errorCode)) {
			return "Errore generico";
		} else if (ERROR_VALIDAZIONE.equalsIgnoreCase(errorCode)) {
			return "Errore di validazione";
		} /*
			 * else if (errorCode == ERROR_SALVATAGGIO_FILE) { return
			 * "Errore nel salvataggio del file"; }
			 */
		return "";

	}
}
