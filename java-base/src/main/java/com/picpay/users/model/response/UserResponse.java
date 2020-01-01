package com.picpay.users.model.response;

import java.io.Serializable;

import com.picpay.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String cpf;

	private String email;

	private String full_name;

	private String password;

	private String phone_number;
	
	public UserResponse (User user) {
		this.id = user.getId();
		this.cpf = user.getCpf();
		this.email = user.getEmail();
		this.full_name = user.getFull_name();
		this.password = user.getPassword();
		this.phone_number = user.getPhone_number();
	}

}
