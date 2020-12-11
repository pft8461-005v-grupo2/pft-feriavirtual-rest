package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Subasta;

import java.io.IOException;

public interface GestionesService {

    int iniciarProcesoVenta(ProcesoVenta procesoVenta) throws IOException, ClassNotFoundException;

    int iniciarSubasta(Subasta subasta) throws ClassNotFoundException, IOException;

    int detenerSubasta(Subasta subasta) throws ClassNotFoundException, IOException;

    void actualizarValoresProcesoVenta(int procesoVentaId) throws ClassNotFoundException;
}
