package com.api.movies.controller.service;

import com.api.movies.data.FilmeEntity;
import com.api.movies.data.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FilmeService {
    @Autowired
    FilmeRepository filmeRepository;
    
    public FilmeEntity criarFilme(FilmeEntity film) {
        film.setId(null);
        filmeRepository.save(film);
        return film;
    }
    
    public FilmeEntity atualizarFilme(Integer filmeId, FilmeEntity filmeRequest) {
        FilmeEntity film = getFilmeId(filmeId);
        
        film.setTitulo(filmeRequest.getTitulo());
        film.setSinopse(filmeRequest.getSinopse());
        film.setGenero(filmeRequest.getGenero());
        film.setLancamento(filmeRequest.getLancamento());
        
        filmeRepository.save(film);
        return film;
    }
    
    public FilmeEntity getFilmeId(Integer filmId) {
        return filmeRepository.findById(filmId).orElse(null);
    }
    
    public List<FilmeEntity> listarFilmes() {
        return filmeRepository.findAll();
    }
    
    public void deletarFilme(Integer filmId) {
        FilmeEntity film = getFilmeId(filmId);
        filmeRepository.deleteById(film.getId());
    }
}
