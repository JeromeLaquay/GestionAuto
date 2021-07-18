package com.example.demo.services;

import com.example.demo.models.Model;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModelService {

    Flux<Model> getAll();

    Mono<Model> getById(Long id);

    Mono<Model> add(Model model);

    Mono<Model> update(Long id,Model model);

    Mono<Model> deleteById(Long id);
}
