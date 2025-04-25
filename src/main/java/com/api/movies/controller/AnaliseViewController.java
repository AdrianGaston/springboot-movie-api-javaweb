package com.api.movies.controller;

import com.api.movies.data.AnaliseRepository;
import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Analise;
import com.api.movies.model.Filme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnaliseViewController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AnaliseController analiseController;

    @Autowired
    private AnaliseRepository analiseRepository;

    @PostMapping("/salvar-analise")
    public String SalvarAnalise(@RequestParam("filmeId") Integer filmeId,
            @RequestParam("analise") String texto,
            @RequestParam("nota") int nota) {

        analiseController.salvarAnalise(filmeId, texto, nota);

        return "redirect:/lista_filmes";
    }

    @GetMapping("/analise/{id}")
    public String exibirDetalheFilme(@PathVariable Integer id, @CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);

        if (filme == null) {
            return "redirect:/";
        }

        Analise novaAnalise = new Analise();
        novaAnalise.setFilme(filme);

        model.addAttribute("filme", filme);
        model.addAttribute("analise", novaAnalise);
        model.addAttribute("tema", tema);

        return "analise";
    }

    @GetMapping("/filme/{id}")
    public String exibirOpcoesFilme(@PathVariable Integer id, @CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);

        model.addAttribute("filme", filme);
        model.addAttribute("tema", tema);

        return "opcoes_filme";
    }

    @GetMapping("/analises")
    public String listarAnalises(@CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        List<Analise> analises = analiseRepository.findAll();
        model.addAttribute("analises", analises);
        model.addAttribute("tema", tema);

        return "listar_analises";
    }

    @GetMapping("/editar_analise/{id}")
    public String exibirFormularioEdicao(@PathVariable Integer id, @CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        Analise analise = analiseRepository.findById(id).orElse(null);

        if (analise == null) {
            return "redirect:/analise";
        }

        model.addAttribute("analise", analise);
        model.addAttribute("tema", tema);

        return "editar_analise";
    }

    @PostMapping("editar_analise/{id}")
    public String salvarEdicaoAnalise(@PathVariable Integer id, @RequestParam("analise") String texto, @RequestParam("nota") int nota) {
        Analise analise = analiseRepository.findById(id).orElse(null);

        if (analise != null) {
            analise.setAnalise(texto);
            analise.setNota(nota);
            analiseRepository.save(analise);

            return "redirect:/detalhes_filme" + analise.getFilme().getId();
        }

        return "redirect:/analise";
    }

    @PostMapping("/atualizar_analise")
    public String atualizarAnalise(
            @RequestParam("id") Integer id,
            @RequestParam("filme.id") Integer filmeId,
            @RequestParam("analise") String texto,
            @RequestParam("nota") int nota) {

        Analise analise = analiseRepository.findById(id).orElse(null);
        Filme filme = filmeRepository.findById(filmeId).orElse(null);

        if (analise != null && filme != null) {
            analise.setAnalise(texto);
            analise.setNota(nota);
            analise.setFilme(filme);
            analiseRepository.save(analise);
        }

        return "redirect:/lista_filmes";
    }

    @PostMapping("/excluir_analise/{id}")
    public String excluirAnalise(@PathVariable Integer id) {
        Analise analise = analiseRepository.findById(id).orElse(null);

        analiseRepository.deleteById(id);

        Integer filmeId = analise.getFilme().getId();
        return "redirect:/filme/" + filmeId;
    }

}
