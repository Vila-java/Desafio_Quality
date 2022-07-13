package com.example.desafio_teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prop {

	private String name;
	private District district;
	private List<Room> roomList;
}
