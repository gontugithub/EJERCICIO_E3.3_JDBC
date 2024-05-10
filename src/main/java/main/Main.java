package main;

import entidades.Cliente;
import entidades.CompraProducto;
import entidades.Producto;
import queries.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class
Main {

    static ArrayList<CompraProducto> carrito = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int idusuariologeado;
    static int numerocompra = -1;
    public static void main(String[] args) throws SQLException {


        System.out.println("\n    BIENVENIDO A NUESTRA TIENDA\n ");


    inicioSesionUsuario();



    }


    public static void inicioSesionUsuario(){

        Cliente usuariologeado;

        System.out.print(" INTRODUCE SU ID: ");

        if((usuariologeado = ClienteQueries.comprobarUsuario(sc.nextInt()) ) != null){
            System.out.println(" BIENVENIDO "+ usuariologeado.getNombre());
            idusuariologeado = usuariologeado.getId();
           menu();
        } else {
            System.out.println(" ERROR ID INEXISTENTE");
            inicioSesionUsuario();
        }
        sc.nextLine();







    }

    public static void menu(){

        boolean flag = true;

        do {

            System.out.print("\n  > QUE DESEA HACER < \n\n" +
                    "   [1] VER CATALOGO PRODUCTOS \n" +
                    "   [2] AÑADIR PRODUCTOS A LA COMPRA\n" +
                    "   [3] PAGAR E IMPRIMIR TICKET\n" +
                    "   [4] CERRAR PROGRAMA\n      >> " );

            switch (sc.nextInt()){


                case 1:
                    verCatalogoProductos();
                    break;

                case 2:
                    carrito = seleccionProducto(carrito);
                    break;

                case 3:
                    realizarpago(carrito);
                    break;

                case 4:
                    flag = false;
                    break;

            }

        } while (flag);







    }


    public static void verCatalogoProductos(){


        ArrayList<Producto> list = new ArrayList<>();



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
                    sc.nextLine();
                    break;
                default:
                    System.out.println("ERROR");
                    break;

            }
            sc.nextLine();
            System.out.println();

            for (Producto p : list){
                System.out.println(p.toString() +"\n");
            }




    }

    public static ArrayList<CompraProducto> seleccionProducto(ArrayList<CompraProducto> carrito){

        int idcompra;

        if (numerocompra == -1){
            idcompra = nuevaCompra(idusuariologeado);
            numerocompra = idcompra;
        }else {
            idcompra = numerocompra;
        }






    int seleccion;

        for (Producto p : ProductoQueries.getAllProductos()){
            System.out.println(p.toString() +"\n");
        }


        while (true){

            System.out.println("SELECCIONAR PRODUCTO: "); // AHORA GUARDARA EL PRODUCTO EN ORDEN DE ARRAY
            if ((seleccion = sc.nextInt()) == -1){
                break;
            }

            sc.nextLine();

            if (seleccion > ProductoQueries.getAllProductos().size()){
                System.out.println("VALOR INVALIDO");
            } else {

                if (carrito.isEmpty()){
                    carrito.add(new CompraProducto(idcompra,seleccion,1));
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
                        carrito.add(new CompraProducto(idcompra,seleccion, 1));
                        System.out.println("AÑADIDO");
                    }

            }


        }


        }

        for (int i = 0; i < carrito.size(); i++) {

            System.out.println(carrito.get(i).getId_producto() + " x"+carrito.get(i).getUnidades());

        }

        return carrito;

    }

    public static int nuevaCompra(int idcomprador) {

        sc.nextLine();

        String concepto;
        int resultado = -1;

        System.out.println("INTRODUCE EL CONCEPTO DE LA COMPRA:");
        concepto = sc.nextLine();

        try {

            resultado = CompraQueries.nuevaCompra(concepto, idcomprador);

        } catch (SQLException e) {

            System.err.println("ERROR AL CREAR COMPRA");

        }

        return resultado;
    }

     public static void realizarpago(ArrayList<CompraProducto> carrito){

        String path = "ticketcompra"+numerocompra+".txt";
         int  numerocompra=carrito.get(0).getId_compra();;
         File ticket = new File(path);
         String textoticket = " TICKET COMPRA " + numerocompra + "\n";
         ArrayList<ArrayList<Object>> lista = new ArrayList<>();
         int total = 0;

         for (int i = 0; i < carrito.size(); i++) {

             try {
                 CompraProductoQueries.insertarCompraProducto(carrito.get(i).getId_compra(),carrito.get(i).getId_producto(),carrito.get(i).getUnidades());



             } catch (SQLException e) {
                e.printStackTrace();
             }

         }

         lista = CombinadasQueries.mostrarcompraprecionombreunidades(numerocompra);

         for (int i = 0; i < lista.size(); i++) {

             double operacion = (int) lista.get(i).get(0) * (double)lista.get(i).get(2);

            textoticket += "   - " +lista.get(i).get(1) + " x" + lista.get(i).get(0) + " " + operacion +"€\n";
            total += operacion;

         }

         textoticket += "\n TOTAL: " + total + "€";

         try {
             if (!ticket.exists()){
                 ticket.createNewFile();
             }

             System.out.println("  Imprimiendo ticket...");

             FileWriter escritor = new FileWriter(ticket);
             PrintWriter pw = new PrintWriter(escritor);

             pw.print(textoticket);
             pw.close();

             Desktop dt= Desktop.getDesktop();
             dt.open(ticket);



         } catch (IOException e) {
             throw new RuntimeException(e);
         }


     }





}
