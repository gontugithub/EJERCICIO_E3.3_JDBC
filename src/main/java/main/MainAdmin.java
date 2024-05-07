package main;

import entidades.Cliente;
import entidades.Producto;
import queries.ClienteQueries;
import queries.ProductoQueries;

import java.sql.SQLException;

public class MainAdmin {

    public static void main(String[] args) {


        for (Cliente c : ClienteQueries.getAllClientes()){
            System.out.println(c.toString() +"\n");
        }


    }


}
