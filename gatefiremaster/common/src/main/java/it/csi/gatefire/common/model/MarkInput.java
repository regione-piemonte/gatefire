/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MarkInput {
	
	private CallInfo callInfo;

	@NotNull(message = "Campo obbligatorio")
	@Valid
	private MarkIdentity markIdentity;

	@NotNull(message = "Campo obbligatorio")
	private MarkType markType;

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

	public MarkType getMarkType() {
		return markType;
	}

	public void setMarkType(MarkType markType) {
		this.markType = markType;
	}

	@Override
	public String toString() {
		return "MarkInput [callInfo=" + callInfo + ", markIdentity=" + markIdentity + ", markType=" + markType + "]";
	}

}
