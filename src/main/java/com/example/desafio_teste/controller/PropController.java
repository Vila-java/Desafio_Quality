package com.example.desafio_teste.controller;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.service.PropServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * The type Prop controller.
 */
@RestController
@RequestMapping("/prop")
public class PropController {

    @Autowired
    private PropServiceInterface propService;
    @PostMapping("/createProps")
    public ResponseEntity<Prop> createProps(@RequestBody @Valid Prop prop) {
        return ResponseEntity.ok(prop);
    }

    @PostMapping("/createDistrict")
    public ResponseEntity<District> createDistrict(@RequestBody @Valid District district) {
        return ResponseEntity.ok(district);
    }

    /**
     * Determina a área total da propriedade
     *
     * @param propName nome da propriedade
     * @return valor da área da propriedade
     */
    @GetMapping("/calculateArea/{propName}")
    public ResponseEntity<BigDecimal> calculateTotalArea(@PathVariable String propName) {
        return ResponseEntity.ok(propService.calculateTotalArea(propName));
    }

    /**
     * Determina o preço total da propriedade.
     *
     * @param propName nome da propriedade
     * @return valor total da propriedade de acordo com o bairro
     */
    @GetMapping("/calculatePropPriceByDistrict/{propName}")
    public ResponseEntity<BigDecimal> calculatePropPriceByDistrict(@PathVariable String propName) {
        return ResponseEntity.ok(propService.calculatePropPriceByDistrict(propName));
    }

    /**
     * Determina o maior cômodo da propriedade
     *
     * @param propName nome da propriedade
     * @return maior sala da propriedade
     */
    @GetMapping("/biggestroom/{propName}")
    public ResponseEntity<Room> getBiggestRoom (@PathVariable String propName){
        return ResponseEntity.ok(propService.getBiggestRoom(propName));
    }

    /**
     * Determina área de cada cômodo da propriedade
     *
     * @param propName nome da propriedade
     * @return listas de cômodos com nome e área total
     */
    @GetMapping("/areaPerRoom/{propName}")
    public ResponseEntity<List<RoomDetailsDto>> areaPerRoom(@PathVariable String propName){
        return ResponseEntity.ok(propService.areaPerRoom(propName));
    }
}
