package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTRATO")
public class Contrato {

    private int id;
    private Date fechainicio;
    private Date fechatermino;
    private Date fechacreacion;
    private char vigencia;

    public Contrato() {
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "FECHAINICIO")
    public Date getFechainicio() {
        return fechainicio;
    }

    @Column(name = "FECHATERMINO")
    public Date getFechatermino() {
        return fechatermino;
    }

    @Column(name = "FECHACREACION")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    @Column(name = "VIGENCIA")
    public char getVigencia() {
        return vigencia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechatermino(Date fechatermino) {
        this.fechatermino = fechatermino;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public void setVigencia(char vigencia) {
        this.vigencia = vigencia;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHAINICIO", Date.class, this.fechainicio == null ? null : this.fechainicio));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHATERMINO", Date.class, this.fechatermino == null ? null : this.fechatermino));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", Date.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_VIGENCIA", String.class, this.vigencia == 0 ? null : this.vigencia));
        return LISTA_SP_IN;
    }
}

