/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.web.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {

	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String STATUS_ERROR = "ERROR";
	
	
	private String status = STATUS_SUCCESS;
	private List<ErrorMessage> errorMessageList;
	private String message;

	private String returnId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ErrorMessage> getErrorMessageList() {
		return this.errorMessageList;
	}

	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	public void addErrorMessage(ErrorMessage errorMessage) {
		if (errorMessageList == null) {
			errorMessageList = new ArrayList<>();
		}
		errorMessageList.add(errorMessage);
	}

	public void addErrorMessage(String fieldName, String label, String message, boolean visualizzaMsg) {
		if (errorMessageList == null) {
			errorMessageList = new ArrayList<>();
		}
		ErrorMessage errorMessage = new ErrorMessage(fieldName, label, message, visualizzaMsg);
		errorMessageList.add(errorMessage);
	}

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

}