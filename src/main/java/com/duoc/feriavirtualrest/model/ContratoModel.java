package com.duoc.feriavirtualrest.model;
import java.util.Date;

public class ContratoModel {

    private int id;
    private int productor_id;
    private Date fechaInicio;
    private Date fechaTermino;
    private Date fechaCreacion;
    private char vigencia;

    public ContratoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductor_id() {
        return productor_id;
    }

    public void setProductor_id(int productor_id) {
        this.productor_id = productor_id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public char getVigencia() { return vigencia; }

    public void setVigencia(char vigencia) { this.vigencia = vigencia; }
}
