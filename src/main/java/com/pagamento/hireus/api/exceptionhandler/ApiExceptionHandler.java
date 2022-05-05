package com.pagamento.hireus.api.exceptionhandler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pagamento.hireus.api.exceptionhandler.Problema.Erro;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ArrayList<Erro> erros = new ArrayList<Problema.Erro>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			erros.add(new Problema.Erro(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		problema.setErros(erros);

		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EmptyRecurseException.class)
	public ResponseEntity<Object> handleRecursoNaoEncontrado(EmptyRecurseException ex, WebRequest request){
		var status = HttpStatus.NOT_FOUND;
		
		Problema problema = retornarProb(status, ex);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ValueMostBePositiveExcpetion.class)
	public ResponseEntity<Object> handleValorTemQueSerPositivo(ValueMostBePositiveExcpetion ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = retornarProb(status, ex);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(FuncionarioAlreadyExistingException.class)
	public ResponseEntity<Object> handleFuncionarioMatriculaExistente(FuncionarioAlreadyExistingException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = retornarProb(status, ex);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(FuncionarioFiredException.class)
	public ResponseEntity<Object> handleFuncionarioInativo(FuncionarioFiredException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = retornarProb(status, ex);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	private Problema retornarProb(HttpStatus status, RuntimeException ex) {
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		return problema;
	}
}