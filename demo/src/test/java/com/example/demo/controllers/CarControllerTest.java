package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureWebTestClient
public class CarControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CarRepository repository;

    @Autowired
    private DatabaseClient databaseClient;


    private List<Car> getData() {
        return Arrays.asList(new Car(1990, "bleu", 50000,null, 1L),
                new Car(1990, "rouge", 23654,"",2L),
                new Car(1990, "bleu", 9875,"moi",3L));
    }

    @BeforeEach
    public void setup() {
        List<String> statements = Arrays.asList("DROP TABLE IF EXISTS CAR ;",
                "CREATE TABLE CAR (id SERIAL PRIMARY KEY, year INTEGER, color varchar (255),mileage INTEGER, owner varchar (255), model_id INTEGER);");

        statements.forEach(it -> databaseClient.execute(it)
                .fetch()
                .rowsUpdated()
                .block());

        repository.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(repository::save)
                .doOnNext(model -> {
                    System.out.println("Car Inserted from CarControllerTest: " + model.toString());
                })
                .blockLast();

    }

    @Test
    public void testGetAllValidateCount() {
        webTestClient.get().uri("/api/cars").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(Car.class)
                .hasSize(3)
                .consumeWith(brand -> {
                    List<Car> brands = brand.getResponseBody();
                    brands.forEach(u -> {
                        assertTrue(u.getId() != null);
                    });
                });
    }

    @Test
    public void testGetAllValidateResponse() {
        Flux<Car> carFlux = webTestClient.get().uri("/api/cars").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(Car.class)
                .getResponseBody();
        StepVerifier.create(carFlux.log("Receiving values !!!"))
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    public void testGetById() {
        webTestClient.get().uri("/api/cars/2")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.color").isEqualTo("rouge");
    }

    @Test
    public void testGetById_NotFound() {
        webTestClient.get().uri("/api/cars/18")
                .exchange().expectStatus().isNotFound();
    }

    @Test
    public void testCreate() {
        Car model = new Car(1990, "bleu", 9875,"moi",1L);
        webTestClient.post().uri("/api/cars").contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(model), Car.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.color").isEqualTo("bleu")
                .jsonPath("$.year").isEqualTo("1990");
    }

    @Test
    public void testDelete() {
        webTestClient.delete().uri("/api/cars/1")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    public void testDelete_notFound() {
        webTestClient.delete().uri("/api/cars/10")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testUpdate() {
        Car car = new Car(1990, "bleu", 9875,"moi",1L);
        webTestClient.put().uri("/api/cars/1")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(car), Car.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.color").isEqualTo("bleu")
                .jsonPath("$.year").isEqualTo(1990);
        ;

    }

    @Test
    public void testUpdate_notFound() {
        Car car = new Car(1990, "bleu", 9875,"moi",1L);
        webTestClient.put().uri("/api/cars/6")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(car), Car.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}
