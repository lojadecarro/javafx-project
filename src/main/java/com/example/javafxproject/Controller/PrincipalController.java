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

    @FXML
    public void onActionCadastrarVenda()  throws IOException {
        abrirJanela("cadastro-venda.fxml", "Cadastro de Venda");
    }

    @FXML
    public void onActionCadastrarAdvertencia()  throws IOException {
        abrirJanela("cadastro-advertencia.fxml", "Cadastro de Advertência");
    }

    @FXML
    public void onActionCadastrarFuncionario()  throws IOException {
        abrirJanela("cadastro-funcionario.fxml", "Cadastro de Funcionário");
    }

    @FXML
    public void onActionCadastrarTestDrive()  throws IOException {
        abrirJanela("cadastro-testdrive.fxml", "Cadastro de Test-Drive");
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
