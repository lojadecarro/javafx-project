package com.example.javafxproject;

import javafx.scene.control.*;
import javafx.fxml.FXML;

public class CadastroClienteController {
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

    @FXML
    public void onActionCadastrarCliente(){
        String nome = txfNome.getText();
        String sobrenome = txfSobrenome.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();

        System.out.println(nome + "\n" + cpf + "\n" + sobrenome + "\n" + email + "\n" + telefone);
    }

}
