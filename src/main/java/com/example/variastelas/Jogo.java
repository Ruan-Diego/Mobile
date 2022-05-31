package com.example.variastelas;

public class Jogo {

    private static int contadorId = 0;

    private int id;
    private String nome;
    private String tipo;

    public Jogo(){

    }
    public Jogo(String nome, String tipo ) {
        this.id = contadorId++;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome;
    }
}
