package com.api.movies.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cookies")
public class SiteController {

    @RequestMapping(value = "/gravaTema", method = RequestMethod.POST)
    public String gravaTema(HttpServletResponse response, String tema) {
        Cookie cookieTema = new Cookie("theme", tema);
        cookieTema.setMaxAge(3600);
        cookieTema.setPath("/");

        response.addCookie(cookieTema);
        System.out.println("Tema recebido: " + tema);
        return "redirect:/cadastrar";
    }

    @RequestMapping("/le")
    public String leCookie(@CookieValue(name = "theme", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("tema", tema);

        return "View";
    }

    @RequestMapping("/exclui")
    public String excluiCookie(HttpServletResponse response) {
        
        Cookie novoCookie = new Cookie("user-id", null);
        novoCookie.setMaxAge(0); 
        novoCookie.setDomain("localhost");
        novoCookie.setPath("/"); 

        response.addCookie(novoCookie); 

        return "excluicookie"; 
    }
    
    @ModelAttribute
    public void carregarTema(HttpServletRequest response, Model model) {
        String tema = "claro";
        Cookie[] cookies = response.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("theme")) {
                    tema = cookie.getValue();
                    break;
                }
            }
        }
        model.addAttribute("tema", tema);
    }
}
