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
import com.example.javafxproject.Tables.Compra;
import com.example.javafxproject.Tables.Funcionario;
import com.example.javafxproject.Tables.Unidade;

public class CompraDAO {
    public Compra create(Compra compra) throws SQLException{
        String sql = """
            INSERT INTO compra (id_funcionario, id_cliente, id_unidade, dia_horario) 
            VALUES (?, ?, ?, ?);    
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setInt(1, compra.getFuncionario().getId());
            statement.setInt(2, compra.getCliente().getId());
            statement.setInt(3, compra.getUnidade().getId());
            statement.setTimestamp(4, Timestamp.valueOf(compra.getDia_horario()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                compra.setId(rs.getInt(1));
            }

            rs.close();

            return compra;
        } 
    }

    public List<Compra> findComprasByData(LocalDate data){
        List<Compra> compras = new ArrayList<>();
        String sql = """
            SELECT * 
            FROM compra
            WHERE date(dia_horario) = ?        
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDate(1, Date.valueOf(data));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Compra compra = resultSetToCompra(rs);
                compras.add(compra);
            }

            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compras;
    }

    private Funcionario findFuncionarioDaCompra(int id){
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

    private Cliente findClienteDaCompra(int id){
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

    private Unidade findUnidadeDaCompra(int id){
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

    private Compra resultSetToCompra(ResultSet rs) throws SQLException{
        Compra compra = new Compra(
            rs.getInt("id"),
            findFuncionarioDaCompra(rs.getInt("id_funcionario")),
            findClienteDaCompra(rs.getInt("id_cliente")),
            findUnidadeDaCompra(rs.getInt("id_unidade"))
        );

        compra.diaHorarioParaResultSet(rs.getTimestamp("dia_horario").toLocalDateTime());

        return compra;
    }

}
