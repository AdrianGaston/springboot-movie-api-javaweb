package com.api.movies.controller;

import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PostMapping("/atualizar-filme/{id}")
    public String atualizarFilme(@PathVariable Integer id, @ModelAttribute Filme filmeAtualizado) {
        filmeAtualizado.setId(id);
        filmeRepository.save(filmeAtualizado);
        
        return "redirect:/lista_filmes";
    }
    
    @GetMapping("/filme/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Integer id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        model.addAttribute("filme", filme);
        
        return "editar_filme";
    }
    
    @PostMapping("/excluir-filme/{id}")
    public String excluirFilme(@PathVariable Integer id) {
        filmeRepository.deleteById(id);
        
        return "redirect:/lista_filmes";
    }
    
    @GetMapping("/editar_filme/{id}")
    public String editarFilme(@PathVariable Integer id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        model.addAttribute("filme", filme);
        
        return "editar_filme";
    }
}
