package com.picpay.users.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSellerRequest {
	
	@NotBlank(message = "cnpj é obrigatório")
	private String cnpj;
	
	@NotBlank(message = "fantasy_name é obrigatório")
	private String fantasy_name;
	
	@NotBlank(message = "social_name é obrigatório")
	private String social_name;
	
	@NotNull(message = "user_id é obrigatório")
	private Long user_id;
	
	@NotBlank(message = "username é obrigatório")
	private String username;
	

}
