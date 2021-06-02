package com.impacta.estruturadedados.exception;

public class NonEmptyTreeException extends RuntimeException {
	
	private static final long serialVersionUID = -1075300797810090968L;
	public NonEmptyTreeException(String message) { 
		super(message); 
	}
}
