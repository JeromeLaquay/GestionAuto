package com.example.demo.repositories;

import com.example.demo.models.Model;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends ReactiveCrudRepository<Model, Long> {

}