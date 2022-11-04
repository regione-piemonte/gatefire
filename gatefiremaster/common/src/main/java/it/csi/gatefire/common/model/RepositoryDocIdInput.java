package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;

public class RepositoryDocIdInput {
	@NotNull(message = "Campo obbligatorio")
	private String docUid;

	public String getDocUid() {
		return docUid;
	}

	public void setDocUid(String docUid) {
		this.docUid = docUid;
	}

}
