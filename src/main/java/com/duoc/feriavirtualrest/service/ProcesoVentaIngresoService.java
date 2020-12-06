package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;

import java.util.List;

public interface ProcesoVentaIngresoService {

    Object  SP_PROCESO_VENTA_INGRESO_CREAR(ProcesoVentaIngreso procesoVentaIngreso);
    Object  SP_PROCESO_VENTA_INGRESO_ACTUALIZAR(ProcesoVentaIngreso procesoVentaIngreso);
    List<ProcesoVentaIngreso> SP_PROCESO_VENTA_INGRESO_CONSULTAR(ProcesoVentaIngreso procesoVentaIngreso) throws ClassNotFoundException;
}
