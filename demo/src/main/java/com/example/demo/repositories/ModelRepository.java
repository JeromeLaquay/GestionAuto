package com.example.demo.repositories;

import com.example.demo.models.Model;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ModelRepository extends ReactiveCrudRepository<Model, Long> {

    @Query("SELECT * FROM MODEL WHERE brand_id = :brandId")
    Flux<Model> findByBrandId(Long brandId);

}