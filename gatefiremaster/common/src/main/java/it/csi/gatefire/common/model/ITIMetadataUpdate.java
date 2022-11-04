/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;

import it.csi.gatefire.common.model.repository.Documento;
import it.csi.gatefire.common.model.repository.Episodio;
import it.csi.gatefire.common.model.repository.Paziente;
import it.csi.gatefire.common.model.repository.Richiesta;

public class ITIMetadataUpdate {

	@NotNull(message = "Campo obbligatorio")
	private Documento documento;
	@NotNull(message = "Campo obbligatorio")
	private Richiesta richiesta;


	
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

	

}
