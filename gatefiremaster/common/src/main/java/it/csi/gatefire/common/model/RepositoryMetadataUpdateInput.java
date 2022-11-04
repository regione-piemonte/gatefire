package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;

public class RepositoryMetadataUpdateInput {



	@NotNull(message = "Campo obbligatorio")
	private String docUid;

	@NotNull(message = "Campo obbligatorio")
	private ITIMetadataUpdate metadata;

	public ITIMetadataUpdate getMetadata() {
		return metadata;
	}

	public void setMetadata(ITIMetadataUpdate metadata) {
		this.metadata = metadata;
	}


	public String getDocUid() {
		return docUid;
	}

	public void setDocUid(String docUid) {
		this.docUid = docUid;
	}

}
