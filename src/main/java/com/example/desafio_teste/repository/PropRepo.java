package com.example.desafio_teste.repository;

import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.service.RoomService;
import com.example.desafio_teste.utils.UtilsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.NameNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PropRepo {

    public Prop getByName(String propName) {
        List<Prop> propList = UtilsGenerator.createProps();

        Prop prop = propList.stream()
                .filter(p -> p.getName().equals(propName))
                .findFirst()
                .orElse(null);

        if (prop == null) {
            throw new NotFoundException("Propriedade não encontrada.");
        }

        return prop;
    }
}
