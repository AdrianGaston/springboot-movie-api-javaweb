package com.api.movies.controller;

import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeViewController {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("filme", new Filme());
        
        return "cadastrar";
    }
    
    @PostMapping("/adicionar")
    public String adicionarFilme(@ModelAttribute Filme filme) {
        filmeRepository.save(filme);
        
        return "redirect:lista_filmes";
    }
    
    @GetMapping("/lista_filmes")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        
        return "lista_filmes";
    }
}
