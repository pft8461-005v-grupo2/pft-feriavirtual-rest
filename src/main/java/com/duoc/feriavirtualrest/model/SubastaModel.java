package com.duoc.feriavirtualrest.model;
import java.util.Date;

public class SubastaModel {
    private int id;
    private Date fechaTermino;
    private int precioGanador;
    private char habilitado;

    public SubastaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public int getPrecioGanador() {
        return precioGanador;
    }

    public void setPrecioGanador(int precioGanador) {
        this.precioGanador = precioGanador;
    }

    public char getHabilitado() { return habilitado; }

    public void setHabilitado(char habilitado) { this.habilitado = habilitado; }
}
