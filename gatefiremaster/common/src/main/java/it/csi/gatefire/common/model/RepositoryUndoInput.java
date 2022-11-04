/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import it.csi.gatefire.common.model.repository.Richiesta;

public class RepositoryUndoInput {
	@NotNull(message = "Campo obbligatorio")
	private Attachment attachment;



	@NotNull(message = "Campo obbligatorio")
	private String originalDocUniqueId;

	@NotNull(message = "Campo obbligatorio")
	private String undoDocUniqueId;

	@NotNull(message = "Campo obbligatorio")
	@Valid
	private Richiesta richiesta;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	

	public String getOriginalDocUniqueId() {
		return originalDocUniqueId;
	}

	public void setOriginalDocUniqueId(String originalDocUniqueId) {
		this.originalDocUniqueId = originalDocUniqueId;
	}

	public String getUndoDocUniqueId() {
		return undoDocUniqueId;
	}

	public void setUndoDocUniqueId(String undoDocUniqueId) {
		this.undoDocUniqueId = undoDocUniqueId;
	}

	public Richiesta getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(Richiesta richiesta) {
		this.richiesta = richiesta;
	}

}
