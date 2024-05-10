package queries;

import conexion.Conexion;
import entidades.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CombinadasQueries {



    public static ArrayList<Producto> mostrarcompraprecionombreunidades(int id_compra){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select cp.unidades, p.nombre, p.precio from compra_producto as cp, producto as p, compra as c where\n" +
                    "    cp.id_producto = p.id AND cp.id_compra = c.id AND c.id = ?";

            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setInt(1,id_compra);

                try (ResultSet rs = ps.executeQuery()){

                    while (rs.next()) {
                        int id = rs.getInt("unidades");
                        String nombre = rs.getString("nombre");
                        double precio = rs.getDouble("precio");

                        listaProductos.add(new Producto(id, nombre, precio));
                    }

                }

            }

        } catch (SQLException e) {
            System.out.println("ERROR");
        }

        return listaProductos;

    }
}
