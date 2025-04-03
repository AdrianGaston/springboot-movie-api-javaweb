package com.api.movies.controller;

import com.api.movies.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MiscController {
    
    @GetMapping("/cadastrar")
    public String mostraCadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrar";
    }
    
    @PostMapping("/cadastrar")
    public String recebeCadastro(Model model, @ModelAttribute Filme filme) {
        model.addAttribute("filme", filme);
        return "filme";
    }
}
