package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;

import java.util.List;

public interface ProcesoVentaService {

    Object  SP_PROCESO_VENTA_CREAR(ProcesoVenta procesoVenta);
    Object  SP_PROCESO_VENTA_ACTUALIZAR(ProcesoVenta procesoVenta);
    List<ProcesoVenta> SP_PROCESOVENTA_CONSULTAR(ProcesoVenta procesoVenta) throws ClassNotFoundException;
}
