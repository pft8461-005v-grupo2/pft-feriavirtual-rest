package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.repository.SolicitudCompraRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("solicitudCompraService")
public class SolicitudCompraServiceImp implements SolicitudCompraService {

    Logger log = LoggerFactory.getLogger(SolicitudCompraServiceImp.class);

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;


    @Autowired
    @Qualifier("solicitudCompraRepository")
    private SolicitudCompraRepository solicitudCompraRepository;


    @Override
    public Object SP_SOLICITUD_COMPRA_CREAR(Solicitud_compra solicitud_compra) {
        try{
            return solicitudCompraRepository.SP_SOLICITUD_COMPRA_CREAR(
                    solicitud_compra.getCliente_id(),
                    solicitud_compra.getProducto(),
                    solicitud_compra.getKilogramos()
            );
        }catch (Exception e) {
            log.error("Error al crear una nueva solicitud de compra", e);
            return null;
        }
    }

    @Override
    public Object SP_SOLICITUD_COMPRA_ACTUALIZAR(Solicitud_compra solicitud_compra) {
        try{
            return solicitudCompraRepository.SP_SOLICITUD_COMPRA_ACTUALIZAR(
                    solicitud_compra.getId(),
                    solicitud_compra.getProducto(),
                    solicitud_compra.getKilogramos(),
                    solicitud_compra.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar una solicitud de compra", e);
            return new Object();
        }
    }

    @Override
    public List<Solicitud_compra> SP_SOLICITUD_COMPRA_CONSULTAR(Solicitud_compra solicitud_compra) throws ClassNotFoundException {
        return (List<Solicitud_compra>)(procedureService.ejecutarSP(SPConstant.SP_SOLICITUD_COMPRA_CONSULTAR, Solicitud_compra.class, solicitud_compra.generarDataIN()));
    }
}
