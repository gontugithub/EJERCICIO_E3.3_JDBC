package main;

import entidades.Cliente;
import entidades.CompraProducto;
import entidades.Producto;
import queries.ClienteQueries;
import queries.CompraProductoQueries;
import queries.CompraQueries;
import queries.ProductoQueries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class
Main {

    static Scanner sc = new Scanner(System.in);
    static int idusuariologeado;
    public static void main(String[] args) throws SQLException {




    inicioSesionUsuario();
    realizarpago(seleccionProducto());





    }


    public static void inicioSesionUsuario(){

        Cliente usuariologeado;

        System.out.print(" INTRODUCE SU ID: ");

        if((usuariologeado = ClienteQueries.comprobarUsuario(sc.nextInt()) ) != null){
            System.out.println(" BIENVENIDO "+ usuariologeado.getNombre());
            idusuariologeado = usuariologeado.getId();
            verCatalogoProductos();
        } else {
            System.out.println(" ERROR ID INEXISTENTE");
            inicioSesionUsuario();
        }
        sc.nextLine();







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

            cont++;
        } while ( cont < 2);


    }

    public static ArrayList<CompraProducto> seleccionProducto(){

       int idcompra = nuevaCompra(idusuariologeado);



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


         for (int i = 0; i < carrito.size(); i++) {

             try {
                 CompraProductoQueries.insertarCompraProducto(carrito.get(i).getId_compra(),carrito.get(i).getId_producto(),carrito.get(i).getUnidades());
             } catch (SQLException e) {
                e.printStackTrace();
             }

         }




     }





}
