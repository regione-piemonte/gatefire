package it.csi.gatefire.common.model.repository;

import javax.validation.constraints.NotNull;

public class Medico extends Persona {
	@NotNull(message = "Campo obbligatorio")
	private Ruolo ruolo;

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
}
