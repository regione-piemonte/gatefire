package it.csi.gatefire.common.model;

import java.io.Serializable;

public class ErrorField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field;
	private String error;

	public static final String CAMPO_OBBLIGATORIO = "Campo obbligatorio";
	public static final String VALORE_INVALIDO = "Valore non valido";
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ErrorField(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorField [field=" + field + ", error=" + error + "]";
	}

}
