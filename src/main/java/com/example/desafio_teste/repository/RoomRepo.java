package com.example.desafio_teste.repository;

import com.example.desafio_teste.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepo {

    public static List<Room> createRooms() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));
        return roomList;
    }
}
