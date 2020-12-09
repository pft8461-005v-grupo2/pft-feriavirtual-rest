package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;
import com.duoc.feriavirtualrest.repository.DetalleSubastaRepository;
import com.duoc.feriavirtualrest.service.DetalleSubastaService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("detalleSubastaService")
public class DetalleSubastaServiceImp implements DetalleSubastaService {

    Logger log = LoggerFactory.getLogger(DetalleSubastaServiceImp.class);

    @Autowired
    private DetalleSubastaRepository detalleSubastaRepository;

    @Autowired
    private ProcedureService procedureService;

    @Override
    public Object SP_DETALLE_SUBASTA_CREAR(Detalle_subasta detalle_subasta) {
        try{
            return detalleSubastaRepository.SP_DETALLE_SUBASTA_CREAR(
                    detalle_subasta.getTransportista_id(),
                    detalle_subasta.getSubasta_id(),
                    detalle_subasta.getValorpropuesta()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo detalle subasta", e);
            return new Object();
        }
    }

    @Override
    public Object SP_DET_SUBASTA_ACTUALIZAR(Detalle_subasta detalle_subasta) {
        try{
            return detalleSubastaRepository.SP_DET_SUBASTA_ACTUALIZAR(
                    detalle_subasta.getTransportista_id(),
                    detalle_subasta.getSubasta_id(),
                    detalle_subasta.getValorpropuesta()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un detalle subasta", e);
            return new Object();
        }
    }

    @Override
    public List<Detalle_subasta> SP_DETALLE_SUBASTA_CONSULTAR(Detalle_subasta detalle_subasta) throws ClassNotFoundException {
        return (List<Detalle_subasta>)(procedureService.ejecutarSP(SPConstant.SP_DETALLE_SUBASTA_CONSULTAR, Detalle_subasta.class, detalle_subasta.generarDataIN()));
    }
}
