package com.picpay.users.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateConsumerRequest {
	
	@NotNull(message = "user_id é obrigatório")
	private Long user_id;
	
	@NotBlank(message = "username é obrigatório")
	private String username;
	
	
}
