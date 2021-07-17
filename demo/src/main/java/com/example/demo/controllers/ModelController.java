package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping()
    Flux<Car> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    Mono<Car> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping()
    Mono<Car> addTodo(@RequestBody Car car) {
        return service.addTodo(car);
    }

    @PutMapping()
    Mono<Car> updateTodo(@RequestBody Car car) {
        return service.updateTodo(car);
    }

    @DeleteMapping("/{id}")
    Mono<Void> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}