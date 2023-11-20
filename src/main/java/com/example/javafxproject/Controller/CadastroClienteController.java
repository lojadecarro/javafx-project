package com.example.javafxproject.Controller;

import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.javafxproject.Tables.Cliente;
import com.example.javafxproject.Tables.Endereco;
import com.example.javafxproject.TablesDAO.ClienteDAO;
import com.example.javafxproject.TablesDAO.EnderecoDAO;

public class CadastroClienteController {
    @FXML
    private TextField txfNomeCompleto;
    @FXML
    private TextField txfCPF;
    @FXML
    private DatePicker dtNascimento;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfTelefone;
    @FXML
    private TextField txfLogradouro;
    @FXML
    private TextField txfNumero;
    @FXML
    private TextField txfComplemento;
    @FXML
    private TextField txfCEP;

    @FXML
    public void onActionCadastrarCliente() throws SQLException{
        String nomeCompleto = txfNomeCompleto.getText();
        String cpf = txfCPF.getText();
        LocalDate dataNascimento = dtNascimento.getValue();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();
        String logradouro = txfLogradouro.getText();
        String numero = txfNumero.getText();
        String complemento = txfComplemento.getText();
        String cep = txfCEP.getText();

        Endereco endereco = new Endereco(logradouro, Short.valueOf(numero), complemento, cep);
        EnderecoDAO.create(endereco);
        
        Cliente cliente = new Cliente(nomeCompleto, email, telefone, cpf, dataNascimento, endereco);
        ClienteDAO.create(cliente);
    }
}
