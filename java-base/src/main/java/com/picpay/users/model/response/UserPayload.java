package com.picpay.users.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.picpay.users.model.Account;
import com.picpay.users.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class UserPayload implements Serializable {

	private static final long serialVersionUID = 1L;

	private Accounts accounts;

	private UserResponse user;

	public UserPayload(User user) {

		this.user = UserResponse.builder().id(user.getId()).cpf(user.getCpf()).email(user.getEmail())
				.full_name(user.getFull_name()).password(user.getPassword()).phone_number(user.getPhone_number())
				.build();

		if (user.getAccounts() != null && !user.getAccounts().isEmpty())
			this.accounts = new Accounts();

		for (Account account : user.getAccounts()) {
			if (account.getSeller() != null) {
				this.accounts.setSeller(SellerResponse.builder().id(account.getId()).user_id(account.getUser().getId())
						.username(account.getUsername()).cnpj(account.getSeller().getCnpj())
						.social_name(account.getSeller().getSocial_name())
						.fantasy_name(account.getSeller().getFantasy_name()).build());
			} else {
				this.accounts.setConsumer(ConsumerResponse.builder().id(account.getId())
						.user_id(account.getUser().getId()).username(account.getUsername()).build());
			}

		}

	}

}
