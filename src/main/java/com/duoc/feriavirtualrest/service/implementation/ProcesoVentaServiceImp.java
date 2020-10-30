package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("procesoVentaService")
public class ProcesoVentaServiceImp implements ProcesoVentaService {

    @Autowired
    private ProcedureService procedureService;

    @Override
    public List<ProcesoVenta> SP_PROCESOVENTA_CONSULTAR(ProcesoVenta procesoVenta) throws ClassNotFoundException {
        return (List<ProcesoVenta>) (procedureService.ejecutarSP(
                SPConstant.SP_PROCESOVENTA_CONSULTAR, ProcesoVenta.class, procesoVenta.generarDataIN()));
    }
}
