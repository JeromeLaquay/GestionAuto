package com.example.demo.services;

import com.example.demo.models.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

    Flux<Car> getAll();

    Mono<Car> getById(Long id);

    Mono<Car> add(Car car);

    Mono<Car> update(Long id, Car car);

    Mono<Void> deleteById(Long id);
}
