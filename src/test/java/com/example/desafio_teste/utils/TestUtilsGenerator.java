package com.example.desafio_teste.utils;

import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestUtilsGenerator {
    public static Prop getByNameWhenExist() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        District district = new District("Campeche", new BigDecimal("5600.00"));

        Prop prop = new Prop("Casa", district.getName(), roomList);

        return prop;
    }

    public static Room getRoom(){
        Room room = new Room("Quarto", 1.60,1.20);

        return room;
        
    }

    public static District getByDistrictNameWhenExist() {
        District district = new District("Lagoa da Conceição", new BigDecimal("6.600"));
        return district;
    }
}
