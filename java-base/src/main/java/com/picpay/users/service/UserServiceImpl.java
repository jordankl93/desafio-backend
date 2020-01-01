package com.picpay.users.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.User;
import com.picpay.users.model.request.CreateUserRequest;
import com.picpay.users.model.response.GenericError;
import com.picpay.users.model.response.UserPayload;
import com.picpay.users.model.response.UserResponse;
import com.picpay.users.repository.UserRepository;
import com.picpay.users.utils.CodeErrorEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<UserResponse> findAllOrByNameUsername(String name) {
		
		if (name == null || name.isEmpty())
			return this.userRepository.findAll().stream().map(q -> new UserResponse(q)).collect(Collectors.toList());
		else {		
			return this.userRepository.findByFullnameOrUsername(name).stream().map(q -> new UserResponse(q)).collect(Collectors.toList());
		}
		
	}

	@Override
	public UserPayload findById(Long id) throws BusinessException {
		Optional<User> user = this.userRepository.findById(id);
		
		if (!user.isPresent())
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.USUARIO_NAO_EXISTENTE.getValue()),
					String.format("Usuário de id %s não existe", id)));			
			
		return user.map(q -> new UserPayload(q)).get();
	}

	@Override
	public UserResponse save(CreateUserRequest userRequest) throws BusinessException {
		
		User user = userRequest.convert();

		boolean existeCpf = this.userRepository.existsByCpf(user.getCpf());

		if (existeCpf)
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.CPF_EXISTENTE.getValue()),
					String.format("Cpf %s já existente", user.getCpf())));

		boolean exiteEmail = this.userRepository.existsByEmail(user.getEmail());

		if (exiteEmail)
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.EMAIL_EXISTENTE.getValue()),
					String.format("E-mail %s já existente", user.getEmail())));

		user = this.userRepository.save(user);
		
		if (user != null)
			return UserResponse.builder()
					.id(user.getId())
					.cpf(user.getCpf())
					.email(user.getEmail())
					.full_name(user.getFull_name())
					.password(user.getPassword())
					.phone_number(user.getPhone_number())
					.build();
		else
			return null;
	}

}
