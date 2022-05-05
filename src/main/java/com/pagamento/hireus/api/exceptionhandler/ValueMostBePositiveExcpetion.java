package com.pagamento.hireus.api.exceptionhandler;


public class ValueMostBePositiveExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValueMostBePositiveExcpetion(String message) {
		super(message);
	}
	
}
