package com.example.demo.controllers;

import com.example.demo.models.Brand;
import com.example.demo.models.Car;
import com.example.demo.models.Model;
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
    Flux<Brand> getAll() {
        return service.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<Brand>> getById(@PathVariable("id") Long id) {
        Mono<Brand> brand = service.getById(id);
        return brand.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Brand> add(@RequestBody @Valid Brand brand) {
        return service.add(brand);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Mono<ResponseEntity<Brand>> update(@PathVariable("id") Long id, @RequestBody @Valid Brand brand) {
        Mono<Brand> result = service.update(id,brand);
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