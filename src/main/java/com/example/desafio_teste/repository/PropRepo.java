package com.example.desafio_teste.repository;

import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.NameNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PropRepo {

    public List<Prop> createProps() {
        List<Room> roomList = RoomRepo.createRooms();
        District district = new District("Campeche", new BigDecimal("5600.0"));

        List<Prop> propList = new ArrayList<>();
        propList.add(new Prop("Casa", district, roomList));
        propList.add(new Prop("Apartamento", district, roomList));
        propList.add(new Prop("Casa 2", district, roomList));
        propList.add(new Prop("Casa 3", district, roomList));

        return propList;
    }

    public Prop getByName(String propName) {
        List<Prop> propList = createProps();

        Prop prop = propList.stream()
                .filter(p -> p.getName().equals(propName))
                .findFirst()
                .orElse(null);

        if (prop == null) {
            System.out.println("Exceção!");
        }

        return prop;
    }
}
