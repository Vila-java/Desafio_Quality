package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;

public interface PropServiceInterface {
    BigDecimal calculateTotalArea(String propName);
    BigDecimal calculatePricePerDistrict(String propName);
    Room getBiggestRoom(String propName);
}
