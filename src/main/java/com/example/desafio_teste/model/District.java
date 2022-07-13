package com.example.desafio_teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {

	@NotBlank(message = "O bairro não pode estar vazio!")
	@Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres")
	private String name;

	@NotEmpty(message = "O valor do metro quadrado no bairro não pode estar vazio!")
	@Digits(integer = 13, fraction = 2, message = "O valor do metro quadrado não pode exceder 13 dígitos!")
	private BigDecimal valueDistrictM2;

}
