package com.example.javafxproject.EstruturaBanco;

import java.sql.SQLException;

/*
Esta classe prepara o workbench para criar a estrutura de banco da loja de carros.
Cria as tabelas, views e inserts basicos.  
*/

public class CriarEstruturaBDD {
    public static void main(String[] args) throws SQLException {
        
        RemoverTodasTabelas.remover();

        CriarTabelas.criar();

        RemoverTodasViews.remover();
        
        CriarViews.criar();

        CriarInsertsBasicos.criar();
    }
}
