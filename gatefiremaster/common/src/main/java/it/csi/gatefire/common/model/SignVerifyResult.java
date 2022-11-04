package it.csi.gatefire.common.model;

import java.util.List;

public class SignVerifyResult extends BaseResult {

	private List<Signer> signer;

	public List<Signer> getSigner() {
		return signer;
	}

	public void setSigner(List<Signer> signer) {
		this.signer = signer;
	}

}
