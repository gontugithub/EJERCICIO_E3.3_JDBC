package queries;

import conexion.Conexion;
import entidades.Producto;

import java.sql.*;
import java.util.ArrayList;

public class CompraQueries {

    public static int nuevaCompra(String concepto, int idcliente) throws SQLException {

        boolean flag = false;
        int nuevacompraid = -1;

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
                String query = "INSERT INTO compra VALUES(null, ? ,?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, concepto);
                ps.setInt(2,idcliente);

                int nRows = ps.executeUpdate();

                flag = nRows == 1;

                try ( ResultSet generatedKeys = ps.getGeneratedKeys()){

                    if (generatedKeys.next()) {
                        nuevacompraid = generatedKeys.getInt(1);

                }

            }

        } catch (SQLException e) {
            System.out.println("ERROR");
        }

        return nuevacompraid;

    }



}}
