package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductorService")
public class ProductorServiceImp implements ProductorService {

    @Autowired
    private ProcedureService procedureService;

    @Override
    public List<Productor> SP_PRODUCTOR_CONSULTAR(Productor productor) throws ClassNotFoundException {
        return (List<Productor>)(procedureService.ejecutarSP(SPConstant.SP_PRODUCTOR_CONSULTAR, Productor.class, productor.generarDataIN()));
    }
}
