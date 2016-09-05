package com.soch.uam.exception;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorDescription;
	
	public InvalidDataException(int errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
}
