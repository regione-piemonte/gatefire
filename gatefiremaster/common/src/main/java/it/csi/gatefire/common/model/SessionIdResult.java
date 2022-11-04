/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sessionIdResult")
public class SessionIdResult extends BaseResult {
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "SessionIdResult [sessionId=" + sessionId + ", getResult()=" + getResult() + "]";
	}

	

}
