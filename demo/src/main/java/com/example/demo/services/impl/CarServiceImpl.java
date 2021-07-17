package com.example.demo.services.impl;

import com.example.demo.models.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    public Flux<Car> getAll() {
        return repository.findAll();
    }

    public Mono<Car> getById(Long id) {
        return repository.findById(id);
    }

    public Mono<Car> addTodo(Car car) {
        return repository.save(car);
    }

    public Mono<Car> updateTodo(Car car) {
        return repository.save(car);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
