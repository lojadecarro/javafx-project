package com.example.javafxproject.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController {
    @FXML
    public void onActionCadastrarCarro() throws IOException {
        abrirJanela("cadastro-carro.fxml", "Cadastro de Carro");
    }

    @FXML
    public void onActionCadastrarCompra()  throws IOException {
        abrirJanela("cadastro-compra.fxml", "Cadastro de Carro");
    }

    @FXML
    public void onActionCadastrarCliente()  throws IOException {
        abrirJanela("cadastro-cliente.fxml", "Cadastro de Cliente");
    }

    // recebe nome arquivo e o titulo -> void criar janela
    private void abrirJanela(String nomeArquivo, String tituloJanela)  throws IOException {
        String file =  "/com/example/javafxproject/" + nomeArquivo;
        FXMLLoader loader = new FXMLLoader(PrincipalController.class.getResource(file));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle(tituloJanela);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
