package com.example.apirestfullwebflux.controller;

import com.example.apirestfullwebflux.collections.Dato;
import com.example.apirestfullwebflux.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class Controlador {
    @Autowired
    Servicio servicio;


    @PostMapping("/crear")
    public ResponseEntity<Mono<Dato>> guardar(@RequestBody Dato dato) {
        return new ResponseEntity<>(servicio.crear(dato), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Flux<Dato>> traerTodos() {
        return new ResponseEntity<>(servicio.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping(path="/detalle/{id}")
    public ResponseEntity<Mono<Dato>> traerUno(@PathVariable("id") String id) {
        return new ResponseEntity<>(servicio.buscarUno(id), HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Mono<Dato>> actualizar(@RequestBody Dato dato) {
        return new ResponseEntity<>(servicio.actualizar(dato), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity <Mono<Void>> eliminar(@PathVariable("id") String id) {
        return new ResponseEntity<>(servicio.eliminar(id), HttpStatus.CREATED);
    }



}
