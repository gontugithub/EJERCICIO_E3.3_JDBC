package main;

import queries.ClienteQueries;

import java.sql.SQLException;

public class MainAdmin {

    public static void main(String[] args) {


        try {
            System.out.println(ClienteQueries.modificarUsuario("pepe", 4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
