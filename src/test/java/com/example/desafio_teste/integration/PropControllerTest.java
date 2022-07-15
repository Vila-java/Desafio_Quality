package com.example.desafio_teste.integration;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.service.RoomService;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Verifica se o total de metros quadrados por propriedade está correto")
    void calculateTotalArea_returnTotalArea_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/calculateArea/" + prop.getName();
        ResponseEntity<BigDecimal> response = testRestTemplate.exchange(url, HttpMethod.GET, null, BigDecimal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.getTotalAreaProp());
    }

    @Test
    void calculatePropPriceByDistrict() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + prop.getName();
        ResponseEntity<BigDecimal> response = testRestTemplate.exchange(url, HttpMethod.GET, null, BigDecimal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.getTotalPriceByDistrict());
    }

    @Test
    void calculatePropPriceByDistrict_WhenPropNameIsEmpty_ReturnNotFoundException() {
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/";
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void calculatePropPriceByDistrict_WhenPropNameIsIncorrect_ReturnNotFoundException() {
        String nomeInexistente = "Casinha";
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + nomeInexistente;
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Propriedade não encontrada.");
    }

    @Test
    void calculatePropPriceByDistrict_WhenDistrictIsEmpty_ReturnNotFoundException() {
        String propName = "Apartamento";
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + propName;
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Bairro não encontrado.");
    }

    @Test
    void getBiggestRoom() {
    }

    @Test
    @DisplayName("Verifica se o total de metros quadrados por comodo está correto")
    void areaPerRoom_returnListRoomNameAndPrice_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/areaPerRoom/" + prop.getName();

        ResponseEntity<List<RoomDetailsDto>> response = testRestTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RoomDetailsDto>>() {});

        RoomService roomService = new RoomService();

        List<RoomDetailsDto> expectedResponse = prop.getRoomList()
                .stream()
                .map(room -> new RoomDetailsDto(room.getName(), roomService.calculateArea(room)))
                .collect(Collectors.toList());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void areaPerRoom_returnException_whenPropNotExist() {
        String url = "http://localhost:" + port + "/prop/areaPerRoom/nomeErrado";

        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url,
                HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Propriedade não encontrada.");
    }
}