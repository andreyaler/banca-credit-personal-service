package com.bootcamp.banca.model;

import com.bootcamp.banca.model.entity.CustomerPersonal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "creditPersonal")
@ToString
@NoArgsConstructor
public class Credit {
	@Id
	private String _id;

	private String creditNumber;
	private CustomerPersonal client;
	private Double amountCredit;
	private Double maintenanceFee;
	private Boolean isActive;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date modifiedAt;
}