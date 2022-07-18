package com.example.apirestfullwebflux.repositories;

import com.example.apirestfullwebflux.collections.Dato;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface Repositorio extends ReactiveMongoRepository<Dato, String> {

}
