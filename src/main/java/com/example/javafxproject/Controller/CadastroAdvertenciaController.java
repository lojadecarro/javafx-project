package com.example.javafxproject.Controller;

import java.sql.SQLException;
import java.util.List;

import com.example.javafxproject.Tables.Advertencia;
import com.example.javafxproject.Tables.Funcionario;
import com.example.javafxproject.TablesDAO.AdvertenciaDAO;
import com.example.javafxproject.TablesDAO.FuncionarioDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    public void initialize() {
        List<Funcionario> funcionarios = FuncionarioDAO.listarFuncionarios();
        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Funcionario funcionario : funcionarios) {
            itens.add(funcionario.getNome());
        }
        cboFuncionario.setItems(itens);
    }

    public void onActionCadastrarnovaadvertencia() throws SQLException{
        Byte gravidade = Byte.parseByte(cboGravidade.getValue());
        String funcionario = cboFuncionario.getValue();
        String motivo = txfMotivo.getText();

        Advertencia advertencia = new Advertencia(gravidade, FuncionarioDAO.findByNome(funcionario), motivo);
        AdvertenciaDAO.create(advertencia);
    }
}
