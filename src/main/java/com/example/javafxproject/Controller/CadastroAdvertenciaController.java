package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroAdvertenciaController {
    @FXML
    private ComboBox<String> cboGravidade;
    @FXML
    private ComboBox<String> cboFuncionario;
    @FXML
    private TextField txfMotivo;

    public void onActionCadastrarnovaadvertencia(){
        //byte gravidade = cboGravidade.getValue();
        // Se colocar do jeito que está em cima retorna um erro
        // dizendo que não dá pra converter byte para String
        // Vou colocar como String, só para testar. Depois modificamos.
        String gravidade = cboGravidade.getValue();
        String funcionario = cboFuncionario.getValue();
        String motivo = txfMotivo.getText();

        System.out.println(gravidade);
        System.out.println(funcionario);
        System.out.println(motivo);
    }
}
