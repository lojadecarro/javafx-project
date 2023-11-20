package com.example.javafxproject.TablesDAO;

import java.sql.SQLException;
import java.util.List;

import com.example.javafxproject.Tables.Marca;

public class Teste {
    public static void main(String[] args) throws SQLException {
        List<Marca> marcas = MarcaDAO.listarMarcas();
        for (Marca marca : marcas) {
            System.out.println(marca.getNome());
        }
    }
}
