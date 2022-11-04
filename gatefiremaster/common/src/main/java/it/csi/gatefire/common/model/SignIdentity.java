package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "signIdentity")
public class SignIdentity extends Identity {

	

	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "SignIdentity [sessionId=" + sessionId + ", getUser()=" + getUser()
				+ ", getPassword()=" + getPassword() + ", getOtp()=" + getOtp() + "]";
	}

}
