package com.picpay.transactions.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

	@Override
	public boolean autorizaTransacao(BigDecimal value) {
		return value.compareTo(new BigDecimal(100)) == -1 ? true : false;
	}

}
