package com.api.movies.controller;

import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmeViewController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping("/cadastrar")
    public String exibirFormulario(@CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("filme", new Filme());
        model.addAttribute("tema", tema);

        return "cadastrar";
    }

    @PostMapping("/adicionar")
    public String adicionarFilme(@ModelAttribute Filme filme) {
        filmeRepository.save(filme);

        return "redirect:lista_filmes";
    }

    @GetMapping("/lista_filmes")
    public String listarFilmes(@CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        model.addAttribute("tema", tema);

        return "lista_filmes";
    }

    @PostMapping("/atualizar-filme/{id}")
    public String atualizarFilme(@PathVariable Integer id, @ModelAttribute Filme filmeAtualizado) {
        filmeAtualizado.setId(id);
        filmeRepository.save(filmeAtualizado);

        return "redirect:/lista_filmes";
    }

    @GetMapping("/filme/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Integer id, @CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        model.addAttribute("filme", filme);
        model.addAttribute("tema", tema);

        return "editar_filme";
    }

    @PostMapping("/excluir-filme/{id}")
    public String excluirFilme(@PathVariable Integer id) {
        filmeRepository.deleteById(id);

        return "redirect:/lista_filmes";
    }

    @GetMapping("/editar_filme/{id}")
    public String editarFilme(@PathVariable Integer id, @CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        model.addAttribute("filme", filme);
        model.addAttribute("tema", tema);

        return "editar_filme";
    }
}
