package com.duoc.feriavirtualrest.model;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;

import java.util.List;

public class ProcesoVentaCompleto {

    private Cliente cliente;
    private Solicitud_compra solicitud_compra;
    private ProcesoVenta procesoVenta;

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
}
