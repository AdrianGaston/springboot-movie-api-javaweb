package com.api.movies.controller;

import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Analise;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AnaliseViewController {
    
   @Autowired
   private FilmeRepository filmeRepository;
   
   @GetMapping("/analise{id}")
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
   
   @PostMapping("/salvar-analise")
   public String salvarAnalise(@ModelAttribute Analise analise) {
       // Aqui você salva a análise via seu service (você pode ajustar conforme sua arquitetura)
        // Exemplo:
        // analiseService.criarAnalise(analise);
       
       return "redirect";
   }
   
   @GetMapping("/filme/{id}")
   public String exibirOpcoesFilme(@PathVariable Integer id, Model model) {
       Filme filme = filmeRepository.findById(id).orElse(null);
       
       model.addAttribute("filme", filme);
       return "opcoes_filme";
   }
}
