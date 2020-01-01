package com.picpay.transactions.service;

import java.math.BigDecimal;

public interface AuthorizationService {
	
	public boolean autorizaTransacao(BigDecimal value);

}
