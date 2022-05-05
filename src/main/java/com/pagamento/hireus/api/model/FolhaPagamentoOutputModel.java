package com.pagamento.hireus.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolhaPagamentoOutputModel {
	
	private Long id;
	private String funcionarioNomeFuncionario;
	private String funcionarioMatriculaFuncionario;
	private String funcionarioCargoDescricaoCargo;
	private String funcionarioCargoSalarioCargo;
	private BigDecimal inss;
	private BigDecimal irrf;
	private BigDecimal salarioLiquido;
}
