package entidades;

public class CompraProducto {

    private int id_compra;
    private int id_producto;
    private int unidades;

    private String nombre;
    private int precio;

    public CompraProducto(int unidades, String nombre, int precio) {
        this.unidades = unidades;
        this.nombre = nombre;
        this.precio = precio;
    }

    public CompraProducto(int id_compra, int id_producto, int unidades) {
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.unidades = unidades;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
