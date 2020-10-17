package com.duoc.feriavirtualrest.model;

public class DetalleSubastaModel {

    private int transportista_id;
    private int subasta_id;
    private int valorPropuesta;

    public DetalleSubastaModel() {
    }

    public int getTransportista_id() {
        return transportista_id;
    }

    public void setTransportista_id(int transportista_id) {
        this.transportista_id = transportista_id;
    }

    public int getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(int subasta_id) {
        this.subasta_id = subasta_id;
    }

    public int getValorPropuesta() {
        return valorPropuesta;
    }

    public void setValorPropuesta(int valorPropuesta) {
        this.valorPropuesta = valorPropuesta;
    }
}
