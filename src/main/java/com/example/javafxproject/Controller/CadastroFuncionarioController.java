package com.example.javafxproject.Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.javafxproject.Tables.Cargo;
import com.example.javafxproject.Tables.Endereco;
import com.example.javafxproject.Tables.Funcionario;
import com.example.javafxproject.Tables.Turno;
import com.example.javafxproject.TablesDAO.CargoDAO;
import com.example.javafxproject.TablesDAO.EnderecoDAO;
import com.example.javafxproject.TablesDAO.FuncionarioDAO;
import com.example.javafxproject.TablesDAO.TurnoDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class CadastroFuncionarioController {
    @FXML
    private TextField txfNomeCompleto;
    @FXML
    private TextField txfCPF;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfTelefone;
    @FXML
    private DatePicker dtNascimento;
    @FXML
    private TextField txfSalarioFixo;
    @FXML
    private TextField txfDiaPagamento;
    @FXML
    private TextField txfIntervalo;
    @FXML
    private TextField txfDuracao;
    @FXML
    private TextField txfInicioTurno;
    @FXML
    private TextField txfFimTurno;
    @FXML
    private TextField txfLogradouro;
    @FXML
    private TextField txfNumero;
    @FXML
    private TextField txfComplemento;
    @FXML
    private TextField txfCEP;
    @FXML
    private ComboBox<String> cboCargo;
    @FXML
    private ComboBox<String> cboTurno;

    @FXML
    public void initialize() {
        List<Cargo> cargos = CargoDAO.listarCargos();
        ObservableList<String> itens = FXCollections.observableArrayList();
        for (Cargo cargo : cargos) {
            itens.add(cargo.getNome());
        }
        cboCargo.setItems(itens);

        List<Turno> turnos = TurnoDAO.listarTurnos();
        ObservableList<String> itens2 = FXCollections.observableArrayList();
        for (Turno turno : turnos) {
            itens2.add(turno.getInicio() + " - " + turno.getFim());
        }
        cboTurno.setItems(itens2);
    }
    
    public void onActionCadastrar() throws SQLException {    
        String nomeCompleto = txfNomeCompleto.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();
        LocalDate dataNascimento = dtNascimento.getValue();
        Double salarioFixo = Double.parseDouble(txfSalarioFixo.getText());
        Short diaPagamento = Short.parseShort(txfDiaPagamento.getText());
        LocalTime inicioTurno = LocalTime.parse(cboTurno.getSelectionModel().getSelectedItem().substring(0, 5));
        LocalTime fimTurno = LocalTime.parse(cboTurno.getSelectionModel().getSelectedItem().substring(8));
        Turno turno = TurnoDAO.findByInicioEFim(inicioTurno, fimTurno);
        LocalTime intervalo = LocalTime.parse(txfIntervalo.getText());
        short duracaoIntervalo = Short.parseShort(txfDuracao.getText());
        String cargoEmString = cboCargo.getSelectionModel().getSelectedItem();
        String logradouro = txfLogradouro.getText();
        String numero =  txfNumero.getText();
        String complemento = txfComplemento.getText();
        String cep = txfCEP.getText();

        Endereco endereco = new Endereco(logradouro, Short.parseShort(numero), complemento, cep);
        EnderecoDAO.create(endereco);

        Cargo cargo = CargoDAO.findByNome(cargoEmString);

        Funcionario funcionario = new Funcionario(nomeCompleto, email, telefone, cpf, dataNascimento, endereco, salarioFixo, diaPagamento, duracaoIntervalo, intervalo, cargo, turno);

        FuncionarioDAO.create(funcionario);
    }
}