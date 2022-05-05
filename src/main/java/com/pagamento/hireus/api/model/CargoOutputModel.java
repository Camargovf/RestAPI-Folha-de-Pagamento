package com.pagamento.hireus.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoOutputModel {
	
	private Long id;
	private String descricaoCargo;
	private BigDecimal salarioCargo;
	private Integer totalHorasCargo;

}
