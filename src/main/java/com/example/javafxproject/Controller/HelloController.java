package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label lblMensagem;
    
    @FXML
    public void onClickBtnVerMensagem() {
        lblMensagem.setText("Olá mundo!!!");
    }
}
