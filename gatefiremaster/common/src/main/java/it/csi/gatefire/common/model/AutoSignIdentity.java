package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.csi.gatefire.common.json.PasswordSerializer;

@XmlType(name = "autoSignIdentity")
public class AutoSignIdentity extends Identity {

	private String delegatedUser;

	private String delegatedPassword;

	private String delegatedDomain;

	public String getDelegatedUser() {
		return delegatedUser;
	}

	public void setDelegatedUser(String delegatedUser) {
		this.delegatedUser = delegatedUser;
	}

	@JsonSerialize(using = PasswordSerializer.class)
	public String getDelegatedPassword() {
		return delegatedPassword;
	}

	public void setDelegatedPassword(String delegatedPassword) {
		this.delegatedPassword = delegatedPassword;
	}

	public String getDelegatedDomain() {
		return delegatedDomain;
	}

	public void setDelegatedDomain(String delegatedDomain) {
		this.delegatedDomain = delegatedDomain;
	}

	@Override
	public String toString() {
		return "AutoSignIdentity [delegatedUser=" + delegatedUser + ", delegatedPassword=" + delegatedPassword
				+ ", delegatedDomain=" + delegatedDomain + ", getUser()=" + getUser() + ", getPassword()="
				+ getPassword() + ", getOtp()=" + getOtp() + "]";
	}

}
