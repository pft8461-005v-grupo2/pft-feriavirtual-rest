package com.duoc.feriavirtualrest.entity;

import java.util.Date;

public class Ingreso {

    private int id;
    private int PRODUCTOR_id;
    private int PRODUCTO_id;
    private Date fechacreacion;
//  private number kilogramos;
    private int kilogramos;
    private int preciokgcostounitario;
    private int preciokgventaunitario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPRODUCTOR_id() {
        return PRODUCTOR_id;
    }

    public void setPRODUCTOR_id(int PRODUCTOR_id) {
        this.PRODUCTOR_id = PRODUCTOR_id;
    }

    public int getPRODUCTO_id() {
        return PRODUCTO_id;
    }

    public void setPRODUCTO_id(int PRODUCTO_id) {
        this.PRODUCTO_id = PRODUCTO_id;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public int getPreciokgcostounitario() {
        return preciokgcostounitario;
    }

    public void setPreciokgcostounitario(int preciokgcostounitario) {
        this.preciokgcostounitario = preciokgcostounitario;
    }

    public int getPreciokgventaunitario() {
        return preciokgventaunitario;
    }

    public void setPreciokgventaunitario(int preciokgventaunitario) {
        this.preciokgventaunitario = preciokgventaunitario;
    }
}
