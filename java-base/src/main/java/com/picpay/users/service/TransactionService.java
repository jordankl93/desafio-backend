package com.picpay.users.service;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.request.CreateTransactionRequest;
import com.picpay.users.model.response.TransactionResponse;

public interface TransactionService {
	
	public TransactionResponse findById(Long id) throws BusinessException;
	
	public TransactionResponse save(CreateTransactionRequest userRequest) throws BusinessException;

}
