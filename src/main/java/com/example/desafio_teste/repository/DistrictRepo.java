package com.example.desafio_teste.repository;

import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.District;
import com.example.desafio_teste.utils.UtilsGenerator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepo {
    public District getByName(String districtName) {
        List<District> districtList = UtilsGenerator.createDistricts();

        District district = districtList.stream()
                .filter(p -> p.getName().equals(districtName))
                .findFirst()
                .orElse(null);

        if (district == null) {
            throw new NotFoundException("Bairro não encontrado.");
        }

        return district;
    }
}
