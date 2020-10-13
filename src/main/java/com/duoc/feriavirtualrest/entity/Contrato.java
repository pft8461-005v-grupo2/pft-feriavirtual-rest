package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Contrato {

    private int id;
    private int PRODUCTOR_id;
    private Date fechainicio;
    private String fechatermino;
    private String fechacreacion;
    private char vigencia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPRODUCTOR_id() {
        return PRODUCTOR_id;
    }

    public void setPRODUCTOR_id(int PRODUCTOR_id) {
        this.PRODUCTOR_id = PRODUCTOR_id;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(String fechatermino) {
        this.fechatermino = fechatermino;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public char getVigencia() {
        return vigencia;
    }

    public void setVigencia(char vigencia) {
        this.vigencia = vigencia;
    }
}
