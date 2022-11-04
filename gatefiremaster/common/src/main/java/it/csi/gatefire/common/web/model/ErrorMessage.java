/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.web.model;

public class ErrorMessage {

	
	
	public static final String MSG_NON_SPECIFICATO = "Non specificato";
	
	private String fieldName;
	private String label;
	private String message;

	private boolean visualizzaMsg;

	public boolean isVisualizzaMsg() {
		return visualizzaMsg;
	}

	public ErrorMessage(String fieldName, String label, String message, boolean visualizzaMsg) {
		this.fieldName = fieldName;
		this.message = message;
		this.label = label;
		this.visualizzaMsg = visualizzaMsg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}

	public String getLabel() {
		return label;
	}

}