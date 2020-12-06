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
import java.util.Date;
import java.util.List;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_INGRESO_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_INGRESO_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_PRODUCTOR_ID", type=Integer.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_PRODUCTO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_KILOGRAMOS", type=Integer.class),
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_PRECIO_KG_COSTO_UNITARIO", type=Integer.class),

                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_INGRESO_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_INGRESO_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_INGRESO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRODUCTO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_KILOGRAMOS", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRECIO_KG_COSTO_UNITARIO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRECIO_KG_VENTA_UNITARIO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_HABILITADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "INGRESO")
public class Ingreso {

    private Integer id;
    private Integer productor_id;
    private Integer producto_id;
    private Date fechacreacion;
    private Integer kilogramos;
    private Integer preciokgcostounitario;
    private Integer preciokgventaunitario;
    private Integer habilitado;

    public Ingreso() {
    }

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    @Column(name = "PRODUCTOR_ID")
    public Integer getProductor_id() {
        return productor_id;
    }

    @Column(name = "PRODUCTO_ID")
    public Integer getProducto_id() {
        return producto_id;
    }

    @Column(name = "FECHACREACION")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    @Column(name = "KILOGRAMOS")
    public Integer getKilogramos() {
        return kilogramos;
    }

    @Column(name = "PRECIOKGCOSTOUNITARIO")
    public Integer getPreciokgcostounitario() {
        return preciokgcostounitario;
    }

    @Column(name = "PRECIOKGVENTAUNITARIO")
    public Integer getPreciokgventaunitario() {
        return preciokgventaunitario;
    }

    @Column(name = "HABILITADO")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductor_id(Integer productor_id) {
        this.productor_id = productor_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public void setKilogramos(Integer kilogramos) {
        this.kilogramos = kilogramos;
    }

    public void setPreciokgcostounitario(Integer preciokgcostounitario) {
        this.preciokgcostounitario = preciokgcostounitario;
    }

    public void setPreciokgventaunitario(Integer preciokgventaunitario) {
        this.preciokgventaunitario = preciokgventaunitario;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == null ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("in_productor_id", Integer.class, this.productor_id == null ? null : this.productor_id));
        LISTA_SP_IN.add(new SPDataIN("in_producto_id", Integer.class, this.producto_id == null ? null : this.producto_id));
        LISTA_SP_IN.add(new SPDataIN("in_fechaCreacion", java.sql.Timestamp.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("in_kilogramos", Integer.class, this.kilogramos == null ? null : this.kilogramos));
        LISTA_SP_IN.add(new SPDataIN("in_precioKgCostoUnitario", Integer.class, this.preciokgcostounitario == null ? null : this.preciokgcostounitario));
        LISTA_SP_IN.add(new SPDataIN("in_precioKgVentaUnitario", Integer.class, this.preciokgventaunitario == null ? null : this.preciokgventaunitario));
        LISTA_SP_IN.add(new SPDataIN("in_habilitado", Integer.class, this.habilitado == null ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
