/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import java.util.Date;

public class Signer {
	private Boolean valid;

	private String signValidation;
	private String signValidationMessage;
	private Date signTime;
	private String signTimeStr;

	public String getSignTimeStr() {
		return signTimeStr;
	}

	public void setSignTimeStr(String signTimeStr) {
		this.signTimeStr = signTimeStr;
	}

	private Certificate certificate;
	private TimeStamp timestamp;

	public String getSignValidation() {
		return signValidation;
	}

	public void setSignValidation(String signValidation) {
		this.signValidation = signValidation;
	}

	public String getSignValidationMessage() {
		return signValidationMessage;
	}

	public void setSignValidationMessage(String signValidationMessage) {
		this.signValidationMessage = signValidationMessage;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public TimeStamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(TimeStamp timestamp) {
		this.timestamp = timestamp;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return "Signer [valid=" + valid + ", signValidation=" + signValidation + ", signValidationMessage="
				+ signValidationMessage + ", signTime=" + signTime + ", certificate=" + certificate + ", timestamp="
				+ timestamp + "]";
	}

}
