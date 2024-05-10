package entidades;

public class CompraProducto {

    private int id_compra;
    private int id_producto;
    private int unidades;

    public CompraProducto(int id_compra, int id_producto, int unidades) {
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.unidades = unidades;
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
