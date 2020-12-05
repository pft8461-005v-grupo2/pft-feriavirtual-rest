package com.duoc.feriavirtualrest.entity.keys;


import javax.persistence.Column;
import java.io.Serializable;

public class ProcesoVentaIngresoKeys implements Serializable  {

    @Column(name = "INGRESO_ID")
    private Integer ingreso_id;
    @Column(name = "PROCESO_VENTA_ID")
    private Integer proceso_venta_id;

    public ProcesoVentaIngresoKeys() {
    }

    public Integer getIngreso_id() {
        return ingreso_id;
    }

    public void setIngreso_id(Integer ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    public Integer getProceso_venta_id() {
        return proceso_venta_id;
    }

    public void setProceso_venta_id(Integer proceso_venta_id) {
        this.proceso_venta_id = proceso_venta_id;
    }
}
