package com.example.demo.services.impl;

import com.example.demo.models.Brand;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository repository;

    @Autowired
    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    public Flux<Brand> getAll() {
        return repository.findAll();
    }

    public Mono<Brand> getById(Long id) {
        return repository.findById(id);
    }

    public Mono<Brand> addTodo(Brand brand) {
        return repository.save(brand);
    }

    public Mono<Brand> updateTodo(Brand brand) {
        return repository.save(brand);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
