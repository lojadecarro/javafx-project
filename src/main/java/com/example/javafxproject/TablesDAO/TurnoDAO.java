package com.example.javafxproject.TablesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.javafxproject.Conexao.Conexao;
import com.example.javafxproject.Tables.Turno;

public class TurnoDAO {
    public Turno create(Turno turno) throws SQLException {
        String sql = """
        INSERT INTO turno (inicio, fim)
        VALUES (?, ?);
        """;
        
        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection
            .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setTime(1, Time.valueOf(turno.getInicio()));
            statement.setTime(2, Time.valueOf(turno.getFim()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                turno.setId(rs.getInt(1));
            }

            rs.close();

            return turno;
        }
    }

    public Turno update(Turno turno) throws SQLException {
        String sql = """
        UPDATE turno
        SET inicio = ?, fim = ?
        WHERE id = ?;
        """;

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setTime(1, Time.valueOf(turno.getInicio()));
            statement.setTime(2, Time.valueOf(turno.getFim()));
            statement.setInt(3, turno.getId());
            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return turno;
            }
            return null;

            } catch (SQLException e) {
                return null;
            }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM turno WHERE id = ?;";

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

    /*
    public void delete(Turno turno) {
        delete(turno.getId());
    }
    */

    public Turno findById(Integer id) {
        String sql = "SELECT * FROM turno WHERE id = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToTurno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public static List<Turno> listarTurnos() {
        String sql = "SELECT * FROM turno;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            List<Turno> turnos = new ArrayList<>();
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Turno turno = resultSetToTurno(rs);
                turnos.add(turno);
            }

            rs.close();
            return turnos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Turno findByInicioEFim(LocalTime inicio, LocalTime fim) {
        String sql = "SELECT * FROM turno WHERE inicio = ? AND fim = ?;";

        try (
            Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setTime(1, Time.valueOf(inicio));
            statement.setTime(2, Time.valueOf(fim));

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToTurno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    protected static Turno resultSetToTurno(ResultSet rs) throws SQLException {
        return new Turno(
            rs.getInt("id"),
            rs.getTime("inicio").toLocalTime(),
            rs.getTime("fim").toLocalTime()
        );
    }
}