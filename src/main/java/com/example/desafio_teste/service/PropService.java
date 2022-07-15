package com.example.desafio_teste.service;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.repository.DistrictRepo;
import com.example.desafio_teste.repository.PropRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class PropService implements PropServiceInterface {

    @Autowired
    PropRepo repoProp;

    @Autowired
    DistrictRepo repoDistrict;

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
    public BigDecimal calculatePropPriceByDistrict(String propName) {
        Prop prop = repoProp.getByName(propName);
        District district = repoDistrict.getByName(prop.getDistrictName());

        BigDecimal total = this.calculateTotalArea(prop.getName()).multiply(district.getValueDistrictM2());
        return total;
    }

    @Override
    public Room getBiggestRoom(String propName) {
        Prop prop = repoProp.getByName(propName);
        RoomService roomService = new RoomService();

        Room bigRoom = prop.getRoomList()
                .stream()
                .max(Comparator.comparing(roomService::calculateArea)).get();
        return bigRoom;
    }

    public List<RoomDetailsDto> areaPerRoom(String propName) {
        Prop prop = repoProp.getByName(propName);
        RoomService roomService = new RoomService();
        return prop.getRoomList().stream()
                .map(r -> new RoomDetailsDto(r.getName(), roomService.calculateArea(r)))
                .collect(Collectors.toList());

    }

}
