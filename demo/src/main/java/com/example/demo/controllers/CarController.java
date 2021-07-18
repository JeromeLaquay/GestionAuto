package com.example.demo.controllers;

import com.example.demo.models.Brand;
import com.example.demo.models.Car;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService service;

    @CrossOrigin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Flux<Car> getAll() {
        return service.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<Car>> getById(@PathVariable("id") Long id) {
        Mono<Car> car = service.getById(id);
        return car.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Car> add(@RequestBody @Valid Car car) {
        return service.add(car);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Mono<ResponseEntity<Car>> update(@PathVariable("id") Long id, @RequestBody @Valid Car car) {
        Mono<Car> result = service.update(id,car);
        return result.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}