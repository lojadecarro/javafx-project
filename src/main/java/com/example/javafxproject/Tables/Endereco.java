package com.example.javafxproject.Tables;

public class Endereco {
    private int id;
    private String logradouro;
    private short numero;
    private String complemento;
    private String endereco = logradouro + " " + numero + ", " + complemento;
    private String cep;
    
    public Endereco(int id, String logradouro, short numero, String complemento, String cep) {
        Verificacoes.verificarParametroNull(id, logradouro, numero, cep);
        verificarEFormatarEndereco(logradouro, numero, complemento, cep);
        this.id = id;
    }

    public Endereco(String logradouro, short numero, String complemento, String cep) {
        Verificacoes.verificarParametroNull(logradouro, numero, cep);
        verificarEFormatarEndereco(logradouro, numero, complemento, cep);
    }

    public int getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public short getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    private void verificarEFormatarEndereco(String logradouro, short numero, String complemento, String cep){
        StringBuilder logradouroFormatado = new StringBuilder();
        String[] palavrasLogradouro = logradouro.split(" ");

        if (!logradouro.matches("^[a-zA-Zç ]+$") || logradouro.length() > 200) {
            throw new RuntimeException("O logradouro de um endereço deve ter o tipo e o nome do logradouro formados por apenas letras.");
        }

        for (String palavra : palavrasLogradouro) {
            if (!palavra.matches("^[a-zA-Zç ]+$")) {
                throw new RuntimeException("O logradouro deve possuir apenas letras");
            }

            if (!palavra.isEmpty()) {
                palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1).toLowerCase();
                logradouroFormatado.append(palavra).append(" ");
            }
        }

        logradouro = logradouroFormatado.toString().replaceAll("\\s+", " ").trim();

        this.logradouro = logradouro;
        setNumero(numero);
        setComplemento(complemento);
        setCep(cep);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComplemento(String complemento) {
        if (complemento.isEmpty()) {
            this.complemento = complemento;
            return;
        }

        if ((!complemento.matches("^([a-zA-Z]+\\s\\d+)+$") || complemento.length() > 100) && !complemento.isEmpty()) {
            throw new RuntimeException("O complemento deve ter a forma de subdivisão do local do endereço e o nome da subdivisão. Caso tenha mais de uma subdivisão, coloque da maior para a menor.");
        }

        this.complemento = complemento;
    }

    public void setNumero(short numero) {
        if (numero > 10000 || numero < 1) {
            throw new RuntimeException("O número deve conter apenas digitos.");
        }

        this.numero = numero;
    }

    public void setCep(String cep) {
        if (!cep.matches("\\d{8}") && !cep.matches("^\\d{5}-\\d{3}$")) {
            throw new RuntimeException("Formato de CEP inválido.");
        }

        if (cep.matches("\\d{8}")) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5);
        }

        this.cep = cep;
    }
} 
