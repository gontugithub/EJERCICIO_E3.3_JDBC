package queries;

import conexion.Conexion;
import entidades.Producto;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.*;
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

    public static int eliminarProducto(int id) throws SQLException {

        boolean flag = false;
        int clienteinsertado = -1;

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "DELETE FROM producto WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1,id);

                int nRows = ps.executeUpdate();

                flag = nRows == 1;

                clienteinsertado = nRows;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return clienteinsertado;

        }

    }

    public static int insertarNuevoProducto(String nombreproducto, double precio) throws SQLException {

        int productoinsertado = -1;

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "INSERT INTO producto VALUES(null,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nombreproducto);
                ps.setDouble(2, precio);

                int nRows = ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        productoinsertado = generatedKeys.getInt(1);

                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return productoinsertado;

        }


    }

    public static int modificarProducto(String nombre, int id, double precio) throws SQLException {

        boolean flag = false;
        int clienteinsertado = -1;

        try (Connection connection = Conexion.open()) {
            // CONEXIÓN CORRECTA
            String query = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nombre);
                ps.setDouble(2,precio);
                ps.setInt(3,id);

                int nRows = ps.executeUpdate();

                flag = nRows == 1;

                clienteinsertado = nRows;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return clienteinsertado;

        }

    }

}


