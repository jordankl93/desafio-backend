package com.picpay.users.service;

import java.util.List;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.request.CreateUserRequest;
import com.picpay.users.model.response.UserPayload;
import com.picpay.users.model.response.UserResponse;

public interface UserService {

	public List<UserResponse> findAllOrByNameUsername(String name);

	public UserPayload findById(Long id) throws BusinessException;
	
	public UserResponse save(CreateUserRequest userRequest) throws BusinessException;
	
}
