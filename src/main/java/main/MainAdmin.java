package main;

import entidades.Cliente;
import entidades.Producto;
import queries.ClienteQueries;
import queries.ProductoQueries;

import java.sql.SQLException;
import java.util.Scanner;

public class MainAdmin {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\n BIENVENDIO AL MODO ADMINISTRADOR");


            menu();





        System.out.println(" ADIOS");


    }

    public static void menu(){

        boolean flag = true;

        do {

            System.out.print("\n  > QUE DESEA HACER < \n\n" +
                    "   [1] DAR DE ALTA NUEVO CLIENTE\n" +
                    "   [2] ELIMINAR CLIENTE\n" +
                    "   [3] MODIFICAR CLIENTE\n" +
                    "   [4] DAR DE ALTA PRODUCTO\n" +
                    "   [5] ELIMINAR PRODUCTO\n" +
                    "   [6] MODIFICAR PRODUCTO\n" +
                    "   [7] CERRAR PROGRAMA\n      >> " );

            switch (sc.nextInt()){



                case 1:
                    sc.nextLine();
                    System.out.print("  ** DAR DE ALTA NUEVO CLIENTE **\n  NOMBRE: ");
                    try {
                        String nombre = sc.nextLine();
                        int id;
                        if (( id= ClienteQueries.insertarNuevoUsuario(nombre))!= -1){
                            System.out.println("  CLIENTE " + nombre.toUpperCase() + " INSERTADO CON ID [" + id + "]");

                        } else{
                            System.err.println("ERROR AL INSERTAR CLIENTE");
                            break;
                        }
                        System.out.println();
                    } catch (SQLException e) {
                        System.err.println("SQL ERROR");
                    }
                    break;

                case 2:
                    System.out.print("  ** ELIMINAR CLIENTE **\n  ID: ");
                    int iddel = sc.nextInt();
                    try {

                        if (ClienteQueries.eliminarUsuario(iddel)!= -1){
                            System.out.println("  CLIENTE ELIMINADO CON ID [" + iddel + "]");

                        } else{
                            System.err.println("ERROR AL ELIMINAR CLIENTE");
                            break;
                        }

                    } catch (SQLException e) {
                        System.err.println("ERROR SQL");
                    }
                    break;

                case 3:
                    System.out.print("  ** MODIFICAR CLIENTE **\n  ID: ");
                    int idmod = sc.nextInt();
                    sc.nextLine();
                    System.out.print("   NUEVO NOMBRE: ");
                    String nombremod = sc.nextLine();

                    try {

                        if (ClienteQueries.modificarUsuario(nombremod,idmod)!= -1){
                            System.out.println(" CLIENTE MODIFICADO CON ID [" + idmod + "]");

                        } else{
                            System.err.println("ERROR AL MODIFICAR CLIENTE");
                            break;
                        }
                    } catch (SQLException e) {
                        System.err.println("ERROR SQL");
                    }
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("  ** DAR DE ALTA NUEVO PRODUCTO **\n  NOMBRE PRODUCTO: ");
                    try {
                        String innombreproducto = sc.nextLine();
                        System.out.print("  PRECIO: ");
                        double inprecio = sc.nextDouble();
                        sc.nextLine();
                        int idinproducto;
                        if (( idinproducto = ProductoQueries.insertarNuevoProducto(innombreproducto,inprecio))!= -1){
                            System.out.println("  PRODUCTO " + innombreproducto.toUpperCase() + " INSERTADO CON ID [" + idinproducto + "]");

                        } else{
                            System.err.println("ERROR AL INSERTAR PRODUCTO");
                            break;
                        }
                        System.out.println();
                    } catch (SQLException e) {
                        System.err.println("SQL ERROR");
                    }
                    break;

                case 5:
                    System.out.print("  ** ELIMINAR PRODUCTO **\n  ID: ");
                    int iddelpro = sc.nextInt();
                    try {

                        if (ProductoQueries.eliminarProducto(iddelpro)!= -1){
                            System.out.println("  PRODUCTO ELIMINADO CON ID [" + iddelpro + "]");

                        } else{
                            System.err.println("ERROR AL ELIMINAR PRODUCTO");
                            break;
                        }

                    } catch (SQLException e) {
                        System.err.println("ERROR SQL");
                    }
                    break;


                case 6:
                    System.out.print("  ** MODIFICAR PRODUCTO **\n  ID: ");
                    int idmodpro = sc.nextInt();
                    sc.nextLine();
                    System.out.print("  NUEVO NOMBRE: ");
                    String nombremodpro = sc.nextLine();
                    System.out.print("  NUEVO PRECIO: ");
                    double preciomod = sc.nextDouble();

                    try {

                        if (ProductoQueries.modificarProducto(nombremodpro,idmodpro,preciomod)!= -1){
                            System.out.println(" PRODUCTO MODIFICADO CON ID [" + idmodpro + "]");

                        } else{
                            System.err.println("ERROR AL MODIFICAR PRODUCTO");
                            break;
                        }
                    } catch (SQLException e) {
                        System.err.println("ERROR SQL");
                    }

                    break;

                case 7:
                    flag = false;
                    break;

                default:
                    System.out.println("  OPCION INVALIDA");
                    break;
            }

        } while (flag);



    }


}
