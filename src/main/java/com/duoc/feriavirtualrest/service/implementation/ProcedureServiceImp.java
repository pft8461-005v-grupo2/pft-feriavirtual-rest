package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.util.SPDataIN;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceUnit;
import javax.persistence.StoredProcedureQuery;

import java.util.List;

@Service("procedureService")
public class ProcedureServiceImp implements ProcedureService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public List<?> ejecutarSP(String SP_NAME, Class<?> SP_CLASS_REF, List<SPDataIN> SP_IN_DATA) {

        EntityManager em = emf.createEntityManager();
        StoredProcedureQuery procedureQuery =
                em.createStoredProcedureQuery(SPConstant.TABLE_SPACE + SP_NAME, SP_CLASS_REF);

        SP_IN_DATA.stream().forEach( in -> {
            procedureQuery.registerStoredProcedureParameter(in.getSP_IN_PARAMETER(), in.getSP_IN_CLASS(), ParameterMode.IN);
            procedureQuery.setParameter(in.getSP_IN_PARAMETER(), in.getSP_IN_DATA());
        });

        procedureQuery.registerStoredProcedureParameter("out_resultado", Class.class, ParameterMode.REF_CURSOR);
        procedureQuery.execute();

        List<?> resultado = procedureQuery.getResultList();
        return resultado;
    }
}
