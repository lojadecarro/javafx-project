package com.example.javafxproject.TablesDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.Tables.Cliente;
import com.example.javafxproject.Tables.FormaDePagamento;
import com.example.javafxproject.Tables.Venda;
import com.example.javafxproject.Tables.Funcionario;
import com.example.javafxproject.Tables.Unidade;

public class VendaDAO {
    public Venda create(Venda venda) throws SQLException{
        String sql = """
            INSERT INTO venda (id_funcionario, id_cliente, id_unidade, id_forma_pagamento, dia_horario, desconto, parcelas, juros) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);    
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, venda.getFuncionario().getId());
            statement.setInt(2, venda.getCliente().getId());
            statement.setInt(3, venda.getUnidade().getId());
            statement.setInt(4, venda.getForma_pagamento().getId());
            statement.setTimestamp(5, Timestamp.valueOf(venda.getDia_horario()));
            statement.setDouble(6, venda.getDesconto());
            statement.setInt(7, venda.getParcelas());
            statement.setDouble(8, venda.getJuros());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                venda.setId(rs.getInt(1));
            }

            rs.close();

            return venda;
        } 
    }

    public List<Venda> findVendasByData(LocalDate data){
        List<Venda> Vendas = new ArrayList<>();
        String sql = """
            SELECT * 
            FROM venda
            WHERE date(dia_horario) = ?        
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDate(1, Date.valueOf(data));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Venda Venda = resultSetToVenda(rs);
                Vendas.add(Venda);
            }

            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Vendas;
    }

    private Funcionario findFuncionarioDaVenda(int id){
        String sql = "SELECT * FROM funcionario WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return FuncionarioDAO.resultSetToFuncionario(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Cliente findClienteDaVenda(int id){
        String sql = "SELECT * FROM cliente WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return ClienteDAO.resultSetToCliente(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Unidade findUnidadeDaVenda(int id){
        String sql = "SELECT * FROM unidade WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return UnidadeDAO.resultSetToUnidade(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private FormaDePagamento findFormaDePagamentoDaVenda(int id){
        String sql = "SELECT * FROM forma_pagamento WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return FormaDePagamentoDAO.resultSetToFormaDePagamento(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private Venda resultSetToVenda(ResultSet rs) throws SQLException{
        Venda venda = new Venda(
            rs.getInt("id"),
            findFuncionarioDaVenda(rs.getInt("id_funcionario")),
            findClienteDaVenda(rs.getInt("id_cliente")),
            findUnidadeDaVenda(rs.getInt("id_unidade")),
            findFormaDePagamentoDaVenda(rs.getInt("id_forma_pagamento")),
            rs.getDouble("desconto"),
            rs.getInt("parcelas"),
            rs.getDouble("juros")
        );

        venda.diaHorarioParaResultSet(rs.getTimestamp("dia_horario").toLocalDateTime());

        return venda;
    }
}
