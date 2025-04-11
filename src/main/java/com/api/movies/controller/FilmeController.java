package com.api.movies.controller;

import com.api.movies.controller.service.FilmeService;
import com.api.movies.data.FilmeEntity;
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
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    FilmeService filmeService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFilmes() {
        List<FilmeEntity> filmes = filmeService.listarFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> getFilmeId(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.getFilmeId(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@RequestBody FilmeEntity film) {
        var novoFilme = filmeService.criarFilme(film);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @RequestBody FilmeEntity filme) {
        var filmeAtualizado = filmeService.atualizarFilme(id, filme);
        
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
