package com.example.desafio_teste.controller;

import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.service.PropServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/prop")
public class PropController {

    @Autowired
    private PropServiceInterface propService;

    @GetMapping("/calculateArea/{propName}")
    public ResponseEntity<BigDecimal> calculateTotalArea(@PathVariable String propName) {
        return ResponseEntity.ok(propService.calculateTotalArea(propName));
    }

    @GetMapping("/calculatePricePerDistrict/{propName}")
    public ResponseEntity<BigDecimal> calculatePricePerDistrict(@PathVariable String propName) {
        return ResponseEntity.ok(propService.calculatePricePerDistrict(propName));
    }

    @GetMapping("/biggestroom/{propName}")
    public ResponseEntity<Room> getBiggestRoom (@PathVariable String propName){
        return ResponseEntity.ok(propService.getBiggestRoom(propName));
    }
}
