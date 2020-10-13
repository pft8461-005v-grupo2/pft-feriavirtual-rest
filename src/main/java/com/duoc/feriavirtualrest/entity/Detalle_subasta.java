package com.duoc.feriavirtualrest.entity;

public class Detalle_subasta {

    private int TRANSPORTISTA_id;
    private int SUBASTA_id;
    private int valorpropuesta;

    public int getTRANSPORTISTA_id() {
        return TRANSPORTISTA_id;
    }

    public void setTRANSPORTISTA_id(int TRANSPORTISTA_id) {
        this.TRANSPORTISTA_id = TRANSPORTISTA_id;
    }

    public int getSUBASTA_id() {
        return SUBASTA_id;
    }

    public void setSUBASTA_id(int SUBASTA_id) {
        this.SUBASTA_id = SUBASTA_id;
    }

    public int getValorpropuesta() {
        return valorpropuesta;
    }

    public void setValorpropuesta(int valorpropuesta) {
        this.valorpropuesta = valorpropuesta;
    }
}
