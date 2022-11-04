package it.csi.gatefire.common.model;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "attachment")
public class Attachment {
	@NotEmpty(message = "Campo obbligatorio")
	private String fileName;
	private byte[] file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonIgnore
	public byte[] getFile() {
		return file;
	}

	@JsonProperty
	public void setFile(byte[] file) {
		this.file = file;
	}

	public Attachment() {
		super();

	}

	public Attachment(String fileName, byte[] file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}

}
