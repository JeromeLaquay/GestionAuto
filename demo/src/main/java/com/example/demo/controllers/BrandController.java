package com.example.demo.controllers;

import com.example.demo.models.Brand;
import com.example.demo.models.Car;
import com.example.demo.services.BrandService;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService service;


    @CrossOrigin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Flux<ResponseEntity<Brand>> getAll() {
        Flux<Brand> models = service.getAll();
        return models.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<Brand>> getById(@PathVariable("id") Long id) {
        Mono<Brand> model = service.getById(id);
        return model.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ResponseEntity<Brand>> add(@RequestBody @Valid Brand brand) {
        Mono<Brand> result = service.add(brand);
        return result.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @CrossOrigin
    @PutMapping()
    Mono<ResponseEntity<Brand>> update(@RequestBody Brand brand) {
        Mono<Brand> result = service.update(brand);
        return result.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Void> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}