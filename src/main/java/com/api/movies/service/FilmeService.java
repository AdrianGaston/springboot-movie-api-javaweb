package com.api.movies.service;

import com.api.movies.model.Filme;
import com.api.movies.data.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FilmeService {
    @Autowired
    FilmeRepository filmeRepository;
    
    public Filme criarFilme(Filme film) {
        film.setId(null);
        filmeRepository.save(film);
        return film;
    }
    
    public Filme atualizarFilme(Integer filmeId, Filme filmeRequest) {
        Filme film = getFilmeId(filmeId);
        
        film.setTitulo(filmeRequest.getTitulo());
        film.setSinopse(filmeRequest.getSinopse());
        film.setGenero(filmeRequest.getGenero());
        film.setLancamento(filmeRequest.getLancamento());
        
        filmeRepository.save(film);
        return film;
    }
    
    public Filme getFilmeId(Integer filmId) {
        return filmeRepository.findById(filmId).orElse(null);
    }
    
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }
    
    public void deletarFilme(Integer filmId) {
        Filme film = getFilmeId(filmId);
        filmeRepository.deleteById(film.getId());
    }
}
