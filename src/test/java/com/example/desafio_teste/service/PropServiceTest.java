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

class PropServiceTest {

    @Test
    @DisplayName("verifica se o total de metros quadrados por propriedade está correto")
    void calculateTotalArea_sumRoomArea_whenRoomListExist() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        BigDecimal expected = new BigDecimal("11.0");

        District district = new District("Campeche", new BigDecimal("5600.00"));
        Prop prop = new Prop("Casa", district, roomList);

        PropService propService = new PropService();
        BigDecimal result = propService.calculateTotalArea(prop);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("verifica se o valor da propriedade está correto")
    void calculatePricePerDistrict_multiplyTotalAreaPerPrice_whenPropExist() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        BigDecimal expected = new BigDecimal("61600.00");

        District district = new District("Campeche", new BigDecimal("5600.0"));
        Prop prop = new Prop("Casa", district, roomList);

        PropService propService = new PropService();
        BigDecimal result = propService.calculatePricePerDistrict(prop);

        assertThat(result).isEqualTo(expected);
    }
}