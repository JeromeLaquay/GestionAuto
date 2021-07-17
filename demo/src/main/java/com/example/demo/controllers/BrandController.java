package com.example.demo.controllers;

import com.example.demo.models.Brand;
import com.example.demo.models.Car;
import com.example.demo.services.BrandService;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @CrossOrigin
    @GetMapping()
    Flux<Brand> getAll() {
        return service.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    Mono<Brand> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @CrossOrigin
    @PostMapping()
    Mono<Brand> addTodo(@RequestBody Brand brand) {
        return service.addTodo(brand);
    }

    @CrossOrigin
    @PutMapping()
    Mono<Brand> updateTodo(@RequestBody Brand brand) {
        return service.updateTodo(brand);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    Mono<Void> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}