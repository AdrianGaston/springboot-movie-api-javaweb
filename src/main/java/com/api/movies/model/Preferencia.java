package com.api.movies.model;


public class Preferencia {
    private String nome;
    private String estilo;

    public Preferencia() {
    }

    public Preferencia(String nome, String estilo) {
        this.nome = nome;
        this.estilo = estilo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
