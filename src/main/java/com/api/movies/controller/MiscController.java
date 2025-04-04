package com.api.movies.controller;


import com.api.movies.model.Analise;
import com.api.movies.model.Filme;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MiscController {
    
    private List<Filme> filmes = new ArrayList<>();
    private List<Analise> analises = new ArrayList<>();
    
    private long contador = 1;//Contador
    private long contadorAnalise = 1;//Contador dos analises
            
    @GetMapping("/cadastrar")
    public String mostraCadastro(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrar";
    }
    
    @PostMapping("/cadastrar")
    public String recebeCadastro(Model model, @ModelAttribute Filme filme) {
        filme.setId(contador++); //Contador até incluir banco de dados
        filmes.add(filme);
        model.addAttribute("filmes", filmes);
        return "lista_filmes";
    }
    
    @GetMapping("/filmes")
    public String listar_filmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "lista_filmes";
    }
    
    @GetMapping("/analise/{id}")
    public String mostrarAnalise(@PathVariable Long id, Model model) {
        Filme filmeSelecionado = null;

        for (Filme f : filmes) {
            if (f.getId().equals(id)) {
                filmeSelecionado = f;
                break;
            }
        }

        if (filmeSelecionado != null) {
            model.addAttribute("filme", filmeSelecionado);
            model.addAttribute("analise", new Analise());
            return "analise";
        } else {
            return "redirect:/filmes";
        }
    }
    
    @PostMapping("/salvar-analise")
    public String salvarAnalise(@ModelAttribute Analise analise) {
        analise.setId(contadorAnalise++);

        for (Filme f : filmes) {
            if (f.getId().equals(analise.getFilme().getId())) {
                analise.setFilme(f);
                break;
            }
        }
        analises.add(analise);
        return "redirect:/filmes";
    }

}
