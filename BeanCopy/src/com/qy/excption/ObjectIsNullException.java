package com.qy.excption;

public class ObjectIsNullException extends RuntimeException {
	
	private static final long serialVersionUID = -3400428888228805450L;

	public ObjectIsNullException() {
		super();
	}
	
	public ObjectIsNullException(String message) {
		super(message);
	}
}
