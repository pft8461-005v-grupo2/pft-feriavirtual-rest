package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;

import java.util.List;

public interface DetalleSubastaService {
    Object SP_DETALLE_SUBASTA_CREAR(Detalle_subasta detalle_subasta);
    Object SP_DET_SUBASTA_ACTUALIZAR(Detalle_subasta detalle_subasta);
    List<Detalle_subasta> SP_DETALLE_SUBASTA_CONSULTAR(Detalle_subasta detalle_subasta) throws ClassNotFoundException;

}
