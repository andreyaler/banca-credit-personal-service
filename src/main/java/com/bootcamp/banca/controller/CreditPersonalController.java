package com.bootcamp.banca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.banca.service.CreditService;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/credit")
public class CreditPersonalController {
	@Autowired
	CreditService creditService;

	@PostMapping("")
	public Mono<ResponseEntity<Map<String, Object>>> createCredit(@RequestParam String documentNumber,
			@RequestParam Double amountCredit) {
		return creditService.createCredit(documentNumber, amountCredit).flatMap(creditEntity -> {
			Map<String, Object> response = new HashMap<>();

			response.put("message", "Se creó la linea de crédito");
			response.put("status", HttpStatus.OK.value());
			response.put("body", creditEntity);

			return Mono.just(ResponseEntity.ok().body(response));
		}).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("")
	public Mono<ResponseEntity<Map<String, Object>>> getCredit(@RequestParam String documentNumber) {
		return creditService.getCredit(documentNumber).flatMap(creditEntity -> {
			Map<String, Object> response = new HashMap<>();

			response.put("message", "El cliente presenta estos datos");
			response.put("status", HttpStatus.OK.value());
			response.put("body", creditEntity);

			return Mono.just(ResponseEntity.ok().body(response));
		}).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("")
	public Mono<ResponseEntity<Map<String, Object>>> updateCredit(@RequestParam String documentNumber,
			@RequestParam Double amountCredit) {
		return creditService.updateCredit(documentNumber, amountCredit).flatMap(creditEntity -> {
			Map<String, Object> response = new HashMap<>();

			response.put("message", "Se actualizó los datos del cliente");
			response.put("status", HttpStatus.OK.value());
			response.put("body", creditEntity);

			return Mono.just(ResponseEntity.ok().body(response));
		}).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("")
	public Mono<ResponseEntity<Map<String, Object>>> deleteCredit(@RequestParam String documentNumber) {
		return creditService.deleteCredit(documentNumber).flatMap(creditEntity -> {
			Map<String, Object> response = new HashMap<>();

			response.put("message", "Se eliminó el credito del cliente");
			response.put("status", HttpStatus.OK.value());
			// response.put("body", creditEntity);

			return Mono.just(ResponseEntity.ok().body(response));
		}).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("/activate")
	public Mono<ResponseEntity<Map<String, Object>>> activateCredit(@RequestParam String documentNumber,
			@RequestParam String creditNumber) {
		return creditService.activateCredit(documentNumber, creditNumber).flatMap(creditEntity -> {
			Map<String, Object> response = new HashMap<>();

			response.put("message", "Se activo el credito del cliente : " + documentNumber);
			response.put("status", HttpStatus.OK.value());
			response.put("body", creditEntity);

			return Mono.just(ResponseEntity.ok().body(response));
		}).defaultIfEmpty(ResponseEntity.notFound().build());
	}
}