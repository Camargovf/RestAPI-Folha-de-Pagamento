package com.pagamento.hireus.api.exceptionhandler;

public class FuncionarioAlreadyExistingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FuncionarioAlreadyExistingException(String message) {
		super(message);
	}
}
