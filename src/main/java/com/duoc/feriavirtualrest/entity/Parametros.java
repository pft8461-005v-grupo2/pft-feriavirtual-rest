package com.duoc.feriavirtualrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametros")
public class Parametros {

    private Integer id;
    private String descripcion;
    private Integer valor;

    public Parametros() {
    }

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    @Column(name = "VALOR")
    public Integer getValor() {
        return valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
