package com.picpay.users.model.request;

import javax.validation.constraints.NotBlank;

import com.picpay.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

	@NotBlank(message = "cpf é obrigatório")
	private String cpf;

	@NotBlank(message = "email é obrigatório")
	private String email;

	@NotBlank(message = "full_name é obrigatório")
	private String full_name;

	@NotBlank(message = "password é obrigatório")
	private String password;

	@NotBlank(message = "phone_number é obrigatório")
	private String phone_number;

	public User convert() {
		return User.builder()
				.cpf(cpf)
				.email(email)
				.full_name(full_name)
				.password(password)
				.phone_number(phone_number)
				.build();
	}

}
