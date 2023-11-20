package com.example.javafxproject.TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.Tables.Cor;


public class CorDAO {
    public Cor create(Cor cor) throws SQLException {
        String sql = """
        INSERT INTO cor (nome)
        VALUES (?);
        """;
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, cor.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                cor.setId(rs.getInt(1));
            }

            rs.close();

            return cor;
        }
    }

    public Cor update(Cor cor) throws SQLException {
        String sql = """
        UPDATE cor
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, cor.getNome());
            statement.setInt(2, cor.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return cor;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    /*
    public void delete(Integer id) {
        String sql = "DELETE FROM cor WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Cor cor) {
        delete(cor.getId());
    }
    */

    public static Cor findByNome(String nome) {
        String sql = "SELECT * FROM cor WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToCor(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

     public static List<Cor> listarCores() { 
        List<Cor> cores = new ArrayList<>();
        String sql = "SELECT * FROM cor;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Cor cor = resultSetToCor(rs);
                cores.add(cor);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return cores;
    }

    protected static Cor resultSetToCor(ResultSet rs) throws SQLException {
        return new Cor(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
    
}