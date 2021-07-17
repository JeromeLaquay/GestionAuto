package com.example.demo.controllers;

import com.example.demo.models.Car;
import com.example.demo.models.Model;
import com.example.demo.services.CarService;
import com.example.demo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    private ModelService service;

    @CrossOrigin
    @GetMapping()
    Flux<Model> getAll() {
        return service.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    Mono<Model> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @CrossOrigin
    @PostMapping()
    Mono<Model> addTodo(@RequestBody Model model) {
        return service.addTodo(model);
    }

    @CrossOrigin
    @PutMapping()
    Mono<Model> updateTodo(@RequestBody Model model) {
        return service.updateTodo(model);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    Mono<Void> deleteById(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}