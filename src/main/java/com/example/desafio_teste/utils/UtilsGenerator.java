package com.example.desafio_teste.utils;

import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.repository.RoomRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UtilsGenerator {

    public static List<Room> createRooms() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));
        return roomList;
    }

    public static List<Prop> createProps() {
        List<Room> roomList = createRooms();
        District district = new District("Campeche", new BigDecimal("5600.0"));

        List<Prop> propList = new ArrayList<>();
        propList.add(new Prop("Casa", district, roomList));
        propList.add(new Prop("Apartamento", district, roomList));
        propList.add(new Prop("Casa 2", district, roomList));
        propList.add(new Prop("Casa 3", district, roomList));

        return propList;
    }
}
