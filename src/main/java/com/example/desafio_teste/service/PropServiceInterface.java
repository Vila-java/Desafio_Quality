package com.example.desafio_teste.service;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface PropServiceInterface {
    BigDecimal calculateTotalArea(String propName);
    BigDecimal calculatePropPriceByDistrict(String propName);
    Room getBiggestRoom(String propName);
    List<RoomDetailsDto> areaPerRoom(String propName);
}
