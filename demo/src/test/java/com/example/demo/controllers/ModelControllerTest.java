package com.example.demo.controllers;

import com.example.demo.models.Model;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.ModelService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(ModelService.class)
public class ModelControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    ModelRepository repository;

    @Test
    public void testGetByIdOk() {
        Model model = new Model("name1", 1L);
        Mono<Model> modelMono = Mono.just(model);

        when(repository.findById(1L)).thenReturn(modelMono);

        webTestClient.get()
                .uri("/api/models/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Model.class)
                .value(employee1 -> model.getName(), equalTo("name1"));
    }

    /** @Test public void testDeleteEmployeeById() {

    when(employeeService.deleteEmployeeById(1)).thenReturn(Mono.just("Employee with id 1 is deleted."));

    webTestClient.delete()
    .uri("/employees/1")
    .exchange()
    .expectStatus().isOk()
    .expectBody(String.class)
    .isEqualTo("Employee with id 1 is deleted.");

    }

     @Test public void testCreateEmployee() {

     Employee employee = Employee.builder().id(1).city("delhi").age(23).name("ABC").build();
     Mono<Employee> employeeMono = Mono.just(employee);
     when(employeeService.createEmployee(employee)).thenReturn(employeeMono);

     webTestClient.post()
     .uri("/employees")
     .contentType(MediaType.APPLICATION_JSON)
     .accept(MediaType.APPLICATION_JSON)
     .body(Mono.just(employee), Employee.class)
     .exchange()
     .expectStatus().isCreated();

     } **/
}