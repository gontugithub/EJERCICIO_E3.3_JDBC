package main;

import entidades.Producto;
import queries.ProductoQueries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {












    }


    public static void inicioSesionUsuario(){

        System.out.println(" NOMBRE: ");
        String nombre = sc.next();
        System.out.println(" CONTRASEÑA: ");
        String password = sc.next();


    }




    public static void verCatalogoProductos(){

        int cont = 0;
        ArrayList<Producto> list = new ArrayList<>();

        do {

            System.out.print(" [1] ORGANIZADO MAS CARO A MAS BARATO\n" +
                    " [2] ORGANIZADO MAS BARATO A MAS CARO\n" +
                    " [3] ORGANIZADO ALFABETICAMENTE POR NOMBRE\n" +
                    " [4] FILTRAR POR CADENA\n" +
                    " >> ");

            switch (sc.nextInt()){

                case 1:
                    list = ProductoQueries.getProductosPrecioDesc();
                    break;
                case 2:
                    list = ProductoQueries.getProductosPrecioAsc();
                    break;
                case 3:
                    list = ProductoQueries.getProductosNombreAlfabetico();
                    break;
                case 4:
                    System.out.print("INTRODUCE LA CADENA QUE QUIERES BUSCAR\n >>  ");
                    list = ProductoQueries.getProductosFiltradoCadena(sc.next());
                    break;
                default:
                    System.out.println("ERROR");
                    break;

            }
            System.out.println();
            for (Producto p : list){
                System.out.println(p.toString() +"\n");
            }

            cont++;
        } while ( cont < 6);


    }



}
