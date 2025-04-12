package com.api.movies.service;

import com.api.movies.data.AnaliseRepository;
import com.api.movies.model.Filme;
import com.api.movies.data.FilmeRepository;
import com.api.movies.model.Analise;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

        @Autowired
        private AnaliseRepository analiseRepository;
        
        @Autowired
        private FilmeRepository filmeRepository;
        
        public Analise criarAnalise(Analise analise) {
           Integer filmeId = analise.getFilme().getId();
           Filme filme = filmeRepository.findById(filmeId).orElse(null);
           
           analise.setId(null);
           analise.setFilme(filme);
           analiseRepository.save(analise);
           
           return analise;
        }
        
        public List<Analise> listarAnalises() {
            return analiseRepository.findAll();
        }
        
        public void deletarAnalise(Integer id) {
            analiseRepository.deleteById(id);
        }
        
        public Analise atualizarAnalise(Integer analiseId, Analise analiseRequest) {
            Analise analise = analiseRepository.findById(analiseId).orElse(null);
            
            analise.setAnalise(analiseRequest.getAnalise());
            analise.setNota(analiseRequest.getNota());
            
            analiseRepository.save(analise);
            return analise;
        }
}
