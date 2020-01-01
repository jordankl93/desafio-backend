package com.picpay.users.model.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenericError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String message;
	
	public GenericError() {
		this.code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
		this.message = "Erro interno";
	}

}
