/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

import javax.validation.constraints.NotNull;

public class Utente extends Persona {

	private String codiceStruttura;
	private String codiceMatricola;

	@NotNull(message = "Campo obbligatorio")
	private Ruolo ruolo;

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public String getCodiceStruttura() {
		return codiceStruttura;
	}

	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}

	public String getCodiceMatricola() {
		return codiceMatricola;
	}

	public void setCodiceMatricola(String codiceMatricola) {
		this.codiceMatricola = codiceMatricola;
	}

	public Utente() {
		super();

	}

	public Utente(@NotNull(message = "Campo obbligatorio") String codiceFiscale,
			@NotNull(message = "Campo obbligatorio") String nome,
			@NotNull(message = "Campo obbligatorio") String cognome, String secondoNome, String prefisso) {
		super(codiceFiscale, nome, cognome, secondoNome, prefisso);

	}

}
