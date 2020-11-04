package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Contrato;
import java.util.List;

public interface ContratoService {
    List<Contrato> SP_CONTRATO_CONSULTAR(Contrato contrato) throws ClassNotFoundException;
}
