package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.repository.ProcesoVentaRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("procesoVentaService")
public class ProcesoVentaServiceImp implements ProcesoVentaService {

    Logger log = LoggerFactory.getLogger(ProcesoVentaServiceImp.class);


    @Autowired
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("procesoVentaRepository")
    private ProcesoVentaRepository procesoVentaRepository;

    @Override
    public Object SP_PROCESO_VENTA_CREAR(ProcesoVenta procesoVenta) {
        try{
            return procesoVentaRepository.SP_PROCESO_VENTA_CREAR(
                    procesoVenta.getSolicitud_compra_id()
            );

        }catch (Exception e) {
            log.error("Error al crear un nuevo proceso de venta", e);
            return new ProcesoVenta();
        }
    }

    @Override
    public Object SP_PROCESO_VENTA_ACTUALIZAR(ProcesoVenta procesoVenta) {
        try{
            return procesoVentaRepository.SP_PROCESO_VENTA_ACTUALIZAR(
                    procesoVenta.getId(),
                    procesoVenta.getEtapa(),
                    procesoVenta.getClienteaceptaacuerdo(),
                    procesoVenta.getPrecioventatotal(),
                    procesoVenta.getPreciocostototal()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un proceso de venta", e);
            return new Object();
        }
    }

    @Override
    public List<ProcesoVenta> SP_PROCESOVENTA_CONSULTAR(ProcesoVenta procesoVenta) throws ClassNotFoundException {
        return (List<ProcesoVenta>) (procedureService.ejecutarSP(
                SPConstant.SP_PROCESO_VENTA_CONSULTAR, ProcesoVenta.class, procesoVenta.generarDataIN()));
    }
}
