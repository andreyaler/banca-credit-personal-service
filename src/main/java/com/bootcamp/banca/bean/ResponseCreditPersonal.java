package com.bootcamp.banca.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResponseCreditPersonal {
	private String message;
	private String status;
	private Map<String, Object> body;
}