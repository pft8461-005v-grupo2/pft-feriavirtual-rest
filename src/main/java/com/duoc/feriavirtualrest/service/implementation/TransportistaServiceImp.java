package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.repository.TransportistaRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.TransportistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transportistaService")
public class TransportistaServiceImp implements TransportistaService {

    Logger log = LoggerFactory.getLogger(TransportistaServiceImp.class);

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("transportistaRepository")
    private TransportistaRepository transportistaRepository;

    @Override
    public Object SP_TRANSPORTISTA_CREAR(Transportista transportista) {
        try{
            return transportistaRepository.SP_TRANSPORTISTA_CREAR(
                    transportista.getUsuario_id(),
                    transportista.getRut(),
                    transportista.getRazonsocial(),
                    transportista.getDireccion(),
                    transportista.getComuna(),
                    transportista.getCorreo()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo transportista", e);
            return new Object();
        }
    }

    @Override
    public List<Transportista> SP_TRANSPORTISTA_CONSULTAR(Transportista transportista) throws ClassNotFoundException {
        return (List<Transportista>)(procedureService.ejecutarSP(SPConstant.SP_TRANSPORTISTA_CONSULTAR, Transportista.class, transportista.generarDataIN()));
    }
}
