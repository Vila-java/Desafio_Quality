package com.example.desafio_teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {


	@NotBlank(message = "O campo não pode estar vazio!")
	@Pattern(regexp = "([A-Z]|[0-9])[\\\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
			message = "O nome do cômodo deve começar com uma letra maiúscula")
	@Size(max = 30, message = "O comprimento do nome do cômodo não pode exceder 30 caracteres")
	private String name;

	@NotEmpty(message = "A largura do cômodo não pode estar vazia!")
	@DecimalMax(value = "25", message = "A largura máxima permitida por cômodo é de 25 metros!")
	private Double width;

	@NotNull(message = "O comprimento do cômodo não pode estar vazio")
	@DecimalMax(value = "33", message = "O comprimento máximo permitido por cômodo é de 33 metros!")
	private Double length;
}
