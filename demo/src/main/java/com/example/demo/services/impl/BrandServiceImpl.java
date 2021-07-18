package com.example.demo.services.impl;

import com.example.demo.models.Brand;
import com.example.demo.models.Model;
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

    @Override
    public Flux<Brand> getAll() {
        return repository.findAll();
    }
    @Override
    public Mono<Brand> getById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Mono<Brand> add(Brand brand) {
        return repository.save(brand);
    }
    @Override
    public Mono<Brand> update(Long id, Brand model) {
        return repository.findById(id)
                .flatMap(db -> {
                    db.setName(model.getName());
                    db.setLogo(model.getLogo());
                    return repository.save(db);
                });
    }
    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
