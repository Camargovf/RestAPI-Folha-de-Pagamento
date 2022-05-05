package com.pagamento.hireus.api.exceptionhandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {
	
	private Integer status;
	private String titulo;
	private List<Erro> erros;
	
	@AllArgsConstructor
	@Getter
	public static class Erro{
		private String nome;
		private String mensagem;
	}

}
