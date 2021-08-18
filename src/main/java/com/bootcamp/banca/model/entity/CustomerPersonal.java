package com.bootcamp.banca.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerPersonal {
	private String documentNumber;
	private String profile;
}