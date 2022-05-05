package com.pagamento.hireus.api.exceptionhandler;

public class FuncionarioFiredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FuncionarioFiredException(String message) {
		super(message);
	}
}
