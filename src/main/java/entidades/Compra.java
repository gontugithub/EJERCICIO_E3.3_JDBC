package entidades;

public class Compra {

    private int id;
    private String concepto;
    private int id_cliente;

    public Compra(int id, String concepto, int id_cliente) {
        this.id = id;
        this.concepto = concepto;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
