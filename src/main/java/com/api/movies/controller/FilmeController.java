package com.api.movies.controller;

import com.api.movies.service.FilmeService;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    /*public ResponseEntity<Filme> addFilme(@RequestBody Filme film) {
        var novoFilme = filmeService.criarFilme(film);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }*/
    public String addFilme(Filme filme) {
        var novoFilme = filmeService.criarFilme(filme);
        return "redirect:/lista_filmes";  // Redireciona para a página de lista de filmes
    }
    
    
    @GetMapping("/listar")
    /*public ResponseEntity<List> getAllFilmes() {
        List<Filme> filmes = filmeService.listarFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }*/
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "lista_filmes";  // Exibe a lista de filmes
    }
    
    
    @GetMapping("/pesquisar/{id}")
    /*public ResponseEntity<Filme> getFilmeId(@PathVariable Integer id) {
        Filme filme = filmeService.getFilmeId(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }*/
    public String getFilmeId(@PathVariable Integer id, Model model) {
        Filme filme = filmeService.getFilmeId(id);
        model.addAttribute("filme", filme);
        return "detalhe_filme";  // Exibe detalhes do filme
    }
    
    
    /*@PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Integer id, @RequestBody Filme filme) {
        var filmeAtualizado = filmeService.atualizarFilme(id, filme);
        
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }*/
    
    @PutMapping("/atualizar/{id}")
    /*public String atualizarFilme(@PathVariable Integer id, @RequestBody Filme filme) {
        filmeService.atualizarFilme(id, filme);
    
        return "redirect:/lista_filmes";
    }*/
    public String atualizarFilme(@PathVariable Integer id, Filme filme) {
        filmeService.atualizarFilme(id, filme);
        return "redirect:/lista_filmes";  // Redireciona para a lista de filmes após a atualização
    }
    
    @DeleteMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
        
        //return new ResponseEntity<>(HttpStatus.OK);
        return "redirect:/filmes";
    }
}
