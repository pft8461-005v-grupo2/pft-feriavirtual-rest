package com.duoc.feriavirtualrest.entity;

import java.util.Date;

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
}
