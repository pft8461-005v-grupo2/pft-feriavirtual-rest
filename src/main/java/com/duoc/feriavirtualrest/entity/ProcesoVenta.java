package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.util.SPDataIN;
import oracle.sql.CHAR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_PROCESO_VENTA_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PROCESO_VENTA_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_SOLICITUD_COMPRA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_PROCESO_VENTA_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PROCESO_VENTA_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_PROCESO_VENTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_ETAPA", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_SUBASTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CLIENTEACEPTAACUERDO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRECIOVENTATOTAL", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRECIOCOSTOTOTAL", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "PROCESO_VENTA")
public class ProcesoVenta implements Serializable {

    private Integer id;
    private Integer solicitud_compra_id;
    private Integer subasta_id;
    private Integer etapa;
    private Date fechacreacion;
    private Integer clienteaceptaacuerdo;
    private Integer precioventatotal;
    private Integer preciocostototal;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "solicitud_compra_id")
    public Integer getSolicitud_compra_id() {
        return solicitud_compra_id;
    }

    public void setSolicitud_compra_id(Integer solicitud_compra_id) {
        this.solicitud_compra_id = solicitud_compra_id;
    }

    @Column(name = "subasta_id")
    public Integer getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(Integer subasta_id) {
        this.subasta_id = subasta_id;
    }

    @Column(name = "etapa")
    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    @Column(name = "fechacreacion")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @Column(name = "clienteaceptaacuerdo")
    public Integer getClienteaceptaacuerdo() {
        return clienteaceptaacuerdo;
    }

    public void setClienteaceptaacuerdo(Integer clienteaceptaacuerdo) {
        this.clienteaceptaacuerdo = clienteaceptaacuerdo;
    }

    @Column(name = "precioventatotal")
    public Integer getPrecioventatotal() {
        return precioventatotal;
    }

    public void setPrecioventatotal(Integer precioventatotal) {
        this.precioventatotal = precioventatotal;
    }

    @Column(name = "preciocostototal")
    public Integer getPreciocostototal() {
        return preciocostototal;
    }

    public void setPreciocostototal(Integer preciocostototal) {
        this.preciocostototal = preciocostototal;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == null ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_SOLICITUD_COMPRA_ID", Integer.class, this.solicitud_compra_id == null ? null : this.solicitud_compra_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SUBASTA_ID", Integer.class, this.subasta_id == null ? null : this.subasta_id));
        LISTA_SP_IN.add(new SPDataIN("IN_ETAPA", Integer.class, this.etapa == null ? null : this.etapa));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", java.sql.Timestamp.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_CLIENTEACEPTAACUERDO", String.class, this.clienteaceptaacuerdo == null ? null : this.clienteaceptaacuerdo));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOVENTATOTAL", Integer.class, this.precioventatotal == null ? null : this.precioventatotal));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOCOSTOTOTAL", Integer.class, this.preciocostototal == null ? null : this.preciocostototal));
        return LISTA_SP_IN;
    }
}



