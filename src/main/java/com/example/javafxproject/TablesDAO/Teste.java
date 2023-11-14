package com.example.javafxproject.TablesDAO;

import java.sql.SQLException;
import java.util.List;

import com.example.javafxproject.Tables.Cargo;

public class Teste {
    public static void main(String[] args) throws SQLException {
        List<Cargo> cargos = CargoDAO.listarCargos();
        for (Cargo cargo : cargos) {
            System.out.println(cargo.getNome());
        }
    }
}
