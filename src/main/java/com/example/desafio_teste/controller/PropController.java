package com.example.desafio_teste.controller;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.service.PropServiceInterface;
import com.example.desafio_teste.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/prop")
public class PropController {

    @Autowired
    private PropServiceInterface propService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/biggestroom")
    public ResponseEntity<Room> getBiggestRoom (@RequestBody @Validated Prop prop){
        return ResponseEntity.ok(roomService.getBiggestRoom(prop));
    }

    @GetMapping("/calculateArea/{propName}")
    public ResponseEntity<BigDecimal> calculateTotalArea(@PathVariable String propName) {
        return ResponseEntity.ok(propService.calculateTotalArea(propName));
    }
}
