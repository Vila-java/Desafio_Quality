package com.example.desafio_teste.repository;

import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.utils.UtilsGenerator;
import org.springframework.stereotype.Repository;

import java.util.List;

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
