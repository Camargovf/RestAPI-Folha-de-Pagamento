package com.pagamento.hireus.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioInputModel {
	
	private Long id;
	@NotBlank
	@Size(min = 10, max = 70)
	@Pattern(regexp = "^[A-Z]+(.)*", message = "A palavra deve iniciar com letra maiuscula")
	private String nomeFuncionario;
	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]*")
	private String matriculaFuncionario;
	private Long cargoId;	
}
