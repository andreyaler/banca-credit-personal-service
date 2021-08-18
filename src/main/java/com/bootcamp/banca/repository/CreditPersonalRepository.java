package com.bootcamp.banca.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.banca.model.Credit;

import reactor.core.publisher.Mono;

public interface CreditPersonalRepository extends ReactiveMongoRepository<Credit, String> {
	public Mono<Credit> findByClientDocumentNumberAndIsActive(String documentNumber, Boolean isActive);

	public Mono<Credit> findByClientDocumentNumberAndCreditNumberAndIsActive(String documentNumber, String creditNumber,
			Boolean isActive);
}