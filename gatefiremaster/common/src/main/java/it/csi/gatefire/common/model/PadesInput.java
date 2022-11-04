/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "padesInput")
public class PadesInput {


	private MarkIdentity markIdentity;

	private boolean requiredMark;

	@NotNull(message = "Campo obbligatorio")
	private SignLayout signLayout;



	private CallInfo callInfo;


	public CallInfo getCallInfo() {
		return callInfo;
	}

	public void setCallInfo(CallInfo callInfo) {
		this.callInfo = callInfo;
	}


	public MarkIdentity getMarkIdentity() {
		return markIdentity;
	}

	public void setMarkIdentity(MarkIdentity markIdentity) {
		this.markIdentity = markIdentity;
	}

	public boolean isRequiredMark() {
		return requiredMark;
	}

	public void setRequiredMark(boolean requiredMark) {
		this.requiredMark = requiredMark;
	}

	public SignLayout getSignLayout() {
		return signLayout;
	}

	public void setSignLayout(SignLayout signLayout) {
		this.signLayout = signLayout;
	}

	
	@Override
	public String toString() {
		return "PadesInput [ markIdentity=" + markIdentity + ", requiredMark="
				+ requiredMark + ", signLayout=" + signLayout + ", callInfo=" + callInfo + "]";
	}

}
