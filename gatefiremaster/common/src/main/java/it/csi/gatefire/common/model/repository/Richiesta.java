package it.csi.gatefire.common.model.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

public class Richiesta {

	@NotNull(message = "Campo obbligatorio")
	private String codiceAzienda; // documententry,submission set author.authorInstitution ID_FLS11_ASSIGNING_AUTH

	@NotNull(message = "Campo obbligatorio")
	private Utente utente; // documententry,submission set author

	@NotNull(message = "Campo obbligatorio")
	private String codiceApplicativo; // documententry.extrametadata

	private List<Prestazione> prestazione;
	// private Date dataOra;

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getCodiceApplicativo() {
		return codiceApplicativo;
	}

	public void setCodiceApplicativo(String codiceApplicativo) {
		this.codiceApplicativo = codiceApplicativo;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Prestazione> getPrestazione() {
		return prestazione;
	}

	public void setPrestazione(List<Prestazione> prestazione) {
		this.prestazione = prestazione;
	}

}
