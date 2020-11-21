package com.duoc.feriavirtualrest.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSP implements Serializable {

    private static final long serialVersionUID = -829808208687594595L;

    @JsonProperty("OUT_GLOSA")
    private String OUT_GLOSA;

    @JsonProperty("OUT_ESTADO")
    private int OUT_ESTADO;

    @JsonProperty("OUT_ID_SALIDA")
    private int OUT_ID_SALIDA;

    public ResponseSP() {
    }

    public String getOUT_GLOSA() {
        return OUT_GLOSA;
    }

    public void setOUT_GLOSA(String OUT_GLOSA) {
        this.OUT_GLOSA = OUT_GLOSA;
    }

    public int getOUT_ESTADO() {
        return OUT_ESTADO;
    }

    public void setOUT_ESTADO(int OUT_ESTADO) {
        this.OUT_ESTADO = OUT_ESTADO;
    }

    public int getOUT_ID_SALIDA() {
        return OUT_ID_SALIDA;
    }

    public void setOUT_ID_SALIDA(int OUT_ID_SALIDA) {
        this.OUT_ID_SALIDA = OUT_ID_SALIDA;
    }
}