package com.picpay.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.Account;
import com.picpay.users.model.Seller;
import com.picpay.users.model.User;
import com.picpay.users.model.request.CreateConsumerRequest;
import com.picpay.users.model.request.CreateSellerRequest;
import com.picpay.users.model.response.ConsumerResponse;
import com.picpay.users.model.response.GenericError;
import com.picpay.users.model.response.SellerResponse;
import com.picpay.users.repository.AccountRepository;
import com.picpay.users.repository.SellerRepository;
import com.picpay.users.repository.UserRepository;
import com.picpay.users.utils.CodeErrorEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	private final UserRepository userRepository;
	
	private final SellerRepository sellerRepository;

	@Override
	public List<Account> findAll() {
		return this.accountRepository.findAll();
	}

	@Override
	public Optional<Account> findById(Long id) {
		return this.accountRepository.findById(id);
	}

	@Override
	public ConsumerResponse saveConsumer(CreateConsumerRequest consumerRequest) throws BusinessException {

		Account account;

		User user = this.userRepository.findById(consumerRequest.getUser_id()).get();

		/* Valida consumer */

		if (user == null)
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.USUARIO_NAO_EXISTENTE.getValue()),
					String.format("Usuário de id %s não existe", consumerRequest.getUser_id())));

		if (this.accountRepository.existsByUsername(consumerRequest.getUsername()))
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.USERNAME_EXISTENTE.getValue()),
					String.format("Username %s já existe", consumerRequest.getUsername())));

		List<Account> accounts = this.accountRepository.findByUser_Id(consumerRequest.getUser_id());

		if (accounts != null && !accounts.isEmpty()) {
			if (accounts.size() > 1)
				throw new BusinessException(new GenericError(
						String.valueOf(CodeErrorEnum.TIPO_CONTA_CADASTRADO.getValue()),
						String.format("O usuário já possui tipo de conta Consumidor", consumerRequest.getUsername())));
			else if (accounts.get(0).getSeller() == null)
				throw new BusinessException(new GenericError(
						String.valueOf(CodeErrorEnum.TIPO_CONTA_CADASTRADO.getValue()),
						String.format("O usuário já possui tipo de conta Consumidor", consumerRequest.getUsername())));
		}

		/* Fim valida consumer */

		account = Account.builder()
				.username(consumerRequest.getUsername())
				.user(user)
				.build();

		account = this.accountRepository.save(account);

		if (account != null)
			return ConsumerResponse.builder()
					.id(account.getId())
					.user_id(account.getUser().getId())
					.username(account.getUsername())
					.build();
		else
			return null;
	}

	@Override
	public SellerResponse saveSeller(CreateSellerRequest sellerRequest) throws BusinessException {
		
		Seller seller;
		Account account;
		
		User user = this.userRepository.findById(sellerRequest.getUser_id()).get();

		/* Valida seller */

		if (user == null)
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.USUARIO_NAO_EXISTENTE.getValue()),
					String.format("Usuário de id %s não existe", sellerRequest.getUser_id())));

		if (this.accountRepository.existsByUsername(sellerRequest.getUsername()))
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.USERNAME_EXISTENTE.getValue()),
					String.format("Username %s já existe", sellerRequest.getUsername())));

		List<Account> accounts = this.accountRepository.findByUser_Id(sellerRequest.getUser_id());

		if (accounts != null && !accounts.isEmpty()) {
			if (accounts.size() > 1)
				throw new BusinessException(new GenericError(
						String.valueOf(CodeErrorEnum.TIPO_CONTA_CADASTRADO.getValue()),
						String.format("O usuário já possui tipo de conta Lojista", sellerRequest.getUsername())));
			else if (accounts.get(0).getSeller() != null)
				throw new BusinessException(new GenericError(
						String.valueOf(CodeErrorEnum.TIPO_CONTA_CADASTRADO.getValue()),
						String.format("O usuário já possui tipo de conta Lojista", sellerRequest.getUsername())));
		}

		/* Fim valida seller */		
		
		seller = Seller.builder()
				.cnpj(sellerRequest.getCnpj())
				.fantasy_name(sellerRequest.getFantasy_name())
				.social_name(sellerRequest.getSocial_name())
				.build();
		
		account = Account.builder()
				.username(sellerRequest.getUsername())
				.user(user)
				.seller(seller)
				.build();
		
		seller.setAccount(account);

		seller = this.sellerRepository.save(seller);

		if (seller != null)
			return SellerResponse.builder()
					.id(account.getId())
					.user_id(account.getUser().getId())
					.username(account.getUsername())
					.cnpj(seller.getCnpj())
					.social_name(seller.getSocial_name())
					.fantasy_name(seller.getFantasy_name())
					.build();
		else
			return null;
	}

}
