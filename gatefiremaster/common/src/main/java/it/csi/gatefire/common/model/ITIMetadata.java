package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;

import it.csi.gatefire.common.model.repository.Documento;
import it.csi.gatefire.common.model.repository.Episodio;
import it.csi.gatefire.common.model.repository.Paziente;
import it.csi.gatefire.common.model.repository.Richiesta;

public class ITIMetadata {
	@NotNull(message = "Campo obbligatorio")
	private Paziente paziente;
	@NotNull(message = "Campo obbligatorio")
	private Documento documento;
	@NotNull(message = "Campo obbligatorio")
	private Richiesta richiesta;
	@NotNull(message = "Campo obbligatorio")
	private Episodio episodio;

	public Paziente getPaziente() {
		return paziente;
	}

	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Richiesta getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(Richiesta richiesta) {
		this.richiesta = richiesta;
	}

	public Episodio getEpisodio() {
		return episodio;
	}

	public void setEpisodio(Episodio episodio) {
		this.episodio = episodio;
	}

}
