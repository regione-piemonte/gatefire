package it.csi.gatefire.common.model.repository;

import javax.validation.constraints.NotNull;

public class Persona {
	@NotNull(message = "Campo obbligatorio")
	private String codiceFiscale;
	@NotNull(message = "Campo obbligatorio")
	private String nome;
	@NotNull(message = "Campo obbligatorio")
	private String cognome;

	private String secondoNome;
	private String prefisso;

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {

		if (codiceFiscale != null) {
			this.codiceFiscale = codiceFiscale.toUpperCase();
		} else {
			this.codiceFiscale = codiceFiscale;
		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null) {
			this.nome = nome.toUpperCase();
		} else {
			this.nome = nome;
		}
	}

	public String getCognome() {

		return cognome;
	}

	public void setCognome(String cognome) {
		if (cognome != null) {
			this.cognome = cognome.toUpperCase();
		} else {
			this.cognome = cognome;
		}

	}

	public String getSecondoNome() {
		return secondoNome;
	}

	public void setSecondoNome(String secondoNome) {
		this.secondoNome = secondoNome;
	}

	public String getPrefisso() {
		return prefisso;
	}

	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}

	public Persona(@NotNull(message = "Campo obbligatorio") String codiceFiscale,
			@NotNull(message = "Campo obbligatorio") String nome,
			@NotNull(message = "Campo obbligatorio") String cognome, String secondoNome, String prefisso) {
		super();

		if (codiceFiscale != null) {
			this.codiceFiscale = codiceFiscale.toUpperCase();
		} else {
			this.codiceFiscale = codiceFiscale;
		}

		if (nome != null) {
			this.nome = nome.toUpperCase();
		} else {
			this.nome = nome;
		}
		if (cognome != null) {
			this.cognome = cognome.toUpperCase();
		} else {
			this.cognome = cognome;
		}
		this.secondoNome = secondoNome;
		this.prefisso = prefisso;
	}

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

}
