package com.example.desafio_teste.service;

import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoomServiceTest {

    @Test
    void calculateArea_calculatedRoomArea_whenRoomExist() {
        Room room = new Room("Quarto", 1.60, 2.0);
        BigDecimal expected = new BigDecimal("3.2");

        RoomService service = new RoomService();
        BigDecimal result = service.calculateArea(room);

        assertThat(result).isEqualTo(expected);

    }


    @Test
    @DisplayName("Retorna o maior comodo da propriedade.")
    void returnBiggestRoom() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));
        roomList.add(new Room("Sacada", 5.0, 5.0));

        RoomService roomService = new RoomService();

        District district = new District("Bela Vista", new BigDecimal("10600.00"));
        Prop prop = new Prop("Apartamento", district, roomList);

        Room expected = new Room("Sacada", 5.0, 5.0);
        Room result = roomService.getBiggestRoom(prop);

    }

}