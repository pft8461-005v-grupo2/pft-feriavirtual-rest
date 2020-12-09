package com.duoc.feriavirtualrest.entity.keys;


import javax.persistence.Column;
import java.io.Serializable;

public class DetalleSubastaKeys implements Serializable {

    @Column(name = "TRANSPORTISTA_ID")
    private Integer transportista_id;

    @Column(name = "SUBASTA_ID")
    private Integer subasta_id;

    public DetalleSubastaKeys() {
        // Sonar
    }

    public Integer getTransportista_id() {
        return transportista_id;
    }

    public void setTransportista_id(Integer transportista_id) {
        this.transportista_id = transportista_id;
    }

    public Integer getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(Integer subasta_id) {
        this.subasta_id = subasta_id;
    }
}
