package com.api.movies.controller;

import com.api.movies.data.AnaliseRepository;
import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Analise;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnaliseViewController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @PostMapping("/salvar-analise")
    public String SalvarAnalise(@RequestParam("filmeId") Integer filmeId,
            @RequestParam("analise") String texto,
            @RequestParam("nota") int nota) {
        Filme filme = filmeRepository.findById(filmeId).orElse(null);

        if (filme == null) {
            return "redirect:/lista_filmes";
        }

        Analise novaAnalise = new Analise();
        novaAnalise.setFilme(filme);
        novaAnalise.setAnalise(texto);
        novaAnalise.setNota(nota);

        analiseRepository.save(novaAnalise);

        return "redirect:/lista_filmes";
    }

    @GetMapping("/analise/{id}")
    public String exibirDetalheFilme(@PathVariable Integer id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);

        if (filme == null) {
            return "redirect:/";
        }

        Analise novaAnalise = new Analise();
        novaAnalise.setFilme(filme);

        model.addAttribute("filme", filme);
        model.addAttribute("analise", novaAnalise);

        return "analise";
    }

    @GetMapping("/filme/{id}")
    public String exibirOpcoesFilme(@PathVariable Integer id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);

        model.addAttribute("filme", filme);
        return "opcoes_filme";
    }
}
