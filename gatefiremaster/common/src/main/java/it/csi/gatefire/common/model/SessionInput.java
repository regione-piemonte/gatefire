package it.csi.gatefire.common.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "sessionInput")
public class SessionInput {
	@NotNull(message = "Campo obbligatorio")
	@Valid
	private SignIdentity signIdentity;

	@NotNull(message = "Campo obbligatorio")
	private SignType signType = SignType.REMOTA;

	private CallInfo callInfo;

	public SignIdentity getSignIdentity() {
		return signIdentity;
	}

	public CallInfo getCallInfo() {
		return callInfo;
	}

	public void setCallInfo(CallInfo callInfo) {
		this.callInfo = callInfo;
	}

	public void setSignIdentity(SignIdentity signIdentity) {
		this.signIdentity = signIdentity;
	}

	public SignType getSignType() {
		return signType;
	}

	public void setSignType(SignType signType) {
		this.signType = signType;
	}

	@Override
	public String toString() {
		return "SessionInput [signIdentity=" + signIdentity + ", signType=" + signType + ", callInfo=" + callInfo + "]";
	}

}
