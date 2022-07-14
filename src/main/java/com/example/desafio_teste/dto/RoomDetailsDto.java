package com.example.desafio_teste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RoomDetailsDto {
    private String name;
    private BigDecimal area;
}
