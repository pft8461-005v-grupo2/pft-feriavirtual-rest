package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.repository.ProductorRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProductorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductorService")
public class ProductorServiceImp implements ProductorService {

    Logger log = LoggerFactory.getLogger(ProductorServiceImp.class);

    @Autowired
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("productorRepository")
    private ProductorRepository productorRepository;

    @Override
    public Object SP_PRODUCTOR_CREAR(Productor productor) {
        try{
            return productorRepository.SP_PRODUCTOR_CREAR(
                    productor.getUsuario_id(),
                    productor.getRut(),
                    productor.getRazonsocial(),
                    productor.getDireccion(),
                    productor.getComuna(),
                    productor.getCorreo()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo productor", e);
            return new Object();
        }
    }

    @Override
    public Object SP_PRODUCTOR_ACTUALIZAR(Productor productor) {
        try{
            return productorRepository.SP_PRODUCTOR_ACTUALIZAR(
                    productor.getId(),
                    productor.getUsuario_id(),
                    productor.getRut(),
                    productor.getRazonsocial(),
                    productor.getDireccion(),
                    productor.getComuna(),
                    productor.getCorreo(),
                    productor.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un nuevo productor", e);
            return new Object();
        }
    }

    @Override
    public List<Productor> SP_PRODUCTOR_CONSULTAR(Productor productor) throws ClassNotFoundException {
        return (List<Productor>)(procedureService.ejecutarSP(SPConstant.SP_PRODUCTOR_CONSULTAR, Productor.class, productor.generarDataIN()));
    }
}
