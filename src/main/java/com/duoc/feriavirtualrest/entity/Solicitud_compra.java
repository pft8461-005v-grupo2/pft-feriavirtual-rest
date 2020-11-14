package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.util.SPDataIN;
import oracle.sql.DATE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_SOLICITUD_COMPRA_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_SOLICITUD_COMPRA_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_CLIENTE_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRODUCTO", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_KILOGRAMOS", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_SOLICITUD_COMPRA_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_SOLICITUD_COMPRA_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_ID_SOLICITUD_COMPRA", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CLIENTE_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRODUCTO", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_KILOGRAMOS", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_HABILITADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "SOLICITUD_COMPRA")
public class Solicitud_compra {

    private int id;
    private int cliente_id;
    private Date fechacreacion;
    private String producto;
    private int kilogramos;
    private int habilitado;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "CLIENTE_ID")
    public int getCliente_id() {
        return cliente_id;
    }

    @Column(name = "FECHACREACION")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    @Column(name = "PRODUCTO")
    public String getProducto() {
        return producto;
    }

    @Column(name = "KILOGRAMOS")
    public int getKilogramos() {
        return kilogramos;
    }

    @Column(name = "HABILITADO")
    public int getHabilitado() {
        return habilitado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_CLIENTE_ID", Integer.class, this.cliente_id == 0 ? null : this.cliente_id));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", DATE.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_PRODUCTO", String.class, this.producto == null ? null : this.producto));
        LISTA_SP_IN.add(new SPDataIN("IN_KILOGRAMOS", Integer.class, this.kilogramos == 0 ? null : this.kilogramos));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", Integer.class, this.habilitado == 0 ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
