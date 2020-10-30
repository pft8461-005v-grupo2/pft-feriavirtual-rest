package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;

import java.util.List;

public interface ProcesoVentaService {

    List<ProcesoVenta> SP_PROCESOVENTA_CONSULTAR(ProcesoVenta procesoVenta) throws ClassNotFoundException;
}
