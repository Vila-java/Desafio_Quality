package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;

public interface PropServiceInterface {
    public BigDecimal calculateTotalArea(String propName);
    public BigDecimal calculatePricePerDistrict(String propName);
    Room getBiggestRoom(String propName);
}
