package com.example.javafxproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroVendaController {
    @FXML
    private ComboBox<String> cboFuncionario;
    @FXML
    private ComboBox<String> cboCliente;
    @FXML
    private ComboBox<String> cboUnidade;
    @FXML
    private TextField txfDiaHorario; 
    @FXML
    private ComboBox<String> cboFormaDePagamento;
    @FXML
    private TextField txfDesconto;
    @FXML
    private TextField txfParcelas;
    @FXML
    private TextField txfJuros;

    public void onActionCadastrarvendanova(){
        String funcionario = cboFuncionario.getValue();
        String cliente = cboCliente.getValue();
        String unidade = cboUnidade.getValue();
        String diaHorario = txfDiaHorario.getText();
        String formaDePagamento = cboFormaDePagamento.getValue();
        String desconto = txfDesconto.getText();
        String parcelas = txfParcelas.getText();
        String juros = txfJuros.getText();

        System.out.println(funcionario);
        System.out.println(cliente);
        System.out.println(unidade);
        System.out.println(diaHorario);
        System.out.println(formaDePagamento);
        System.out.println(desconto);
        System.out.println(parcelas);
        System.out.println(juros);
    }

}
