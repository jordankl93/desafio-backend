package com.picpay.users.service;

import java.util.List;
import java.util.Optional;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.Account;
import com.picpay.users.model.request.CreateConsumerRequest;
import com.picpay.users.model.request.CreateSellerRequest;
import com.picpay.users.model.response.ConsumerResponse;
import com.picpay.users.model.response.SellerResponse;

public interface AccountService {

	public List<Account> findAll();

	public Optional<Account> findById(Long id);
	
	public ConsumerResponse saveConsumer(CreateConsumerRequest consumerRequest) throws BusinessException;
	
	public SellerResponse saveSeller(CreateSellerRequest sellerRequest) throws BusinessException;
	
}
