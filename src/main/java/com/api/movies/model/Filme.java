package com.api.movies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Table;



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
