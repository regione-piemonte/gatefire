package it.csi.gatefire.common.model;

import java.util.Date;

public class TimeStamp {

	@Override
	public String toString() {
		return "TimeStamp [serialNumber=" + serialNumber + ", generationTime=" + generationTime + ", tsAuthorityName="
				+ tsAuthorityName + ", signValid=" + signValid + "]";
	}

	private String serialNumber;

	private Date generationTime;

	private String tsAuthorityName;

	private Boolean signValid;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Date generationTime) {
		this.generationTime = generationTime;
	}

	public Boolean getSignValid() {
		return signValid;
	}

	public void setSignValid(Boolean signValid) {
		this.signValid = signValid;
	}

	public String getTsAuthorityName() {
		return tsAuthorityName;
	}

	public void setTsAuthorityName(String tsAuthorityName) {
		this.tsAuthorityName = tsAuthorityName;
	}

}
