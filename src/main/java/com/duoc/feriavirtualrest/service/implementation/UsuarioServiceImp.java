package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.repository.UsuarioRepository;
import com.duoc.feriavirtualrest.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceUnit;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {

    Logger log = LoggerFactory.getLogger(UsuarioServiceImp.class);

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    @PersistenceUnit
    private EntityManagerFactory emf;


    @Override
    public Object SP_USUARIO_CREAR(UsuarioModel usuarioModel) {
        try{
            return usuarioRepository.SP_USUARIO_CREAR(
                    usuarioModel.getRol_id(),
                    usuarioModel.getCorreo(),
                    usuarioModel.getContrasena()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo usuario", e);
            return new Object();
        }

    }

    @Override
    public List<Usuario> SP_USUARIO_CONSULTAR(int id) {
        try{
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery procedureQuery =
                    em.createStoredProcedureQuery("PORTAFOLIO.SP_USUARIO_CONSULTAR", Usuario.class);
            procedureQuery.registerStoredProcedureParameter("IN_ID", Integer.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter("OUT_RESULTADO", Class.class, ParameterMode.REF_CURSOR);
            procedureQuery.setParameter("IN_ID", id);
            procedureQuery.execute();
            List<Usuario> resultado = procedureQuery.getResultList();
            return resultado;
        }catch (Exception e) {
            log.error("Error al consultar usuario por id", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Object SP_USUARIO_CONSULTAR_CORREO(String correo) {
        try{
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery procedureQuery =
                    em.createStoredProcedureQuery("PORTAFOLIO.SP_USUARIO_CONSULTAR_CORREO", Usuario.class);
            procedureQuery.registerStoredProcedureParameter("IN_CORREO", String.class, ParameterMode.IN);
            procedureQuery.registerStoredProcedureParameter("OUT_RESULTADO", Class.class, ParameterMode.REF_CURSOR);
            procedureQuery.setParameter("IN_CORREO", correo);
            procedureQuery.execute();
            List<Usuario> resultado = procedureQuery.getResultList();
            return resultado;
        }catch (Exception e) {
            log.error("Error al consultar usuario por correo", e);
            return new ArrayList<>();
        }
    }
}