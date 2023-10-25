package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class CadastroCarroController {

    @FXML
    private ComboBox<String> cboConservacao;
    @FXML
    private TextField txfCidade;
    @FXML
    private TextField txfEstado;
    @FXML
    private TextField txfPlaca;
    @FXML
    private TextField txfAno;
    @FXML
    private TextField txfFabricante;
    @FXML
    private ComboBox<String> cboCategoria;
    @FXML
    private ComboBox<String> cboMarca;
    @FXML
    private TextField txfModelo;
    @FXML
    private TextField txfCor;
    @FXML
    private ComboBox<String> cboDirecao;

    public void onActionCadastrarcarronovo() {
        String conservacao = cboConservacao.getValue();
        String cidade = txfCidade.getText();
        String estado = txfEstado.getText();
        String placa = txfPlaca.getText();
        String ano = txfAno.getText();
        String fabricante = txfFabricante.getText();
        String categoria = cboCategoria.getValue();
        String marca = cboMarca.getValue();
        String modelo = txfModelo.getText();
        String cor = txfCor.getText();
        String direcao = cboDirecao.getValue();

        System.out.println(conservacao);
        System.out.println(cidade);
        System.out.println(estado);
        System.out.println(placa);
        System.out.println(ano); 
        System.out.println(fabricante);
        System.out.println(categoria);
        System.out.println(marca);
        System.out.println(modelo);
        System.out.println(cor);     
        System.out.println(direcao);
    }
}