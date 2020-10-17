package com.duoc.feriavirtualrest.model;
import java.util.Date;

public class IngresoModel {

    private int id;
    private int productor_id;
    private int producto_id;
    private Date fechaCreacion;
    private int kilogramos;
    private int precioKgCostoUnitario;
    private int precioKgVentaUnitario;

    public IngresoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductor_id() {
        return productor_id;
    }

    public void setProductor_id(int productor_id) {
        this.productor_id = productor_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public int getPrecioKgCostoUnitario() {
        return precioKgCostoUnitario;
    }

    public void setPrecioKgCostoUnitario(int precioKgCostoUnitario) {
        this.precioKgCostoUnitario = precioKgCostoUnitario;
    }

    public int getPrecioKgVentaUnitario() {
        return precioKgVentaUnitario;
    }

    public void setPrecioKgVentaUnitario(int precioKgVentaUnitario) {
        this.precioKgVentaUnitario = precioKgVentaUnitario;
    }
}
