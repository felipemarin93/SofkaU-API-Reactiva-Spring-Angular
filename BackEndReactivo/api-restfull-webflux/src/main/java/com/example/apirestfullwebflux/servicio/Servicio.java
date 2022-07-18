package com.example.apirestfullwebflux.servicio;

import com.example.apirestfullwebflux.collections.Dato;
import com.example.apirestfullwebflux.repositories.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Servicio {

    @Autowired
    Repositorio repositorio;

    public Mono<Dato> crear(Dato dato){
        return repositorio.save(dato);
    }
    public Flux<Dato> buscarTodos() {
        return repositorio.findAll();
    }

    public Mono<Dato> buscarUno(String id) {
        return repositorio.findById(id);
    }

    public Mono<Dato> actualizar(Dato dato) {
        return repositorio.save(dato);
    }

    public Mono<Void> eliminar(String id) {
        return repositorio.deleteById(id);
    }
}
