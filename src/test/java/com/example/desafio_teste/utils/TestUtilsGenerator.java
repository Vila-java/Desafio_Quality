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

        District district = new District("Campeche", new BigDecimal("5600.0"));

        Prop prop = new Prop("Casa", district.getName(), roomList);

        return prop;
    }

    public static Prop createPropWithPropNameEmpty() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        District district = new District("Campeche", new BigDecimal("5600.0"));

        Prop prop = new Prop("", district.getName(), roomList);

        return prop;
    }

    public static Prop createPropWithPropRoomListEmpty() {
        List<Room> roomList = new ArrayList<>();

        District district = new District("Campeche", new BigDecimal("5600.0"));

        Prop prop = new Prop("Casa", district.getName(), roomList);

        return prop;
    }

    public static Prop createPropWithPropDistrictEmpty() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        District district = new District("", new BigDecimal(""));

        Prop prop = new Prop("Casa", district.getName(), roomList);

        return prop;
    }

    public static Prop createPropWithPropNameInLowerCase() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Quarto", 1.5, 2.0));
        roomList.add(new Room("Cozinha", 4.0, 2.0));

        District district = new District("Campeche", new BigDecimal("5600.0"));

        Prop prop = new Prop("casa", district.getName(), roomList);

        return prop;
    }

    public static District createDistrict() {
        District district = new District("Campeche", new BigDecimal("5600.0"));
        return district;
    }

    public static District createDistrictWithEmptyDistrictName() {
        District district = new District("", new BigDecimal("5600.0"));
        return district;
    }

    public static District createDistrictWithDistrictNameLongerThan45Caracters() {
        District district = new District("São José do Vale do Rio Preto Vale do José São Preto Rio", new BigDecimal("5600.0"));
        return district;
    }

    public static District createDistrictWithValueM2Empty() {
        District district = new District("Campeche", null);
        return district;
    }

    public static District createDistrictWithValueM2LongerThan13Digits() {
        District district = new District("Campeche", new BigDecimal("123123123123123123123123123.0"));
        return district;
    }


    public static BigDecimal getTotalAreaProp() {
        return new BigDecimal("11.0");
    }

    public static BigDecimal getTotalPriceByDistrict () {
        return new BigDecimal("61600.00");
    }

    public static Room getRoom() {
        Room room = new Room("Quarto", 1.60, 1.20);

        return room;

    }

    public static Room returnBiggestRoom() {
        Room room = new Room("Cozinha", 4.0, 2.0);

        return room;

    }
}
