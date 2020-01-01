package com.picpay.users.resource;

import java.util.List;

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
import com.picpay.users.model.request.CreateConsumerRequest;
import com.picpay.users.model.request.CreateSellerRequest;
import com.picpay.users.model.request.CreateUserRequest;
import com.picpay.users.model.response.ConsumerResponse;
import com.picpay.users.model.response.SellerResponse;
import com.picpay.users.model.response.UserPayload;
import com.picpay.users.model.response.UserResponse;
import com.picpay.users.service.AccountService;
import com.picpay.users.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	private UserService userService;

	private AccountService accountService;

	UserController(UserService userService, AccountService accountService) {
		this.userService = userService;
		this.accountService = accountService;
	}

	@GetMapping
	public List<UserResponse> findAllOrByNameUsername(String q) {
		return userService.findAllOrByNameUsername(q);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@Valid @RequestBody CreateUserRequest userRequest) throws BusinessException {		
		return userService.save(userRequest);
	}

	@PostMapping(path = { "/consumers" })
	@ResponseStatus(HttpStatus.CREATED)
	public ConsumerResponse createConsumer(@Valid @RequestBody CreateConsumerRequest consumer) throws BusinessException {		
		return accountService.saveConsumer(consumer);
	}
	
	@PostMapping(path = { "/sellers" })
	@ResponseStatus(HttpStatus.CREATED)
	public SellerResponse createSeller(@Valid @RequestBody CreateSellerRequest seller) throws BusinessException {		
		return accountService.saveSeller(seller);
	}

	@GetMapping(path = { "/{user_id}" })
	public UserPayload findById(@PathVariable Long user_id) throws BusinessException{				
		return userService.findById(user_id);
	}

}
