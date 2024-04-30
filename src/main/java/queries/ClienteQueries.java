package queries;

import conexion.Conexion;
import entidades.Producto;

import java.sql.*;

public class ClienteQueries {

    public static int comprobarUsuario(String user, String pasw) {

        try (Connection connection = Conexion.open()) {

            String query = "SELECT * FROM clientes WHERE nombre = ? AND password = ? ";

            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user);
                ps.setString(2, pasw);

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        return -1;
                    }
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}