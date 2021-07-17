package com.example.demo.services.impl;

import com.example.demo.models.Model;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelRepository repository;

    @Autowired
    public ModelServiceImpl(ModelRepository repository) {
        this.repository = repository;
    }

    public Flux<Model> getAll() {
        return repository.findAll();
    }

    public Mono<Model> getById(Long id) {
        return repository.findById(id);
    }

    public Mono<Model> addTodo(Model model) {
        return repository.save(model);
    }

    public Mono<Model> updateTodo(Model model) {
        return repository.save(model);
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
