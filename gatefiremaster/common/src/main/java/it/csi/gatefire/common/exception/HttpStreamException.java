package it.csi.gatefire.common.exception;

public class HttpStreamException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 589474717551829885L;

	public HttpStreamException(String message) {

		super(message);
	}

	public HttpStreamException(String message, int errorCode) {

		super(message);
		this.errorCode = errorCode;

	}

	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStreamException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public HttpStreamException(Throwable arg0) {
		super(arg0);

	}

}
