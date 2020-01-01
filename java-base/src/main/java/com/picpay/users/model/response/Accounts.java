package com.picpay.users.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class Accounts implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ConsumerResponse consumer;
	
	private SellerResponse seller;

}
