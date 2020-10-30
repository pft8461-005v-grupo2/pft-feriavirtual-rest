package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.util.SPDataIN;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcesoVenta {

    private int id;
    private int INGRESO_id;
    private String SOLICITUD_COMPRA_id;
    private String SUBASTA_id;
    private int etapa;
    private Date fechacreacion;
    private char clienteaceptaacuerdo;
    private int precioventatotal;
    private int preciocostotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getINGRESO_id() {
        return INGRESO_id;
    }

    public void setINGRESO_id(int INGRESO_id) {
        this.INGRESO_id = INGRESO_id;
    }

    public String getSOLICITUD_COMPRA_id() {
        return SOLICITUD_COMPRA_id;
    }

    public void setSOLICITUD_COMPRA_id(String SOLICITUD_COMPRA_id) {
        this.SOLICITUD_COMPRA_id = SOLICITUD_COMPRA_id;
    }

    public String getSUBASTA_id() {
        return SUBASTA_id;
    }

    public void setSUBASTA_id(String SUBASTA_id) {
        this.SUBASTA_id = SUBASTA_id;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public char getClienteaceptaacuerdo() {
        return clienteaceptaacuerdo;
    }

    public void setClienteaceptaacuerdo(char clienteaceptaacuerdo) {
        this.clienteaceptaacuerdo = clienteaceptaacuerdo;
    }

    public int getPrecioventatotal() {
        return precioventatotal;
    }

    public void setPrecioventatotal(int precioventatotal) {
        this.precioventatotal = precioventatotal;
    }

    public int getPreciocostotal() {
        return preciocostotal;
    }

    public void setPreciocostotal(int preciocostotal) {
        this.preciocostotal = preciocostotal;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_INGRESO_ID", Integer.class, this.INGRESO_id == 0 ? null : this.INGRESO_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SOLICITUD_COMPRA_ID", Integer.class, this.SOLICITUD_COMPRA_id == null ? null : this.SOLICITUD_COMPRA_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SUBASTA_ID", Integer.class, this.SUBASTA_id == null ? null : this.SUBASTA_id));
        LISTA_SP_IN.add(new SPDataIN("IN_ETAPA", String.class, this.etapa == 0 ? null : this.etapa));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", String.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_CLIENTEACEPTAACUERDO", String.class, this.clienteaceptaacuerdo == 0 ? null : this.clienteaceptaacuerdo));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOVENTATOTAL", String.class, this.precioventatotal == 0 ? null : this.precioventatotal));
        LISTA_SP_IN.add(new SPDataIN("IN_PRECIOCOSTOTOTAL", String.class, this.preciocostotal == 0 ? null : this.preciocostotal));
        return LISTA_SP_IN;
    }
}



