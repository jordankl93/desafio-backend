package com.picpay.users.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTransactionRequest {	
	
	@NotNull(message = "payee_id é obrigatório")
	private Long payee_id;

	@NotNull(message = "payer_id é obrigatório")
	private Long payer_id;
	
	@NotNull(message = "value é obrigatório")
	private BigDecimal value;

}
