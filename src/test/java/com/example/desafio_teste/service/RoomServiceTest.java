package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    @Test
    void calculateArea_calculatedRoomArea_whenRoomExist() {
        Room room = new Room("Quarto", 1.60, 2.0);

        BigDecimal expected = new BigDecimal("3.2");

        RoomService service = new RoomService();
        BigDecimal result = service.calculateArea(room);

        assertThat(result).isEqualTo(expected);
    }
}