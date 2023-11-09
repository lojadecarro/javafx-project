package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class CadastroFuncionarioController {
    @FXML
    private ComboBox<String> cboCargo;
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
        String cargo = cboCargo.getValue();
        String nome = txfNome.getText();
        String sobrenome = txfSobrenome.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();

        System.out.println(cargo);
        System.out.println(nome);
        System.out.println(sobrenome);
        System.out.println(cpf);
        System.out.println(email);
        System.out.println(telefone);
    }
    
}