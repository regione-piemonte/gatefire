package it.csi.gatefire.common.model.repository;

import javax.validation.constraints.NotNull;

public class Indirizzo {

	@NotNull(message = "Campo obbligatorio")
	String indirizzo;
	@NotNull(message = "Campo obbligatorio")
	String comune;
	@NotNull(message = "Campo obbligatorio")
	String codiceNazione;
	@NotNull(message = "Campo obbligatorio")
	String codIstatComune;

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCodiceNazione() {
		return codiceNazione;
	}

	public void setCodiceNazione(String codiceNazione) {
		this.codiceNazione = codiceNazione;
	}

	public String getCodIstatComune() {
		return codIstatComune;
	}

	public void setCodIstatComune(String codIstatComune) {
		this.codIstatComune = codIstatComune;
	}

}
