package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Subasta {

    private int id;
    private Date fechatermino;
    private int precioganador;
    private char habilitado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(Date fechatermino) {
        this.fechatermino = fechatermino;
    }

    public int getPrecioganador() {
        return precioganador;
    }

    public void setPrecioganador(int precioganador) {
        this.precioganador = precioganador;
    }

    public char getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(char habilitado) {
        this.habilitado = habilitado;
    }
}
