package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Transportista;

import java.util.List;

public interface TransportistaService {
    List<Transportista> SP_TRANSPORTISTA_CONSULTAR(Transportista transportista) throws ClassNotFoundException;
}
