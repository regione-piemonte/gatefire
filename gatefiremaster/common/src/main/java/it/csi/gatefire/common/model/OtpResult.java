package it.csi.gatefire.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "otpResult")
public class OtpResult extends BaseResult {
	private Boolean success = true;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "OtpResult [success=" + success + ", getResult()=" + getResult() + "]";
	}
}
