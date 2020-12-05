package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_PRODUCTO_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PRODUCTO_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_DESCRIPCION", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_PRODUCTO_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PRODUCTO_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_PRODUCTO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_DESCRIPCION", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_HABILITADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    private Integer id;
    private String descripcion;
    private Integer habilitado;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "HABILITADO")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == null ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_DESCRIPCION", String.class, this.descripcion == null ? null : this.descripcion));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", Integer.class, this.habilitado == null ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
