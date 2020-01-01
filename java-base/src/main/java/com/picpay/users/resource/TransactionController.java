package com.picpay.users.resource;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.request.CreateTransactionRequest;
import com.picpay.users.model.response.TransactionResponse;
import com.picpay.users.service.TransactionService;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {
	
	private TransactionService transactionService;
	
	TransactionController(TransactionService transactionService){
		this.transactionService = transactionService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionResponse create(@Valid @RequestBody CreateTransactionRequest userRequest) throws BusinessException {		
		return transactionService.save(userRequest);
	}
	
	@GetMapping(path = { "/{transaction_id}" })
	public TransactionResponse findById(@PathVariable Long transaction_id) throws BusinessException{				
		return transactionService.findById(transaction_id);
	}

}
