/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "otpReqInput")
public class OtpReqInput {
	@NotNull(message = "Campo obbligatorio")
	private Identity identity;

	private OtpCredentialsType otpCredentialsType = OtpCredentialsType.SMS;

	private CallInfo callInfo;

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public CallInfo getCallInfo() {
		return callInfo;
	}

	public void setCallInfo(CallInfo callInfo) {
		this.callInfo = callInfo;
	}

	public OtpCredentialsType getOtpCredentialsType() {
		return otpCredentialsType;
	}

	public void setOtpCredentialsType(OtpCredentialsType otpCredentialsType) {
		this.otpCredentialsType = otpCredentialsType;
	}

}
