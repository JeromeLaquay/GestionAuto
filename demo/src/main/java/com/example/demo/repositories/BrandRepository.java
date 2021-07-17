package com.example.demo.repositories;

import com.example.demo.models.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BrandRepository extends ReactiveCrudRepository<Brand, Long> {

}
