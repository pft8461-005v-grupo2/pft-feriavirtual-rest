package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Subasta;

import java.util.List;

public interface SubastaService {
    Object  SP_SUBASTA_CREAR(Subasta subasta);
    Object  SP_SUBASTA_ACTUALIZAR(Subasta subasta);
    List<Subasta> SP_SUBASTA_CONSULTAR(Subasta subata) throws ClassNotFoundException;

}
