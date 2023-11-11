package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroTestDriveController {
    @FXML
    private TextField txfInicio;
    @FXML
    private TextField txfFim;
    @FXML
    private ComboBox<String> cboCliente;
    @FXML
    private ComboBox<String> cboFuncionario;
    @FXML
    private ComboBox<String> cboUnidade;

    public void onActionAgendartestdrive(){
        String inicio = txfInicio.getText();
        String fim = txfFim.getText();
        String cliente = cboCliente.getValue();
        String funcionario = cboFuncionario.getValue();
        String unidade = cboUnidade.getValue();

        System.out.println(inicio);
        System.out.println(fim);
        System.out.println(cliente);
        System.out.println(funcionario);
        System.out.println(unidade);
    }
    
}
