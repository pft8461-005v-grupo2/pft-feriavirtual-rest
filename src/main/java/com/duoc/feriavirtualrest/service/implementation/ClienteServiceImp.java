package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clienteService")
public class ClienteServiceImp implements ClienteService {

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;

    @Override
    public List<Cliente> SP_CLIENTE_CONSULTAR(Cliente cliente) throws ClassNotFoundException {
        return (List<Cliente>)(procedureService.ejecutarSP(SPConstant.SP_CLIENTE_CONSULTAR, Cliente.class, cliente.generarDataIN()));

    }
}
