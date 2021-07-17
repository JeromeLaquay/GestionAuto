package com.example.demo.repositories;

import com.example.demo.models.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CarRepository extends ReactiveCrudRepository<Car, Long> {

}