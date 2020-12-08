package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.repository.ProcesoVentaIngresoRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("procesoVentaIngresoService")
public class ProcesoVentaIngresoServiceImp implements ProcesoVentaIngresoService {

    Logger log = LoggerFactory.getLogger(ProcesoVentaIngresoServiceImp.class);

    @Autowired
    private ProcesoVentaIngresoRepository procesoVentaIngresoRepository;

    @Autowired
    private ProcedureService procedureService;

    @Override
    public Object SP_PROCESO_VENTA_INGRESO_CREAR(ProcesoVentaIngreso procesoVentaIngreso) {
        try{
            return procesoVentaIngresoRepository.SP_PROCESO_VENTA_INGRESO_CREAR(
                    procesoVentaIngreso.getIngreso_id(),
                    procesoVentaIngreso.getProceso_venta_id(),
                    procesoVentaIngreso.getKilogramosocupados()
            );
        }catch (Exception e) {
            log.error("Error al crear un proceso venta ingreso", e);
            return new Object();
        }
    }

    @Override
    public Object SP_PROCESO_VENTA_INGRESO_ACTUALIZAR(ProcesoVentaIngreso procesoVentaIngreso) {
        try{
            return procesoVentaIngresoRepository.SP_PROCESO_VENTA_INGRESO_ACTUALIZAR(
                    procesoVentaIngreso.getIngreso_id(),
                    procesoVentaIngreso.getProceso_venta_id(),
                    procesoVentaIngreso.getKilogramosocupados(),
                    procesoVentaIngreso.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un proceso venta ingreso", e);
            return new Object();
        }
    }

    @Override
    public List<ProcesoVentaIngreso> SP_PROCESO_VENTA_INGRESO_CONSULTAR(ProcesoVentaIngreso procesoVentaIngreso) throws ClassNotFoundException {
        return (List<ProcesoVentaIngreso>)(procedureService.ejecutarSP(SPConstant.SP_PROCESO_VENTA_INGRESO_CONSULTAR, ProcesoVentaIngreso.class, procesoVentaIngreso.generarDataIN()));
    }
}
