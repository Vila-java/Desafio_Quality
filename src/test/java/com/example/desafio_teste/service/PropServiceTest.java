package com.example.desafio_teste.service;

import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.repository.PropRepo;
import com.example.desafio_teste.repository.RoomRepo;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropServiceTest {

    @InjectMocks
    PropService propService;

    @Mock
    PropRepo propRepo;

    @BeforeEach
    public void setup() {

    }

    @Test
    @DisplayName("verifica se o total de metros quadrados por propriedade está correto")
    void calculateTotalArea_sumRoomsArea_whenPropExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());

        BigDecimal expected = new BigDecimal("11.0");

        String propName = "Casa";
        BigDecimal result = propService.calculateTotalArea(propName);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Retorna uma exceção caso a Propriedade não exista")
    void calculateTotalArea_returnException_whenPropNotExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenThrow(RuntimeException.class);

        String propName = "Casa";

        assertThrows(RuntimeException.class, () -> propService.calculateTotalArea(propName));
    }

//    @Test
//    @DisplayName("verifica se o valor da propriedade está correto")
//    void calculatePricePerDistrict_multiplyTotalAreaPerPrice_whenPropExist() {
//        List<Room> roomList = new ArrayList<>();
//        roomList.add(new Room("Quarto", 1.5, 2.0));
//        roomList.add(new Room("Cozinha", 4.0, 2.0));
//
//        BigDecimal expected = new BigDecimal("61600.00");
//
//        District district = new District("Campeche", new BigDecimal("5600.0"));
//        Prop prop = new Prop("Casa", district, roomList);
//
//        PropService propService = new PropService();
//        BigDecimal result = propService.calculatePricePerDistrict(prop);
//
//        assertThat(result).isEqualTo(expected);
//    }
}