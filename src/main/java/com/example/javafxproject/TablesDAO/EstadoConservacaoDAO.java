package com.example.javafxproject.TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.Tables.EstadoConservacao;

public class EstadoConservacaoDAO {
    public EstadoConservacao create(EstadoConservacao estadoConservacao) throws SQLException {
        String sql = """
        INSERT INTO estado_conservacao (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, estadoConservacao.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                estadoConservacao.setId(rs.getInt(1));
            }

            rs.close();

            return estadoConservacao;
        }
    }

    public EstadoConservacao update(EstadoConservacao estadoConservacao) throws SQLException {
        String sql = """
        UPDATE estado_conservacao
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, estadoConservacao.getNome());
            statement.setInt(2, estadoConservacao.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return estadoConservacao;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public static EstadoConservacao findByNome(String nome) {
        String sql = "SELECT * FROM estado_conservacao WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToEstadoConservacao(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static List<EstadoConservacao> listarEstadosDeConservacao() { 
        List<EstadoConservacao> ecs = new ArrayList<>();
        String sql = "SELECT * FROM estado_conservacao;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                EstadoConservacao estadoConservacao = resultSetToEstadoConservacao(rs);
                ecs.add(estadoConservacao);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return ecs;
    }

    protected static EstadoConservacao resultSetToEstadoConservacao(ResultSet rs) throws SQLException {
        return new EstadoConservacao(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}
