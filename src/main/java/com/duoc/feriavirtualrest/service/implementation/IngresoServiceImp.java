package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.repository.IngresoRepository;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ingresoService")
public class IngresoServiceImp implements IngresoService {

    Logger log = LoggerFactory.getLogger(IngresoServiceImp.class);

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private ProcedureService procedureService;

    @Override
    public Object SP_INGRESO_CREAR(Ingreso ingreso) {
        try{
            return ingresoRepository.SP_INGRESO_CREAR(
                    ingreso.getProductor_id(),
                    ingreso.getProducto_id(),
                    ingreso.getKilogramos(),
                    ingreso.getPreciokgcostounitario()
            );

        }catch (Exception e) {
            log.error("Error al crear un nuevo ingreso", e);
            return new Ingreso();
        }
    }

    @Override
    public Object SP_INGRESO_ACTUALIZAR(Ingreso ingreso) {
        try{
            return ingresoRepository.SP_INGRESO_ACTUALIZAR(
                    ingreso.getId(),
                    ingreso.getProducto_id(),
                    ingreso.getKilogramos(),
                    ingreso.getPreciokgcostounitario(),
                    ingreso.getPreciokgventaunitario(),
                    ingreso.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un ingreso", e);
            return new Object();
        }
    }

    @Override
    public List<Ingreso> SP_INGRESO_CONSULTAR(Ingreso ingreso) throws ClassNotFoundException {
        return (List<Ingreso>) (procedureService.ejecutarSP(
                SPConstant.SP_INGRESO_CONSULTAR, Ingreso.class, ingreso.generarDataIN()));
    }
}
