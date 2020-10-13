package com.duoc.feriavirtualrest.model;

public class DetalleSolicitudModel {

    private int id;
    private int solicitud_compra_id;
    private String producto;
    private int kilogramos;

    public DetalleSolicitudModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolicitud_compra_id() {
        return solicitud_compra_id;
    }

    public void setSolicitud_compra_id(int solicitud_compra_id) {
        this.solicitud_compra_id = solicitud_compra_id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }
}
