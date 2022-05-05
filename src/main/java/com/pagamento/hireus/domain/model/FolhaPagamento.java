package com.pagamento.hireus.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Getter
@Entity(name = "folha_pagamento")
public class FolhaPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Funcionario funcionario;
	@Column(nullable = false, length = 6)
	private String referencia;
	@Column(nullable = false)
	private BigDecimal inss;
	@Column(nullable = false)
	private BigDecimal irrf;
	@Column(nullable = false)
	private BigDecimal salarioLiquido;

	public void setId(Long id) {
		this.id = id;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setInss() {
		Cargo cargoFuncionario = getFuncionario().getCargo();
		BigDecimal salarioFuncionario = cargoFuncionario.getSalarioCargo();
		
		if (salarioFuncionario.compareTo(BigDecimal.valueOf(1212)) <= 0) {
			this.inss = salarioFuncionario.multiply(converteDoubleparaBigDecimal(0.075));
		} else if (salarioFuncionario.compareTo(BigDecimal.valueOf(2427.35)) <= 0) {
			this.inss = salarioFuncionario.multiply(converteDoubleparaBigDecimal(0.09));
		} else if (salarioFuncionario.compareTo(BigDecimal.valueOf(3641.03)) <= 0) {
			this.inss = salarioFuncionario.multiply(converteDoubleparaBigDecimal(0.12));
		} else {
			this.inss = salarioFuncionario.multiply(converteDoubleparaBigDecimal(0.14));
		}
	}

	public void setIrrf() {
		Cargo cargoFuncionario = getFuncionario().getCargo();
		BigDecimal salarioFuncionario = cargoFuncionario.getSalarioCargo();
		
		if (salarioFuncionario.compareTo(BigDecimal.valueOf(1903.98)) <= 0) {
			this.irrf = salarioFuncionario.multiply(converteDoubleparaBigDecimal(0.0));
		} else if (salarioFuncionario.compareTo(BigDecimal.valueOf(2826.65)) <= 0) {
			this.irrf = salarioFuncionario.multiply(converteDoubleparaBigDecimal((0.075/12)));
		} else if (salarioFuncionario.compareTo(BigDecimal.valueOf(3751.05)) <= 0) {
			this.irrf = salarioFuncionario.multiply(converteDoubleparaBigDecimal((0.15/12)));
		} else if (salarioFuncionario.compareTo(BigDecimal.valueOf(4664.68)) <= 0) {
			this.irrf = salarioFuncionario.multiply(converteDoubleparaBigDecimal((0.225/12)));
		} else {
			this.irrf = salarioFuncionario.multiply(converteDoubleparaBigDecimal((0.275/12)));
		}	
	}

	public void setSalarioLiquido() {
		BigDecimal salarioFuncionario = getFuncionario().getCargo().getSalarioCargo();
		this.salarioLiquido = salarioFuncionario.subtract(getInss()).subtract(getIrrf());
	}
	
	private BigDecimal converteDoubleparaBigDecimal(Double valor) {
		return BigDecimal.valueOf(valor);
	}

}
