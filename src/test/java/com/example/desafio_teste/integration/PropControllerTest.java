package com.example.desafio_teste.integration;

import com.example.desafio_teste.dto.RoomDetailsDto;
import com.example.desafio_teste.exception.NotFoundException;
import com.example.desafio_teste.model.District;
import com.example.desafio_teste.model.Prop;
import com.example.desafio_teste.service.RoomService;
import com.example.desafio_teste.utils.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import com.example.desafio_teste.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Classe que faz o teste integrado com a camada Controller.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropControllerTest {

    @LocalServerPort
    private int port;

    /**
     * Injeçao de dependencia da classe TestRestTemplate.
     */
    @Autowired
    TestRestTemplate testRestTemplate;

    /**
     * Calculate area total quando existe propriedade.
     */
    @Test
    void createProps() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/createProps";
        HttpEntity<Prop> httpEntity = new HttpEntity<>(prop);
        ResponseEntity<Prop> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Prop.class);

        Prop propResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(propResponse).isNotNull();
    }

    @Test
    void createPropsWithPropNameEmpty_ShouldReturnAnBadRequest() {
        Prop prop = TestUtilsGenerator.createPropWithPropNameEmpty();
        String url = "http://localhost:" + port + "/prop/createProps";
        HttpEntity<Prop> httpEntity = new HttpEntity<>(prop);
        ResponseEntity<Prop> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Prop.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createPropsWithRoomListEmpty_ShouldReturnAnBadRequest() {
        Prop prop = TestUtilsGenerator.createPropWithPropRoomListEmpty();
        String url = "http://localhost:" + port + "/prop/createProps";
        HttpEntity<Prop> httpEntity = new HttpEntity<>(prop);
        ResponseEntity<Prop> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Prop.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createPropsWithDistrictEmpty_ShouldReturnAnBadRequest() {
        Prop prop = TestUtilsGenerator.createPropWithPropRoomListEmpty();
        String url = "http://localhost:" + port + "/prop/createProps";
        HttpEntity<Prop> httpEntity = new HttpEntity<>(prop);
        ResponseEntity<Prop> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Prop.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createPropsWithPropNameInLowerCase_ShouldReturnAnBadRequest() {
        Prop prop = TestUtilsGenerator.createPropWithPropNameInLowerCase();
        String url = "http://localhost:" + port + "/prop/createProps";
        HttpEntity<Prop> httpEntity = new HttpEntity<>(prop);
        ResponseEntity<Prop> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, Prop.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createDistrict() {
        District district = TestUtilsGenerator.createDistrict();
        String url = "http://localhost:" + port + "/prop/createDistrict";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        ResponseEntity<District> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, District.class);

        District districtResponse = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(districtResponse).isNotNull();
    }

    @Test
    void createDistrictWithDistrictNameEmpty_ShouldReturnAnBadRequest() {
        District district = TestUtilsGenerator.createDistrictWithEmptyDistrictName();
        String url = "http://localhost:" + port + "/prop/createDistrict";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        ResponseEntity<District> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createDistrictWithDistrictNameLongerThan45Caracters_ShouldReturnAnBadRequest() {
        District district = TestUtilsGenerator.createDistrictWithDistrictNameLongerThan45Caracters();
        String url = "http://localhost:" + port + "/prop/createDistrict";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        ResponseEntity<District> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createDistrictWithDistrictValueEmpty_ShouldReturnAnBadRequest() {
        District district = TestUtilsGenerator.createDistrictWithValueM2Empty();
        String url = "http://localhost:" + port + "/prop/createDistrict";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        ResponseEntity<District> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void createDistrictWithDistrictValueM2LongerThan13Digits_ShouldReturnAnBadRequest() {
        District district = TestUtilsGenerator.createDistrictWithValueM2LongerThan13Digits();
        String url = "http://localhost:" + port + "/prop/createDistrict";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        ResponseEntity<District> response = testRestTemplate.exchange(url, HttpMethod.POST, httpEntity, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    /**
     * Calculate area total quando existe propriedade.
     */
    @Test
    @DisplayName("Verifica se o total de metros quadrados por propriedade está correto")
    void calculateTotalArea_returnTotalArea_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/calculateArea/" + prop.getName();
        ResponseEntity<BigDecimal> response = testRestTemplate.exchange(url, HttpMethod.GET, null, BigDecimal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.getTotalAreaProp());
    }

    /**
     * Calcula preço da propriedade por bairro.
     */
    @Test
    void calculatePropPriceByDistrict() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + prop.getName();
        ResponseEntity<BigDecimal> response = testRestTemplate.exchange(url, HttpMethod.GET, null, BigDecimal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.getTotalPriceByDistrict());
    }

    /**
     * Retorna um erro quando calcula preço da propriedade por bairro e nao possui nome da propriedade.
     */
    @Test
    void calculatePropPriceByDistrict_WhenPropNameIsEmpty_ReturnNotFoundException() {
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/";
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    /**
     * Retorna um erro quando calcula preço da propriedade por bairro e nome da propriedade está incorreto.
     */
    @Test
    void calculatePropPriceByDistrict_WhenPropNameIsIncorrect_ReturnNotFoundException() {
        String nomeInexistente = "Casinha";
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + nomeInexistente;
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Propriedade não encontrada.");
    }

    /**
     * Retorna um erro quando calcula preço da propriedade por bairro e nome da propriedade está vazio.
     */
    @Test
    void calculatePropPriceByDistrict_WhenDistrictIsEmpty_ReturnNotFoundException() {
        String propName = "Apartamento";
        String url = "http://localhost:" + port + "/prop/calculatePropPriceByDistrict/" + propName;
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Bairro não encontrado.");
    }

    /**
     * Retorna o maior cômodo.
     */
    @Test
    void getBiggestRoom_returnBiggetRoom_whenPropExist() {
        Prop prop = TestUtilsGenerator.getByNameWhenExist();
        String url = "http://localhost:" + port + "/prop/biggestroom/" + prop.getName();
        ResponseEntity<Room> response = testRestTemplate.exchange(url, HttpMethod.GET, null, Room.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestUtilsGenerator.returnBiggestRoom());

    }

    /**
     * Calcula a area por cômodo.
     */
    @Test
    void getBiggestRoom_returnNotFoundException_whenPropNotExist() {
        String propNameInexistente  = "Casinha";
        String url = "http://localhost:" + port + "/prop/biggestroom/" + propNameInexistente;
        ResponseEntity<NotFoundException> response = testRestTemplate.exchange(url, HttpMethod.GET, null, NotFoundException.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Propriedade não encontrada.");
    }

    /**
     * Calcula a area por cômodo.
     */
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