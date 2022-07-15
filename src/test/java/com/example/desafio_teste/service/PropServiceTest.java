package com.example.desafio_teste.service;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.repository.DistrictRepo;
import com.example.desafio_teste.repository.PropRepo;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

/**
 * Classe de testes unitários da camada Service
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropServiceTest {

    /**
     * Injeçao de mock da classe PropService
     */
    @InjectMocks
    PropService propService;

    /**
     * Mocks da classe PropRepo
     */
    @Mock
    PropRepo propRepo;

    /**
     * Mock da classe DistrictRepo
     */
    @Mock
    DistrictRepo districtRepo;


    /**
     * Verifica se o total de metros quadrados por propriedade está correto
     */
    @Test
    @DisplayName("Verifica se o total de metros quadrados por propriedade está correto")
    void calculateTotalArea_sumRoomsArea_whenPropExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());

        BigDecimal expected = TestUtilsGenerator.getTotalAreaProp();

        String propName = "Casa";
        BigDecimal result = propService.calculateTotalArea(propName);

        assertThat(result).isEqualTo(expected);
    }

    /**
     * Retorna uma exceção caso a Propriedade não exista
     */
    @Test
    @DisplayName("Retorna uma exceção caso a Propriedade não exista")
    void calculateTotalArea_returnException_whenPropNotExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenThrow(new NotFoundException("Propriedade não encontrada.") {
                });

        String propName = "Casa";

        Exception ex = assertThrows(NotFoundException.class, () -> propService.calculateTotalArea(propName));
        assertEquals(ex.getMessage(), "Propriedade não encontrada.");
    }

    /**
     * Retorna o preço da propriedade correto se o bairro e propriedade existirem
     */
    @Test
    @DisplayName("Retorna o preço da propriedade correto se o bairro e propriedade existirem")
    void calculatePropPriceByDistrict_multiplyTotalAreaPerPrice_whenPropAndDistrictExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());
        BDDMockito.when(districtRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getDistrict());

        BigDecimal expected = TestUtilsGenerator.getTotalPriceByDistrict();

        String propName = "Casa";
        BigDecimal result = propService.calculatePropPriceByDistrict(propName);

        assertThat(result).isEqualTo(expected);
    }

    /**
     * Retorna uma exceção caso bairro não exista
     */
    @Test
    @DisplayName("Retorna uma exceção caso bairro não exista")
    void calculatePropPriceByDistrictThrowsNotFoundException_whenDistrictNotExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());
        BDDMockito.when(districtRepo.getByName(ArgumentMatchers.anyString()))
                .thenThrow(new NotFoundException("Bairro não encontrado.") {
                });

        String propName = "Casa";
        Exception ex = assertThrows(NotFoundException.class, () -> propService.calculatePropPriceByDistrict(propName));
        assertEquals(ex.getMessage(), "Bairro não encontrado.");

    }

    /**
     * Retorna o maior cômodo da propriedade
     */
    @Test
    @DisplayName("Retorna o maior cômodo da propriedade")
    void returnBiggestRoom_returnBiggestRoom_whenPropExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());

        Room expected = new Room("Cozinha", 4.0, 2.0);
        String propName = "Casa";

        Room result = propService.getBiggestRoom(propName);

        assertThat(result).isEqualTo(expected);

    }

    /**
     * Verifica se o total de metros quadrados por comodo está correto
     */
    @Test
    @DisplayName("Verifica se o total de metros quadrados por comodo está correto")
    void calculateTotalRoomArea_sumRoomsArea_whenRoomExist() {
        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
                .thenReturn(TestUtilsGenerator.getByNameWhenExist());

        List<RoomDetailsDto> roomDetailsDtoListExpected = new ArrayList<>();
        roomDetailsDtoListExpected.add(new RoomDetailsDto("Quarto", new BigDecimal("3.0")));
        roomDetailsDtoListExpected.add(new RoomDetailsDto("Cozinha", new BigDecimal("8.0")));

        String propName = "Quarto";
        List result = propService.areaPerRoom(propName);

        assertThat(result).isEqualTo(roomDetailsDtoListExpected);

    }

}