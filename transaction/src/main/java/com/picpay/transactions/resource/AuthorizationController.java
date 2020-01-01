package com.picpay.transactions.resource;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.transactions.service.AuthorizationService;

@RestController
@RequestMapping(path = "/authorization")
public class AuthorizationController {
	
	private AuthorizationService authorizationService;
	
	AuthorizationController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;	
	}
	
	@GetMapping
	public ResponseEntity<Boolean> autorizaTransacao(@Valid @RequestParam BigDecimal value) {
		
		if(this.authorizationService.autorizaTransacao(value))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
