package it.csi.gatefire.common.exception;

import java.util.ArrayList;
import java.util.List;

import it.csi.gatefire.common.model.ErrorField;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<ErrorField> errorFields;

	public ValidationException(String message, List<ErrorField> errorFields) {
		super(message);
		this.errorFields = errorFields;

	}

	public ValidationException(String message, ErrorField errorField) {
		super(message);
		List<ErrorField> list = new ArrayList<>();
		list.add(errorField);
		this.errorFields = list;

	}

	public List<ErrorField> getErrorFields() {
		return errorFields;
	}

}
