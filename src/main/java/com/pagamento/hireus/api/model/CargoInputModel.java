package com.pagamento.hireus.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter //Get, necessidade de uso por conta do oprivate.
@Setter //Set, necessidade de uso por conta do moddel (DTO)
public class CargoInputModel {
	
	private Long id;
	@NotBlank()
	@Size(min = 2, max = 50)
//	@Pattern(regexp = "^[A-Z]+(.)*")
	private String descricaoCargo;
	@NotNull
//	@Pattern(regexp = "^[0-9]+[\.]?[0-9]+$", message = "Apenas números maior que zero. Decimal formato xxxx.xx")
	private BigDecimal salarioCargo;
	@NotNull
//	@Pattern(regexp = "^[1-9]+[0-9]*$", message = "Apenas valores positivos")
	private Integer totalHorasCargo;

}
