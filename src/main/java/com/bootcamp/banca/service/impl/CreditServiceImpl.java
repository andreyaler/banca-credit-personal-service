package com.bootcamp.banca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.banca.model.Credit;
import com.bootcamp.banca.model.entity.CustomerPersonal;
import com.bootcamp.banca.repository.CreditPersonalRepository;
import com.bootcamp.banca.service.CreditService;
import com.bootcamp.banca.util.Util;

import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

@Service
public class CreditServiceImpl implements CreditService {

	@Autowired
	Webclients webclients;

	@Autowired
	CreditPersonalRepository creditPersonRepository;

	@Override
	public Mono<Credit> createCredit(String documentNumber, Double amountCredit) {
		return webclients.getClient(documentNumber).flatMap(responseCreditClient -> {
			Map<String, Object> body = responseCreditClient.getBody();
			Credit credit = new Credit();

			credit.setAmountCredit(amountCredit);
			credit.setCreditNumber(Util.generateAccountNumber());
			credit.setMaintenanceFee(0.3);
			credit.setClient(new CustomerPersonal((String) body.get("documentNumber"), (String) body.get("profile")));
			credit.setCreatedAt(new Date());
			credit.setIsActive(true);

			return creditPersonRepository.findByClientDocumentNumberAndIsActive(documentNumber, true)
					.flatMap(Mono::just).switchIfEmpty(creditPersonRepository.save(credit));

		});
	}

	@Override
	public Mono<Credit> getCredit(String documentNumber) {
		return creditPersonRepository.findByClientDocumentNumberAndIsActive(documentNumber, true);
	}

	@Override
	public Mono<Credit> updateCredit(String documentNumber, Double amountCredit) {
		return creditPersonRepository.findByClientDocumentNumberAndIsActive(documentNumber, true)
				.flatMap(creditEntity -> {
					creditEntity.setAmountCredit(amountCredit);
					creditEntity.setModifiedAt(new Date());

					return creditPersonRepository.save(creditEntity);
				}).switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<Credit> deleteCredit(String documentNumber) {
		return creditPersonRepository.findByClientDocumentNumberAndIsActive(documentNumber, true)
				.flatMap(creditEntity -> {
					creditEntity.setIsActive(false);
					creditEntity.setModifiedAt(new Date());

					return creditPersonRepository.save(creditEntity);
				}).switchIfEmpty(Mono.empty());
	}

	@Override
	public Mono<Credit> activateCredit(String documentNumber, String numberCredit) {
		return creditPersonRepository
				.findByClientDocumentNumberAndCreditNumberAndIsActive(documentNumber, numberCredit, false)
				.flatMap(creditEntity -> {
					creditEntity.setIsActive(true);
					creditEntity.setModifiedAt(new Date());

					return creditPersonRepository.findByClientDocumentNumberAndIsActive(documentNumber, true)
							.flatMap(Mono::just).switchIfEmpty(creditPersonRepository.save(creditEntity));
				}).switchIfEmpty(Mono.empty());
	}
}