package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class CadastroUnidadeController {
    @FXML
    private ChoiceBox<String> series;
    @FXML
    private TextField txfAno;
    @FXML
    private TextField txfPlaca;
    @FXML
    private TextField txfQuilometragem;
    @FXML
    private TextField txfValorUnitario;
    @FXML
    private ToggleButton disponibilidadeButton;
    @FXML
    private TextField txfVersao;
    @FXML
    private TextField txfDirecao;
    @FXML
    private TextField txfCor;

    

    public void alterarDisponibilidade() {
        // Lógica para alterar a disponibilidade da unidade
        // Você pode adicionar aqui a lógica para alternar entre disponível e indisponível
    }

}
