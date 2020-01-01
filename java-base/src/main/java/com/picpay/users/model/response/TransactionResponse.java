package com.picpay.users.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long payee_id;
	
	private Long payer_id;
	
	private Date transaction_date;
	
	private BigDecimal value;


}
