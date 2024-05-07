package queries;

import conexion.Conexion;

import java.sql.*;

public class CompraProductoQueries {

    public static int insertarCompraProducto(int idcliente, int idproducto ,int unidades) throws SQLException {

        boolean flag = false;
        int nuevacompraid = -1;

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "INSERT INTO compra_producto VALUES(?, ? ,?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1,idcliente);
                ps.setInt(2,idproducto);
                ps.setInt(3,unidades);

                int nRows = ps.executeUpdate();

                flag = nRows == 1;

                try ( ResultSet generatedKeys = ps.getGeneratedKeys()){

                    if (generatedKeys.next()) {
                        nuevacompraid = generatedKeys.getInt(1);

                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return nuevacompraid;

        }



}}
