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
    @Override
    public Flux<Car> getAll() {
        return repository.findAll();
    }
    @Override
    public Mono<Car> getById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Mono<Car> add(Car car) {
        return repository.save(car);
    }
    @Override
    public Mono<Car> update(Car car) {
        return repository.save(car);
    }
    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
