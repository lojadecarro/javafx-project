package com.example.javafxproject;


import java.sql.Connection;
import java.sql.SQLException;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.EstruturaBanco.CriarInsertsBasicos;
import com.example.javafxproject.EstruturaBanco.CriarTabelas;
//import com.example.javafxproject.EstruturaBanco.RemoverTodasTabelas;

//import org.mariadb.jdbc.Connection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = Conexao.getConnection();
        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
        
        CriarTabelas.criar();
        CriarInsertsBasicos.criar();
        //RemoverTodasTabelas.remover();
    }
}
