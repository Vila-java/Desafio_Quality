package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class RoomServiceTest {

//    @Autowired
//    RoomService service;
    @Test
    void calculateArea_calculatedRoomArea_whenRoomExist() {
        Room room = TestUtilsGenerator.getRoom();
        BigDecimal expected = new BigDecimal("1.92");

        RoomService service = new RoomService();

        BigDecimal result = service.calculateArea(room);

        assertThat(result).isEqualTo(expected);

    }
}