package com.picpay.users.utils;

public enum CodeErrorEnum {

	ERRO_INTERNO(999), 
	CAMPOS_OBG_USER(1), 
	CPF_EXISTENTE(2), 
	EMAIL_EXISTENTE(3), 
	USUARIO_NAO_EXISTENTE(4), 
	USERNAME_EXISTENTE(5),
	TIPO_CONTA_CADASTRADO(6),
	TRANSACAO_NAO_EXISTENTE(7),
	TRANSACAO_NAO_PERMITIDA(8);

	private final int value;

	CodeErrorEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
