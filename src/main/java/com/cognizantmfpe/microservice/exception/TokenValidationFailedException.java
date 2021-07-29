package com.cognizantmfpe.microservice.exception;

public class TokenValidationFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String string) {
		super(string);
	}

}
