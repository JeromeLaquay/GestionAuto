package com.example.demo.services;

import com.example.demo.models.Brand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BrandService {
    Flux<Brand> getAll();

    Mono<Brand> getById(Long id);

    Mono<Brand> add(Brand brand);

    Mono<Brand> update(Long id,Brand brand);

    Mono<Void> deleteById(Long id);
}
