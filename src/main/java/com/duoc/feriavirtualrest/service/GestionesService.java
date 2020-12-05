package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;

import java.io.IOException;

public interface GestionesService {

    int iniciarProcesoVenta(ProcesoVenta procesoVenta) throws IOException, ClassNotFoundException;

    boolean verificarExistenciaProducto(String producto);

}
