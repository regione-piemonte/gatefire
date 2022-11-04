/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.security;

import java.security.Principal;

public class ShibbolethAuthToken implements Principal {

	private String username;
	private String nome;
	private String cognome;

	@Override
	public String getName() {

		return username;
	}

	public ShibbolethAuthToken(String username) {
		super();
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
