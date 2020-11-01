package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.util.SPDataIN;
import oracle.sql.CHAR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROCESO_VENTA")
public class ProcesoVenta implements Serializable {

    private int id;
    private int ingreso_id;
    private int solicitud_compra_id;
    private int subasta_id;
    private int etapa;
    private Date fechacreacion;
    private char clienteaceptaacuerdo;
    private int precioventatotal;
    private int preciocostototal;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ingreso_id")
    public int getIngreso_id() {
        return ingreso_id;
    }

    public void setIngreso_id(int ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    @Column(name = "solicitud_compra_id")
    public int getSolicitud_compra_id() {
        return solicitud_compra_id;
    }

    public void setSolicitud_compra_id(int solicitud_compra_id) {
        this.solicitud_compra_id = solicitud_compra_id;
    }

    @Column(name = "subasta_id")
    public int getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(int subasta_id) {
        this.subasta_id = subasta_id;
    }

    @Column(name = "etapa")
    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
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
    public char getClienteaceptaacuerdo() {
        return clienteaceptaacuerdo;
    }

    public void setClienteaceptaacuerdo(char clienteaceptaacuerdo) {
        this.clienteaceptaacuerdo = clienteaceptaacuerdo;
    }
    @Column(name = "precioventatotal")
    public int getPrecioventatotal() {
        return precioventatotal;
    }

    public void setPrecioventatotal(int precioventatotal) {
        this.precioventatotal = precioventatotal;
    }

    @Column(name = "preciocostototal")
    public int getPreciocostototal() {
        return preciocostototal;
    }

    public void setPreciocostototal(int preciocostototal) {
        this.preciocostototal = preciocostototal;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_INGRESO_ID", Integer.class, this.ingreso_id == 0 ? null : this.ingreso_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SOLICITUD_COMPRA_ID", Integer.class, this.solicitud_compra_id == 0 ? null : this.solicitud_compra_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SUBASTA_ID", Integer.class, this.subasta_id == 0 ? null : this.subasta_id));
        LISTA_SP_IN.add(new SPDataIN("IN_ETAPA", Integer.class, this.etapa == 0 ? null : this.etapa));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", Date.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_CLIENTEACEPTAACUERDO", String.class, this.clienteaceptaacuerdo == 0 ? null : this.clienteaceptaacuerdo));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOVENTATOTAL", Integer.class, this.precioventatotal == 0 ? null : this.precioventatotal));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOCOSTOTOTAL", Integer.class, this.preciocostototal == 0 ? null : this.preciocostototal));
        return LISTA_SP_IN;
    }
}



