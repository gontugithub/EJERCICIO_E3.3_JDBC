package main;

import entidades.Cliente;
import entidades.Producto;
import queries.ClienteQueries;
import queries.ProductoQueries;

import java.sql.SQLException;

public class MainAdmin {

    public static void main(String[] args) {


        try {
            System.out.println(ProductoQueries.eliminarProducto(5));




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
