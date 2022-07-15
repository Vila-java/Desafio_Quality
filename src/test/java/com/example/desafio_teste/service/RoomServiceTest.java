package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Classe de testes unitários da camada Service
 */
class RoomServiceTest {


    /**
     * Calcula a area total do cômodo quado cômodo existir.
     */
    @Test
    void calculateArea_calculatedRoomArea_whenRoomExist() {
        Room room = TestUtilsGenerator.getRoom();
        BigDecimal expected = new BigDecimal("1.92");

        RoomService service = new RoomService();

        BigDecimal result = service.calculateArea(room);

        assertThat(result).isEqualTo(expected);

    }
}