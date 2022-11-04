/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.csi.gatefire.common.json.PasswordSerializer;

@XmlType(name = "markIdentity")
public class MarkIdentity {
	@NotEmpty(message = "Campo obbligatorio")
	private String user;
	@NotEmpty(message = "Campo obbligatorio")
	private String password;

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

	@Override
	public String toString() {
		return "MarkIdentity [user=" + user + ", password=" + password + "]";
	}
}
