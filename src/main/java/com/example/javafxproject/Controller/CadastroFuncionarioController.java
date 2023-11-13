package com.example.javafxproject.Controller;

import java.sql.SQLException;
import java.time.LocalDate;
//import org.controlsfx.control.TimePicker;
import java.time.LocalTime;

import com.example.javafxproject.Tables.Cargo;
import com.example.javafxproject.Tables.Endereco;
import com.example.javafxproject.Tables.Funcionario;
import com.example.javafxproject.Tables.Turno;
import com.example.javafxproject.TablesDAO.CargoDAO;
import com.example.javafxproject.TablesDAO.EnderecoDAO;
import com.example.javafxproject.TablesDAO.FuncionarioDAO;
import com.example.javafxproject.TablesDAO.TurnoDAO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class CadastroFuncionarioController {
    @FXML
    private ComboBox<String> cboCargo;
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
    
    
    public void onActionCadastrar() throws SQLException {
        String nomeCompleto = txfNomeCompleto.getText();
        String cpf = txfCPF.getText();
        String email = txfEmail.getText();
        String telefone = txfTelefone.getText();
        LocalDate dataNascimento = dtNascimento.getValue();
        String salarioFixo = txfSalarioFixo.getText();
        String diaPagamento = txfDiaPagamento.getText();
        String intervaloEmString = txfIntervalo.getText();
        String cargoEmString = cboCargo.getValue();
        String inicioTurnoEmString = txfInicioTurno.getText();
        String fimTurnoEmString = txfFimTurno.getText();
        String logradouro = txfLogradouro.getText();
        String numero =  txfNumero.getText();
        String complemento = txfComplemento.getText();
        String cep = txfCEP.getText();

        FuncionarioDAO funcionarioDao = new FuncionarioDAO();

        EnderecoDAO enderecoDao = new EnderecoDAO();
        TurnoDAO turnoDao = new TurnoDAO();
        CargoDAO cargoDao = new CargoDAO();

        Endereco endereco = new Endereco(logradouro,(short) 12, complemento, cep);
        Endereco enderecoCriado = enderecoDao.create(endereco);
        Cargo cargo = cargoDao.findByNome(cargoEmString);
        LocalTime intervalo = LocalTime.parse(intervaloEmString); // Conversão. Supondo que o formato seja HH:mm:ss
        LocalTime inicioTurno = LocalTime.parse(inicioTurnoEmString);
        LocalTime fimTurno = LocalTime.parse(fimTurnoEmString);
        Turno turno = new Turno(inicioTurno, fimTurno);

        Funcionario funcionario = new Funcionario(nomeCompleto, email, telefone, cpf, dataNascimento, enderecoCriado, Double.valueOf(salarioFixo), Short.valueOf(diaPagamento), intervalo, cargo, turno);

        Funcionario funcionarioCriado = funcionarioDao.create(funcionario);
        
        System.out.println(nomeCompleto);
        System.out.println(email);
        System.out.println(cpf);
        System.out.println(telefone);
        System.out.println(dataNascimento);
        System.out.println(salarioFixo);
        System.out.println(diaPagamento);
        System.out.println(intervalo);
        System.out.println(cargo);
        System.out.println(turno);

    }
    
}