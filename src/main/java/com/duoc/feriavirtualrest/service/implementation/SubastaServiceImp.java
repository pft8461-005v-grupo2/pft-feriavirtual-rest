package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Subasta;
import com.duoc.feriavirtualrest.repository.ClienteRepository;
import com.duoc.feriavirtualrest.repository.SubastaRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.SubastaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subastaService")
public class SubastaServiceImp implements SubastaService {

    Logger log = LoggerFactory.getLogger(SubastaServiceImp.class);

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("subastaRepository")
    private SubastaRepository subastaRepository;

    @Override
    public Object SP_SUBASTA_CREAR(Subasta subasta) {
        try{
            return subastaRepository.SP_SUBASTA_CREAR(
                    subasta.getFechatermino()
            );
        }catch (Exception e) {
            log.error("Error al crear una nueva subasta", e);
            return new Object();
        }
    }

    @Override
    public Object SP_SUBASTA_ACTUALIZAR(Subasta subasta) {
        try{
            return subastaRepository.SP_SUBASTA_ACTUALIZAR(
                    subasta.getId(),
                    subasta.getFechatermino(),
                    subasta.getPrecioganador(),
                    subasta.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar una subasta", e);
            return new Object();
        }
    }

    @Override
    public List<Subasta> SP_SUBASTA_CONSULTAR(Subasta subata) throws ClassNotFoundException {
        return (List<Subasta>)(procedureService.ejecutarSP(SPConstant.SP_SUBASTA_CONSULTAR, Subasta.class, subata.generarDataIN()));
    }
}
