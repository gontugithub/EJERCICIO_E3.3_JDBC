package main;

import entidades.Cliente;
import entidades.Compra;
import entidades.CompraProducto;
import entidades.Producto;
import queries.ClienteQueries;
import queries.ProductoQueries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {




    inicioSesionUsuario();
    seleccionProducto();







    }


    public static void inicioSesionUsuario(){

        Cliente usuariologeado;

        System.out.print(" INTRODUCE SU ID: ");

        if((usuariologeado = ClienteQueries.comprobarUsuario(sc.nextInt()) ) != null){
            System.out.println(" BIENVENIDO "+ usuariologeado.getNombre());
            verCatalogoProductos();
        } else {
            System.out.println(" ERROR ID INEXISTENTE");
            inicioSesionUsuario();
        }





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
        } while ( cont < 2);


    }

    public static void seleccionProducto(){



    int seleccion;

        ArrayList<CompraProducto> carrito = new ArrayList<>();

        for (Producto p : ProductoQueries.getAllProductos()){
            System.out.println(p.toString() +"\n");
        }


        while (true){

            System.out.println("SELECCIONAR PRODUCTO: "); // AHORA GUARDARA EL PRODUCTO EN ORDEN DE ARRAY
            if ((seleccion = sc.nextInt()) == -1){
                break;
            }

            if (seleccion > ProductoQueries.getAllProductos().size()){
                System.out.println("VALOR INVALIDO");
            } else {

                if (carrito.isEmpty()){
                    carrito.add(new CompraProducto(1,seleccion,1));
                } else {

                    boolean flag = false;

                    for (int i = 0; i < carrito.size(); i++) {

                        if (carrito.get(i).getId_producto() == seleccion){
                            carrito.get(i).setUnidades(carrito.get(i).getUnidades()+1);
                            System.out.println("COINCIDENCIA");
                            flag = true;
                            break;
                        }

                    }

                    if (flag == false) { // NO SE HAN ENCONTRADO EL PRODUCTO ASI QUE CREAMOS UNA NUEVA PARTE EN EL ARRAY
                        carrito.add(new CompraProducto(1,seleccion, 1));
                        System.out.println("AÑADIDO");
                    }

            }


        }


        }

        for (int i = 0; i < carrito.size(); i++) {

            System.out.println(carrito.get(i).getId_producto() + " x"+carrito.get(i).getUnidades());

        }









    }




}
