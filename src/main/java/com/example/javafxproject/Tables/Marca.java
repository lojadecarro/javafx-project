package com.example.javafxproject.Tables;

public class Marca {
    private int id;
    private String nome;

    public Marca(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public Marca(String nome) {
        Verificacoes.verificarParametroNull(nome);
        setNome(nome);
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 50 || nome.length() < 2 || !nome.matches("^[\\p{L}- ]+$")) {
            throw new RuntimeException("O nome da marca deve ter no mínimo duas letras e no máximo 50 letras.");
        
        }
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
        
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
