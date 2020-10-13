package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Detalle_solicitud {

    private int id;
    private int SOLICITUD_COMPRA_id;
    private String producto;
  //private number kilogramos;
    private int kilogramos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSOLICITUD_COMPRA_id() {
        return SOLICITUD_COMPRA_id;
    }

    public void setSOLICITUD_COMPRA_id(int SOLICITUD_COMPRA_id) {
        this.SOLICITUD_COMPRA_id = SOLICITUD_COMPRA_id;
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
