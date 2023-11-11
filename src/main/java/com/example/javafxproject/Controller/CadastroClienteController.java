package com.example.javafxproject.Controller;

import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.time.LocalDate;

import com.example.javafxproject.Tables.Cliente;
import com.example.javafxproject.Tables.Endereco;
import com.example.javafxproject.TablesDAO.ClienteDAO;

public class CadastroClienteController {
    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfSobrenome;
    @FXML
    private TextField txfCPF;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfTelefone;

    @FXML
    public void onActionCadastrarCliente() throws SQLException{
        String nome = txfNome.getText();
        String sobrenome = txfSobrenome.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();

        ClienteDAO clienteDao = new ClienteDAO();

        Endereco endereco = new Endereco("Rua Jaragua", (short) 13,"AP10", "01129888");
        Cliente cliente = new Cliente(nome, email, telefone, cpf, LocalDate.of(1990, 5, 15), endereco);

        Cliente clienteCriado = clienteDao.create(cliente);

        System.out.println(nome + "\n" + cpf + "\n" + sobrenome + "\n" + email + "\n" + telefone);
        System.out.println(clienteCriado.getId());
    }

}
