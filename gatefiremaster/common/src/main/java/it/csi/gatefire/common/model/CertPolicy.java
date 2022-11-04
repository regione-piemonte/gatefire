package it.csi.gatefire.common.model;

public class CertPolicy {
	private String cpsUri;
	private String cpText;

	public String getCpsUri() {
		return cpsUri;
	}

	public void setCpsUri(String cpsUri) {
		this.cpsUri = cpsUri;
	}

	public String getCpText() {
		return cpText;
	}

	public void setCpText(String cpText) {
		this.cpText = cpText;
	}

	@Override
	public String toString() {
		return "CertPolicy [cpsUri=" + cpsUri + ", cpText=" + cpText + "]";
	}

}
