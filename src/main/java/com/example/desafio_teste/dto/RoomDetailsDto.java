package com.example.desafio_teste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailsDto {
    private String name;
    private BigDecimal area;
}
