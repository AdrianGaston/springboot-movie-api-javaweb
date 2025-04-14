package com.api.movies.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import jakarta.persistence.Table;
import java.util.List;


@Data
@Entity
@Table(name="Filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int lancamento;
    
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Analise>analise;
    
    //Construtores
    public Filme() {
    }

    public Filme(Integer id, String titulo, String sinopse, String genero, int lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.lancamento = lancamento;
    }
}
