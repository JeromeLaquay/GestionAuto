package com.example.demo.services.impl;

import com.example.demo.models.Model;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository repository;

    @Override
    public Flux<Model> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Model> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Model> add(Model model) {
        return repository.save(model);
    }

    @Override
    public Mono<Model> update(Long id, Model model) {
        return repository.findById(id)
                .flatMap(db -> {
                    db.setName(model.getName());
                    db.setBrandId(model.getBrandId());
                    return repository.save(db);
                });
    }

    @Override
    public Mono<Model> deleteById(Long id) {
        return repository.findById(id)
                .flatMap(existing -> repository.delete(existing)
                        .then(Mono.just(existing)));
    }
}
