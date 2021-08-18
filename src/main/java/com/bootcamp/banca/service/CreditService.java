package com.bootcamp.banca.service;

import com.bootcamp.banca.model.Credit;

import reactor.core.publisher.Mono;

public interface CreditService {
	public Mono<Credit> createCredit(String documentNumber, Double amountCredit);

	public Mono<Credit> getCredit(String documentNumber);

	public Mono<Credit> updateCredit(String documentNumber, Double amountCredit);

	public Mono<Credit> deleteCredit(String documentNumber);

	public Mono<Credit> activateCredit(String documentNumber, String numberCredit);
}