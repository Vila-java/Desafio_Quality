package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Prop;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PropService implements PropServiceInterface {

    public BigDecimal calculateTotalArea(Prop prop) {
        RoomService roomService = new RoomService();

         BigDecimal totalArea = prop.getRoomList()
                                        .stream()
                                        .map(p -> roomService.calculateArea(p))
                                        .reduce(BigDecimal.ZERO,
                                                (sumArea, eachValue) -> sumArea.add(eachValue) );

         return totalArea;
    }
}
