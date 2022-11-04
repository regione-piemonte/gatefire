package it.csi.gatefire.common.model;

public abstract class BaseResult {
	private Result result = new Result();

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
