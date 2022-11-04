/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlRootElement;

import it.csi.gatefire.common.util.ErrorHelper;
@XmlRootElement(name="result")
public class Result {

	public Result() {
		super();

	}

	public Result(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = ErrorHelper.decodeError(errorCode);

	}  

	private String errorCode = "0";
	private String description;
	private String message;
	private String originalReturnCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOriginalReturnCode() {
		return originalReturnCode;
	}

	public void setOriginalReturnCode(String originalReturnCode) {
		this.originalReturnCode = originalReturnCode;
	}

	@Override
	public String toString() {
		return "Result [errorCode=" + errorCode + ", description=" + description + ", message=" + message + "]";
	}

	

}
