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
public class ConsumerResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long user_id;
	
	private String username;
	

}
