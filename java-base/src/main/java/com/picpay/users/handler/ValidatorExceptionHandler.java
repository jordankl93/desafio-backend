package com.picpay.users.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.response.GenericError;
import com.picpay.users.utils.CodeErrorEnum;

@RestControllerAdvice
public class ValidatorExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<GenericError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<GenericError> errors = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			GenericError error = new GenericError(String.valueOf(CodeErrorEnum.CAMPOS_OBG_USER.getValue()), message);
			errors.add(error);
		}

		return errors;
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<GenericError> handleBusinessException(BusinessException exception) {
		
		if (String.valueOf(CodeErrorEnum.USUARIO_NAO_EXISTENTE.getValue()).equals(exception.getCode()))
			return new ResponseEntity<>(exception.getErro(), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(exception.getErro(), HttpStatus.UNPROCESSABLE_ENTITY);
		
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<GenericError> handleHttpClientErrorException(HttpClientErrorException exception) {
		GenericError errorDetails;
		
		if(HttpStatus.UNAUTHORIZED.equals(exception.getStatusCode())) {
			errorDetails = new GenericError(String.valueOf(CodeErrorEnum.TRANSACAO_NAO_PERMITIDA.getValue()), "Transação não autorizada");
			return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
		} else {
			errorDetails = new GenericError(String.valueOf(CodeErrorEnum.ERRO_INTERNO.getValue()), "Erro de Cliente");
			return new ResponseEntity<>(errorDetails, exception.getStatusCode());
		}
		
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<GenericError> handleAllExceptions(Exception ex) {
		GenericError errorDetails = new GenericError(String.valueOf(CodeErrorEnum.ERRO_INTERNO.getValue()), "Erro interno");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
