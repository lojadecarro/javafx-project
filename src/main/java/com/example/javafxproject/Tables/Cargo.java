package com.example.javafxproject.Tables;

public class Cargo {
    private int id;
    private String nome;

    public Cargo(int id, String nome) {
        Verificacoes.verificarParametroNull(id, nome);
        this.id = id;
        setNome(nome);
    }

    public Cargo(String nome) {
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
        String[] palavras = nome.split(" ");
        StringBuilder nomeFormatadodo = new StringBuilder();

        if (nome.length() > 40 || nome.length() < 4 || !nome.matches("^[\\p{L} ]+$")) {
            throw new RuntimeException("Um cargo deve ter apenas letras sem acentos e possuir um tamanho entre 2 e 40.");
        }
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                nomeFormatadodo.append(palavra).append(" ");
            }
        }

        nome = nomeFormatadodo.toString().replaceAll("\\s+", " ").trim();
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}
