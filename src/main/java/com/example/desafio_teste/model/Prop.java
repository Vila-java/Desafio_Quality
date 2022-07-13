package com.example.desafio_teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prop {

	@NotBlank(message = "O nome da propriedade não pode ser vazio!")
	@Pattern(regexp = "([A-Z]|[0-9])[\\\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
			message = "O nome da propriedade deve começar com uma letra maiúscula")
	@Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
	private String name;

	@NotNull(message = "O campo bairro não pode ser nulo")
	private District district;

	@NotEmpty(message = "A lista de cômodos não pode estar vazia!")
	private List<@Valid Room> roomList;
}
