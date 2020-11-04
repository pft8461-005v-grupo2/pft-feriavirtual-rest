package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contratoService")
public class ContratoServiceImp implements ContratoService {

    @Autowired
    private ProcedureService procedureService;

    @Override
    public List<Contrato> SP_CONTRATO_CONSULTAR(Contrato contrato) throws ClassNotFoundException {
        return (List<Contrato>)(procedureService.ejecutarSP(SPConstant.SP_CONTRATO_CONSULTAR, Contrato.class, contrato.generarDataIN()));
    }
}
