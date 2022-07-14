package com.example.desafio_teste.service;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.repository.PropRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Data
public class PropService implements PropServiceInterface {

    @Autowired
    PropRepo repoProp;

    @Override
    public BigDecimal calculateTotalArea(String propName) {
        RoomService roomService = new RoomService();

        Prop prop = repoProp.getByName(propName);

         BigDecimal totalArea = prop.getRoomList()
                                        .stream()
                                        .map(p -> roomService.calculateArea(p))
                                        .reduce(BigDecimal.ZERO,
                                                (sumArea, eachValue) -> sumArea.add(eachValue) );

         return totalArea;
    }
    @Override
    public BigDecimal calculatePricePerDistrict(String propName) {
        Prop prop = repoProp.getByName(propName);

        BigDecimal total = this.calculateTotalArea(prop.getName()).multiply(prop.getDistrict().getValueDistrictM2());
        return total;
    }
}
