package queries;

import conexion.Conexion;
import entidades.Cliente;
import entidades.Producto;

import java.sql.*;
import java.util.ArrayList;

public class ClienteQueries {

    public static Cliente comprobarUsuario(int ide) {

        Cliente usuario = null;


        try (Connection connection = Conexion.open()) {

            String query = "SELECT * FROM cliente WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, ide);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        usuario = new Cliente(id,nombre);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

}