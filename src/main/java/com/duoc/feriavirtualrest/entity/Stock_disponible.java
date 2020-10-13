package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Stock_disponible {

    private int id;
    private int PRODUCTO_id;
//  private number kilogramos;
    private int kilogramos;
    private Date fechaultimamodificacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPRODUCTO_id() {
        return PRODUCTO_id;
    }

    public void setPRODUCTO_id(int PRODUCTO_id) {
        this.PRODUCTO_id = PRODUCTO_id;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public Date getFechaultimamodificacion() {
        return fechaultimamodificacion;
    }

    public void setFechaultimamodificacion(Date fechaultimamodificacion) {
        this.fechaultimamodificacion = fechaultimamodificacion;
    }
}
