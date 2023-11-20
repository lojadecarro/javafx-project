package com.example.javafxproject.TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.Tables.Marca;

public class MarcaDAO {
    public Marca create(Marca marca) throws SQLException{
        String sql = """
        INSERT INTO marca (nome) VALUES
        (?);
        """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, marca.getNome());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                marca.setId(rs.getInt(1));
            }

            rs.close();

            return marca;
        }
    }

    public Marca update(Marca marca) throws SQLException{
        String sql = """
        UPDATE marca
        SET nome = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, marca.getNome());
            statement.setInt(2, marca.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return marca;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public Marca findByNome(String nome) {
        String sql = "SELECT * FROM marca WHERE nome = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, nome);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToMarca(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static List<Marca> listarMarcas() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marca;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Marca marca = resultSetToMarca(rs);
                marcas.add(marca);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return marcas;
    }

    protected static Marca resultSetToMarca(ResultSet rs) throws SQLException {
        return new Marca(
            rs.getInt("id"),
            rs.getString("nome")
        );
    }
}

    