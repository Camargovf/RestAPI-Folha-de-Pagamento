package com.pagamento.hireus.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioOutputModel {
	
	private Long id;
	private String nomeFuncionario;
	private String matriculaFuncionario;
	private Boolean ativo;
	private OffsetDateTime dataAdimissao;
	private OffsetDateTime dataDesligamento;
	private Long cargoId;
	private String cargoDescricaoCargo;

}
