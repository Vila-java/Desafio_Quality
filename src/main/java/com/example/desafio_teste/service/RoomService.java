package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Comparator;

@Service
public class RoomService implements RoomServiceInterface {
    public BigDecimal calculateArea(@Valid Room room) {
        return BigDecimal.valueOf(room.getLength() * room.getWidth());
    }
}
