package com.picpay.users.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.picpay.users.exception.BusinessException;
import com.picpay.users.model.Transaction;
import com.picpay.users.model.request.CreateTransactionRequest;
import com.picpay.users.model.response.GenericError;
import com.picpay.users.model.response.TransactionResponse;
import com.picpay.users.repository.TransactionRepository;
import com.picpay.users.utils.CodeErrorEnum;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	
	@Override
	public TransactionResponse findById(Long id) throws BusinessException {
		Optional<Transaction> transaction = this.transactionRepository.findById(id);
		
		if (!transaction.isPresent())
			throw new BusinessException(new GenericError(String.valueOf(CodeErrorEnum.TRANSACAO_NAO_EXISTENTE.getValue()),
					String.format("Transação de id %s não existe", id)));		
		
		return TransactionResponse.builder()
			.id(transaction.get().getId())
			.payee_id(transaction.get().getPayee_id())
			.payer_id(transaction.get().getPayer_id())
			.transaction_date(transaction.get().getTransaction_date())
			.value(transaction.get().getValue())					
			.build();
	}

	@Override
	public TransactionResponse save(CreateTransactionRequest transRequest) throws BusinessException {
		final String uri = "http://localhost:8080/authorization";
		Transaction transaction;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <String> entity = new HttpEntity<>(headers);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("value", transRequest.getValue());
		
		ResponseEntity<Boolean> responseEntity = new RestTemplate().exchange(uriBuilder.toUriString() , HttpMethod.GET, entity, Boolean.class);
		
		boolean transacaoAceita = responseEntity.getBody();
		if(transacaoAceita) {
			transaction = Transaction.builder()
					.payee_id(transRequest.getPayee_id())
					.payer_id(transRequest.getPayer_id())
					.transaction_date(new Date())
					.value(transRequest.getValue())
					.build();

			transaction = this.transactionRepository.save(transaction);

			if (transaction != null)
				return TransactionResponse.builder()
						.id(transaction.getId())
						.payee_id(transaction.getPayee_id())
						.payer_id(transaction.getPayer_id())
						.transaction_date(transaction.getTransaction_date())
						.value(transaction.getValue())
						.build();
			else
				return null;
		}

		return null;
	}

}
