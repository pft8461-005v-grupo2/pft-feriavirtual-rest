package com.duoc.feriavirtualrest.model;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;

import java.util.List;

public class ProcesoVentaCompleto {

    private Cliente cliente;
    private Solicitud_compra solicitud_compra;
    private ProcesoVenta procesoVenta;
    private ProcesoVentaIngreso procesoVentaIngreso;
    private Ingreso ingreso;
    private Producto producto;
    private Productor productor;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Solicitud_compra getSolicitud_compra() {
        return solicitud_compra;
    }

    public void setSolicitud_compra(Solicitud_compra solicitud_compra) {
        this.solicitud_compra = solicitud_compra;
    }

    public ProcesoVenta getProcesoVenta() {
        return procesoVenta;
    }

    public void setProcesoVenta(ProcesoVenta procesoVenta) {
        this.procesoVenta = procesoVenta;
    }

    public ProcesoVentaIngreso getProcesoVentaIngreso() {
        return procesoVentaIngreso;
    }

    public void setProcesoVentaIngreso(ProcesoVentaIngreso procesoVentaIngreso) {
        this.procesoVentaIngreso = procesoVentaIngreso;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }
}
