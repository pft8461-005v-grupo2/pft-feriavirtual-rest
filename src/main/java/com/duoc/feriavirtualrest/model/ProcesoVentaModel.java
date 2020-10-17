package com.duoc.feriavirtualrest.model;
import java.util.Date;

public class ProcesoVentaModel {

    private int id;
    private int ingreso_id;
    private int solicitud_compra_id;
    private int subasta_id;
    private int etapa;
    private Date fechaDeCreacion;
    private char clienteAceptaAcuerdo;
    private int precioVentaTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngreso_id() {
        return ingreso_id;
    }

    public void setIngreso_id(int ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    public int getSolicitud_compra_id() {
        return solicitud_compra_id;
    }

    public void setSolicitud_compra_id(int solicitud_compra_id) {
        this.solicitud_compra_id = solicitud_compra_id;
    }

    public int getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(int subasta_id) {
        this.subasta_id = subasta_id;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public char getClienteAceptaAcuerdo() {
        return clienteAceptaAcuerdo;
    }

    public void setClienteAceptaAcuerdo(char clineteAceptaAcuerdo) {
        this.clienteAceptaAcuerdo = clineteAceptaAcuerdo;
    }

    public int getPrecioVentaTotal() {
        return precioVentaTotal;
    }

    public void setPrecioVentaTotal(int precioVentaTotal) {
        this.precioVentaTotal = precioVentaTotal;
    }

    public int getPrecioCostoTotal() {
        return precioCostoTotal;
    }

    public void setPrecioCostoTotal(int precioCostoTotal) {
        this.precioCostoTotal = precioCostoTotal;
    }

    private int precioCostoTotal;

    public ProcesoVentaModel() {
    }


}
