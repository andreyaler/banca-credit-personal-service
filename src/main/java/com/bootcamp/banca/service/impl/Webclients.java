package com.bootcamp.banca.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bootcamp.banca.bean.ResponseCreditPersonal;

import reactor.core.publisher.Mono;

@Component
public class Webclients {
	String endpointCustomerP = "http://localhost:8090/";
	String pathSendAccount = "/p-customer?documentNumber=";

	private final WebClient client;

	public Webclients(WebClient.Builder builder) {
		this.client = builder.baseUrl(endpointCustomerP).build();
	}

	public Mono<ResponseCreditPersonal> getClient(String documentNumber) {
		return this.client.get().uri(pathSendAccount.concat(documentNumber)).retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono.empty())
				.bodyToMono(ResponseCreditPersonal.class);
	}
}