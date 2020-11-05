package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Contrato;

import java.util.List;

public interface ContratoService {
    Object SP_CONTRATO_CREAR(Contrato contrato);
    List<Contrato> SP_CONTRATO_CONSULTAR(Contrato contrato) throws ClassNotFoundException;
}
