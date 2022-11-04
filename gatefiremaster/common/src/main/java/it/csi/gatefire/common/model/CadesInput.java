package it.csi.gatefire.common.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "cadesInput")
public class CadesInput {

	private SignIdentity signIdentity;

	private MarkIdentity markIdentity;

	private boolean requiredMark;

	private CallInfo callInfo;

	@NotNull(message = "Campo obbligatorio")
	private SignType signType = SignType.REMOTA;

	public CallInfo getCallInfo() {
		return callInfo;
	}

	public void setCallInfo(CallInfo callInfo) {
		this.callInfo = callInfo;
	}

	public SignIdentity getSignIdentity() {
		return signIdentity;
	}

	public void setSignIdentity(SignIdentity signIdentity) {
		this.signIdentity = signIdentity;
	}

	public MarkIdentity getMarkIdentity() {
		return markIdentity;
	}

	public void setMarkIdentity(MarkIdentity markIdentity) {
		this.markIdentity = markIdentity;
	}

	public boolean isRequiredMark() {
		return requiredMark;
	}

	public void setRequiredMark(boolean requiredMark) {
		this.requiredMark = requiredMark;
	}

	public SignType getSignType() {
		return signType;
	}

	public void setSignType(SignType signType) {
		this.signType = signType;
	}

	@Override
	public String toString() {
		return "CadesInput [signIdentity=" + signIdentity + ", markIdentity=" + markIdentity + ", requiredMark="
				+ requiredMark + ", callInfo=" + callInfo + "]";
	}

}
