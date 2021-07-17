package com.example.demo.controllers;

import com.example.demo.models.Model;
import com.example.demo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private ModelService service;

    @Autowired
    ModelController(ModelService modelService){
        this.service = modelService;
    }

    @CrossOrigin
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Flux<ResponseEntity<Model>> getAll() {
        Flux<Model> models = service.getAll();
        return models.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<ResponseEntity<Model>> getById(@PathVariable("id") Long id) {
        Mono<Model> model = service.getById(id);
        return model.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ResponseEntity<Model>> add(@RequestBody @Valid Model model) {
        Mono<Model> result = service.add(model);
        return result.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @CrossOrigin
    @PutMapping()
    Mono<ResponseEntity<Model>> update(@RequestBody Model model) {
        Mono<Model> result = service.update(model);
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