package com.api.movies.controller;

import com.api.movies.service.FilmeService;
import com.api.movies.model.Filme;
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
    
    @PostMapping("/adicionar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme film) {
        var novoFilme = filmeService.criarFilme(film);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFilmes() {
        List<Filme> filmes = filmeService.listarFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Filme> getFilmeId(@PathVariable Integer id) {
        Filme filme = filmeService.getFilmeId(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    /*@PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Integer id, @RequestBody Filme filme) {
        var filmeAtualizado = filmeService.atualizarFilme(id, filme);
        
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }*/
    
    @PutMapping("/atualizar/{id}")
    public String atualizarFilme(@PathVariable Integer id, @RequestBody Filme filme) {
        filmeService.atualizarFilme(id, filme);
    
        return "redirect:/lista_filmes";
    }
    
    @DeleteMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
        
        //return new ResponseEntity<>(HttpStatus.OK);
        return "redirect:/filmes";
    }
}
