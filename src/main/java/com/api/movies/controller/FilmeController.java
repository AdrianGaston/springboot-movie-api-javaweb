package com.api.movies.controller;

import com.api.movies.service.FilmeService;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    FilmeService filmeService;
    
    @PostMapping("/adicionar")
    public String addFilme(Filme filme) {
        var novoFilme = filmeService.criarFilme(filme);
        return "redirect:/lista_filmes";  
    }
    
    @GetMapping("/listar")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "lista_filmes";
    }
    
    @GetMapping("/pesquisar/{id}")
    public String getFilmeId(@PathVariable Integer id, Model model) {
        Filme filme = filmeService.getFilmeId(id);
        model.addAttribute("filme", filme);
        return "detalhe_filme";
    }
    
    @PutMapping("/atualizar/{id}")
    public String atualizarFilme(@PathVariable Integer id, Filme filme) {
        filmeService.atualizarFilme(id, filme);
        return "redirect:/lista_filmes";
    }
    
    @DeleteMapping("/deletar/{id}")
    public String deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
        
        return "redirect:/filmes";
    }
}
