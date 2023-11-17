package com.example.javafxproject.Tables;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private List<Venda> compras = new ArrayList<>();
    private List<Compra> vendas = new ArrayList<>();
    
    public Cliente(int id, String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        super(id, nome, email, contato, cpf, data_nascimento, endereco);
    }

    public Cliente(String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco) {
        super(nome, email, contato, cpf, data_nascimento, endereco);
    }

    public void addVendaCliente(Compra compra){
        vendas.add(compra);
    }
    
    public void addCompraCliente(Venda venda){
        compras.add(venda);
    }

    @Override
    public String toString() {
        return "Cliente [compras=" + compras + ", vendas=" + vendas + "]";
    }
}
