package com.example.javafxproject.TablesDAO;

import java.sql.SQLException;
import java.util.List;

import com.example.javafxproject.Tables.Funcionario;

public class Teste {
    public static void main(String[] args) throws SQLException {
        List<Funcionario> funcionarios = FuncionarioDAO.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }
    }
}
