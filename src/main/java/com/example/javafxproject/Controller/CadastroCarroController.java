package com.example.javafxproject.Controller;

import java.sql.SQLException;
import java.util.List;

import com.example.javafxproject.Tables.Cor;
import com.example.javafxproject.Tables.EstadoConservacao;
import com.example.javafxproject.Tables.Marca;
import com.example.javafxproject.Tables.Modelo;
import com.example.javafxproject.Tables.Transmissao;
import com.example.javafxproject.Tables.Unidade;
import com.example.javafxproject.Tables.Versao;
import com.example.javafxproject.TablesDAO.CorDAO;
import com.example.javafxproject.TablesDAO.EstadoConservacaoDAO;
import com.example.javafxproject.TablesDAO.MarcaDAO;
import com.example.javafxproject.TablesDAO.ModeloDAO;
import com.example.javafxproject.TablesDAO.TransmissaoDAO;
import com.example.javafxproject.TablesDAO.UnidadeDAO;
import com.example.javafxproject.TablesDAO.VersaoDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class CadastroCarroController {
    @FXML
    private ComboBox<String> cboEstadoDeConservacao;
    @FXML
    private TextField txfPlaca;
    @FXML
    private TextField txfAno;
    @FXML
    private TextField txfQuilometragem;
    @FXML
    private TextField txfValor;
    @FXML
    private ComboBox<String> cboCor;
    @FXML
    private ComboBox<String> cboTransmissao;
    @FXML
    private ComboBox<String> cboMarca;
    @FXML
    private ComboBox<String> cboModelo;
    @FXML
    private ComboBox<String> cboVersao;

    @FXML
    public void initialize() {
        List<Marca> marcas = MarcaDAO.listarMarcas();
        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Marca marca : marcas) {
            itens.add(marca.getNome());
        }
        cboMarca.setItems(itens);

        List<Transmissao> transmissoes = TransmissaoDAO.listarTransmissoes();
        ObservableList<String> itens2 = FXCollections.observableArrayList();
        for (Transmissao transmissao : transmissoes) {
            itens2.add(transmissao.getTipo());
        }
        cboTransmissao.setItems(itens2);

        List<Cor> cores = CorDAO.listarCores();
        ObservableList<String> itens3 = FXCollections.observableArrayList();
        for (Cor cor : cores) {
            itens3.add(cor.getNome());
        }
        cboCor.setItems(itens3);

        List<EstadoConservacao> estados = EstadoConservacaoDAO.listarEstadosDeConservacao();
        ObservableList<String> itens4 = FXCollections.observableArrayList();
        for (EstadoConservacao estado : estados) {
            itens4.add(estado.getNome());
        }
        cboEstadoDeConservacao.setItems(itens4);
    }

    @FXML
    private void selecionarMarca() {
        String marcaSelecionada = cboMarca.getValue();
        cboModelo.getItems().clear();
        List<Modelo> modelos = ModeloDAO.findModelosByMarca(marcaSelecionada);
        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Modelo modelo : modelos) {
            itens.add(modelo.getNome());
        }
        cboModelo.setItems(itens);
    }

    @FXML
    private void selecionarModelo() {
        String modeloSelecionado = cboModelo.getValue();
        cboVersao.getItems().clear();
        List<Versao> versoes = VersaoDAO.findVersoesByModelo(modeloSelecionado);
        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Versao versao : versoes) {
            itens.add(versao.getNome());
        }
        cboVersao.setItems(itens);
    }

    public void onActionCadastrarcarronovo() throws SQLException {
        String placa = txfPlaca.getText();
        Short ano = Short.valueOf(txfAno.getText());
        Double valorUnitario = Double.valueOf(txfValor.getText());
        int quilometragem = Integer.valueOf(txfQuilometragem.getText());
        Versao versao = VersaoDAO.findVersaoByModeloENome(cboModelo.getSelectionModel().getSelectedItem(), cboVersao.getSelectionModel().getSelectedItem());
        Cor cor = CorDAO.findByNome(cboCor.getSelectionModel().getSelectedItem());
        EstadoConservacao estado = EstadoConservacaoDAO.findByNome(cboEstadoDeConservacao.getSelectionModel().getSelectedItem());
        Transmissao transmissao = TransmissaoDAO.findByNome(cboTransmissao.getSelectionModel().getSelectedItem());

        Unidade unidade = new Unidade(ano, placa, quilometragem, estado, valorUnitario, versao, transmissao, cor);
        UnidadeDAO.create(unidade);
    }
}