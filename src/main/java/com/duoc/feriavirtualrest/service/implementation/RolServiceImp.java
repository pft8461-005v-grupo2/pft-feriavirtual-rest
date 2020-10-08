package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.Rol;
import com.duoc.feriavirtualrest.service.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service("rolService")
public class RolServiceImp implements RolService {

    Logger log = LoggerFactory.getLogger(RolServiceImp.class);

    @PersistenceUnit
    private EntityManagerFactory emf;


    @Override
    public List<Rol> SP_ROL_CONSULTAR_ID(int id) {
        try{
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery procedureQuery =
                    em.createStoredProcedureQuery("PORTAFOLIO.SP_ROL_CONSULTAR_ID", Rol.class);
            procedureQuery.registerStoredProcedureParameter("IN_ID", Integer.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter("OUT_RESULTADO", Class.class, ParameterMode.REF_CURSOR);
            procedureQuery.setParameter("IN_ID", id);
            procedureQuery.execute();
            List<Rol> resultado = procedureQuery.getResultList();
            return resultado;

        }catch (Exception e) {
            log.error("Error al consultar rol por id", e);
            return new ArrayList<>();
        }
    }

}
