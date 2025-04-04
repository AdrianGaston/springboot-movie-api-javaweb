package com.api.movies.controller;

import com.api.movies.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MiscController {
    
    private List<Filme> filmes = new ArrayList<>();
    
    @GetMapping("/cadastrar")
    public String mostraCadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrar";
    }
    
    @PostMapping("/cadastrar")
    public String recebeCadastro(Model model, @ModelAttribute Filme filme) {
        filmes.add(filme);
        model.addAttribute("filmes", filmes);
        return "lista_filmes";
    }
    
    @GetMapping("/filmes")
    public String listar_filmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "lista_filmes";
    }
}
