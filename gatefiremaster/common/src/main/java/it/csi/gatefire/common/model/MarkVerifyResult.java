package it.csi.gatefire.common.model;

public class MarkVerifyResult extends BaseResult {

	private Boolean valid;

	private String validation;
	private String validationMessage;

	// private Attachment originalFile;
	private Certificate certificate;
	private TimeStamp timeStamp;

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	/*
	 * public Attachment getOriginalFile() { return originalFile; }
	 * 
	 * public void setOriginalFile(Attachment originalFile) { this.originalFile =
	 * originalFile; }
	 */
	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
