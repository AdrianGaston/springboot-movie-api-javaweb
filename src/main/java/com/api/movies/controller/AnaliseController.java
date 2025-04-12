package com.api.movies.controller;

import com.api.movies.model.Analise;
import com.api.movies.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/analise")
public class AnaliseController {
    
    @Autowired
    private AnaliseService analiseService;
    
    @PostMapping("/adicionar")  
    public ResponseEntity<Analise> addAnalise(@RequestBody Analise analise) {
        var novaAnalise = analiseService.criarAnalise(analise);
        
        return new ResponseEntity<>(novaAnalise, HttpStatus.OK);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Analise>> listarAnalises() {
        var analises = analiseService.listarAnalises();
        
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Analise> atualizarAnalise(@PathVariable Integer id, @RequestBody Analise analise) {
        var analiseAtualizada = analiseService.atualizarAnalise(id, analise);
        
        return new ResponseEntity<>(analiseAtualizada, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Integer id) {
        analiseService.deletarAnalise(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
