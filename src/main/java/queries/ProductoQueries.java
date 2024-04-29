package queries;

import conexion.Conexion;
import entidades.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoQueries {

    public static ArrayList<Producto> getAllProductos(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select * from producto";
            try (PreparedStatement ps = connection.prepareStatement(query)){

                try (ResultSet rs = ps.executeQuery()){

                    while (rs.next()) {
                        int id = rs.getInt("id");
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

    public static ArrayList<Producto> getProductosPrecioDesc(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select * from producto order by precio DESC";
            try (PreparedStatement ps = connection.prepareStatement(query)){

                try (ResultSet rs = ps.executeQuery()){

                    while (rs.next()) {
                        int id = rs.getInt("id");
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

    public static ArrayList<Producto> getProductosPrecioAsc(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select * from producto order by precio ASC";
            try (PreparedStatement ps = connection.prepareStatement(query)){

                try (ResultSet rs = ps.executeQuery()){

                    while (rs.next()) {
                        int id = rs.getInt("id");
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

    public static ArrayList<Producto> getProductosNombreAlfabetico(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select * from producto order by nombre;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
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

    public static ArrayList<Producto> getProductosFiltradoCadena(String cadena){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "select * from producto where nombre like ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, "%" + cadena + "%");

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
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


