package it.csi.gatefire.common.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RepositoryInput {
	@NotNull(message = "Campo obbligatorio")
	private Attachment attachment;

	
	
	@NotNull(message = "Campo obbligatorio")
	@Valid
	private ITIMetadata metadata;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	

	public ITIMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(ITIMetadata metadata) {
		this.metadata = metadata;
	}

}
