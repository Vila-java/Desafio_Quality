package com.example.desafio_teste.integration;

import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.model.Room;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void calculateTotalArea_returnTotalArea_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/calculateArea/" + prop.getName();
        ResponseEntity<BigDecimal> response = testRestTemplate.exchange(url, HttpMethod.GET, null, BigDecimal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.getTotalAreaProp());

    }

    @Test
    void calculatePropPriceByDistrict() {
    }

    @Test
    void getBiggestRoom_returnBiggetRoom_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/biggestroom/" + prop.getName();
        ResponseEntity<Room> response = testRestTemplate.exchange(url, HttpMethod.GET, null, Room.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.returnBiggestRoom());

    }

    @Test
    void areaPerRoom() {
    }
}