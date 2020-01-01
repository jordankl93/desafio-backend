package com.picpay.users.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SellerResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	
	private String fantasy_name;
	
	private Long id;
	
	private String social_name;
	
	private Long user_id;
	
	private String username;

}
