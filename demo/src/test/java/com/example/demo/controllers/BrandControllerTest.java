package com.example.demo.controllers;

import com.example.demo.models.Brand;
import com.example.demo.models.Model;
import com.example.demo.repositories.BrandRepository;
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
public class BrandControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BrandRepository repository;

    @Autowired
    private DatabaseClient databaseClient;


    private List<Brand> getData() {
        return Arrays.asList(new Brand("name1", "logo1"),
                new Brand("name2", "logo2"),
                new Brand("name3", "logo3"));
    }

    @BeforeEach
    public void setup() {
        List<String> statements = Arrays.asList("DROP TABLE IF EXISTS BRAND ;",
                "CREATE TABLE BRAND (id SERIAL PRIMARY KEY, name varchar (255), logo varchar (255));");

        statements.forEach(it -> databaseClient.execute(it)
                .fetch()
                .rowsUpdated()
                .block());

        repository.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(repository::save)
                .doOnNext(model -> {
                    System.out.println("Brand Inserted from BrandControllerTest: " + model.toString());
                })
                .blockLast();

    }

    @Test
    public void testGetAllValidateCount() {
        webTestClient.get().uri("/api/brands").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(Brand.class)
                .hasSize(3)
                .consumeWith(brand -> {
                    List<Brand> brands = brand.getResponseBody();
                    brands.forEach(u -> {
                        assertTrue(u.getId() != null);
                    });
                });
    }

    @Test
    public void testGetAllValidateResponse() {
        Flux<Model> modelFlux = webTestClient.get().uri("/api/brands").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(Model.class)
                .getResponseBody();
        StepVerifier.create(modelFlux.log("Receiving values !!!"))
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    public void testGetById() {
        webTestClient.get().uri("/api/brands/2")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("name2");
    }

    @Test
    public void testGetById_NotFound() {
        webTestClient.get().uri("/api/brands/18")
                .exchange().expectStatus().isNotFound();
    }

    @Test
    public void testCreate() {
        Brand model = new Brand("name4", "logo4");
        webTestClient.post().uri("/api/brands").contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(model), Model.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("name4")
                .jsonPath("$.logo").isEqualTo("logo4");
    }

    @Test
    public void testDelete() {
        webTestClient.delete().uri("/api/brands/1")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    public void testDelete_notFound() {
        webTestClient.delete().uri("/api/brands/10")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testUpdate() {
        Brand brand = new Brand("name10", "logo10");
        webTestClient.put().uri("/api/brands/1")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(brand), Brand.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("name10")
                .jsonPath("$.logo").isEqualTo("logo10");
        ;

    }

    @Test
    public void testUpdate_notFound() {
        Brand brand = new Brand("name20", "logo20");
        webTestClient.put().uri("/api/brands/6")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(brand), Brand.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}