package com.example.desafio_teste.service;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implemnetaçao da classe PropService.
 */
public interface PropServiceInterface {
    /**
     * Calculate total area big decimal.
     *
     * @param propName the prop name
     * @return the big decimal
     */
    BigDecimal calculateTotalArea(String propName);

    /**
     * Calculate prop price by district big decimal.
     *
     * @param propName the prop name
     * @return the big decimal
     */
    BigDecimal calculatePropPriceByDistrict(String propName);

    /**
     * Gets biggest room.
     *
     * @param propName the prop name
     * @return the biggest room
     */
    Room getBiggestRoom(String propName);

    /**
     * Area per room list.
     *
     * @param propName the prop name
     * @return the list
     */
    List<RoomDetailsDto> areaPerRoom(String propName);
}
