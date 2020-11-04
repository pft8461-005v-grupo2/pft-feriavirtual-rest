package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.TransportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transportistaService")
public class TransportistaServiceImp implements TransportistaService {

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;

    @Override
    public List<Transportista> SP_TRANSPORTISTA_CONSULTAR(Transportista transportista) throws ClassNotFoundException {
        return (List<Transportista>)(procedureService.ejecutarSP(SPConstant.SP_TRANSPORTISTA_CONSULTAR, Transportista.class, transportista.generarDataIN()));
    }
}
