package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CadastroCompraController {
    @FXML
    private ComboBox<String> cboFuncionario;
    @FXML
    private ComboBox<String> cboCliente;
    @FXML
    private ComboBox<String> cboUnidade;

    public void onActionCadastrarcompranova(){
        String funcionario = cboFuncionario.getValue();
        String cliente = cboCliente.getValue();
        String unidade = cboUnidade.getValue();

        System.out.println(funcionario);
        System.out.println(cliente);
        System.out.println(unidade);
    }
    
}
