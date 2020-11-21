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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    private Integer id;
    private Integer cliente_id;
    private Date fechacreacion;
    private String producto;
    private Integer kilogramos;
    private Integer habilitado;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    @Column(name = "CLIENTE_ID")
    public Integer getCliente_id() {
        return cliente_id;
    }

    @Column(name = "FECHACREACION")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    @NotNull
    @Size(min=3, max=50)
    @Column(name = "PRODUCTO")
    public String getProducto() {
        return producto;
    }

    @NotNull
    @Min(8)
    @Column(name = "KILOGRAMOS")
    public Integer getKilogramos() {
        return kilogramos;
    }

    @Column(name = "HABILITADO")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setKilogramos(Integer kilogramos) {
        this.kilogramos = kilogramos;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID_SOLICITUD_COMPRA", Integer.class, this.id == null ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_CLIENTE_ID", Integer.class, this.cliente_id == null ? null : this.cliente_id));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", java.sql.Timestamp.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_PRODUCTO", String.class, this.producto == null ? null : this.producto));
        LISTA_SP_IN.add(new SPDataIN("IN_KILOGRAMOS", Integer.class, this.kilogramos == null ? null : this.kilogramos));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", Integer.class, this.habilitado == null ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
