package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;

/**
 * Implemnetaçao da classe RoomService.
 */
public interface RoomServiceInterface {
     /**
      * Método que calcula area toral de um cômodo.
      *
      * @param room cômodo
      * @return a área total
      */
     BigDecimal calculateArea(Room room);
}
