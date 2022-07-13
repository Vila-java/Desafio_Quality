package com.example.desafio_teste.controller;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.service.PropServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/prop")
public class PropController {
    @Autowired
    PropServiceInterface propService;

    @GetMapping("/calculateArea")
    public ResponseEntity<BigDecimal> calculateTotalArea(@RequestBody @Valid Prop prop) {
        return ResponseEntity.ok(propService.calculateTotalArea(prop));
    }
}
