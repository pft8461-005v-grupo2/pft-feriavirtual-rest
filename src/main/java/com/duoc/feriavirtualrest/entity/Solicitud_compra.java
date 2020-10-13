package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Solicitud_compra {

    private int id;
    private int CLIENTE_id;
    private Date fechacreacion;
    private char habilitado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCLIENTE_id() {
        return CLIENTE_id;
    }

    public void setCLIENTE_id(int CLIENTE_id) {
        this.CLIENTE_id = CLIENTE_id;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public char getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(char habilitado) {
        this.habilitado = habilitado;
    }
}
