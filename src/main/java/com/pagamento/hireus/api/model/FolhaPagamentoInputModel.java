package com.pagamento.hireus.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolhaPagamentoInputModel {
	
	private Long id;
	@NotNull
	private Long funcionarioId;
	@NotBlank
	@Size(min = 6, max = 6)
	@Pattern(regexp = "[0-9]*")
	private String referencia;
  
}
