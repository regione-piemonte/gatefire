package it.csi.gatefire.common.model;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.csi.gatefire.common.json.PasswordSerializer;

@XmlType(name = "identity")
public class Identity {
	@NotEmpty(message = "Campo obbligatorio")
	private String user;

	private String password;

	private String otp;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@JsonSerialize(using = PasswordSerializer.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonSerialize(using = PasswordSerializer.class)
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "Identity [user=" + user + ", password=" + password + ", otp=" + otp + "]";
	}

}
