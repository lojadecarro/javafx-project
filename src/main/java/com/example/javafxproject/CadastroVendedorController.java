package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class CadastroVendedorController {
    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfSobrenome;
    @FXML
    private TextField txfCPF;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfTelefone;
    
    
    public void onActionCadastrar() {
        String nome = txfNome.getText();
        String sobrenome = txfSobrenome.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();

        System.out.println(nome);
        System.out.println(sobrenome);
        System.out.println(cpf);
        System.out.println(email);
        System.out.println(telefone);
    }
    
}