package com.api.movies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MiscController {
    
    @GetMapping("/cadastrar")
    public String mostrarCadastro() {
        return "cadastrar";
    }
}
