package com.example.demo.controllers;

import com.example.demo.models.Model;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ModelControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ModelRepository repository;

    @Autowired
    private ModelService service;


    @Autowired
    private DatabaseClient databaseClient;


    private List<Model> getData() {
        return Arrays.asList(new Model("name1", 1L),
                new Model("name2", 2L),
                new Model("name3", 3L));
    }

    @BeforeEach
    public void setup() {
        List<String> statements = Arrays.asList("DROP TABLE IF EXISTS MODEL ;",
                "CREATE TABLE MODEL (id SERIAL PRIMARY KEY, name varchar (255), brand_id INTEGER);");

        statements.forEach(it -> databaseClient.execute(it)
                .fetch()
                .rowsUpdated()
                .block());

        repository.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(repository::save)
                .doOnNext(model -> {
                    System.out.println("Model Inserted from ModelControllerTest: " + model.toString());
                })
                .blockLast();

    }

    @Test
    public void testGetAllValidateCount() {
        webTestClient.get().uri("/api/models").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(Model.class)
                .hasSize(3)
                .consumeWith(model -> {
                    List<Model> models = model.getResponseBody();
                    models.forEach(u -> {
                        assertTrue(u.getId() != null);
                    });
                });
    }
    @Test
    public void testGetAllValidateResponse(){
        Flux<Model> modelFlux = webTestClient.get().uri("/api/models").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(Model.class)
                .getResponseBody();
        StepVerifier.create(modelFlux.log("Receiving values !!!"))
                .expectNextCount(3)
                .verifyComplete();

    }
    @Test
    public void testGetById(){
        webTestClient.get().uri("/api/models/2")
                .exchange().expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("name2");
    }
    @Test
    public void testGetById_NotFound(){
        webTestClient.get().uri("/api/models/18")
                .exchange().expectStatus().isNotFound();
    }
    @Test
    public void testCreate(){
        Model model = new Model("name4",6L);
        webTestClient.post().uri("/api/models").contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(model),Model.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("name4");
    }
    @Test
    public void testDelete(){
        webTestClient.delete().uri("/api/models/1")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }
    @Test
    public void testUpdate(){
        Model model = new Model("name10",10L);
        webTestClient.put().uri("/api/models/1")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(model),Model.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("name10");
    }
    @Test
    public void testUpdate_notFound(){
        Model model = new Model("name20",20L);
        webTestClient.put().uri("/api/models/6")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(model),Model.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}