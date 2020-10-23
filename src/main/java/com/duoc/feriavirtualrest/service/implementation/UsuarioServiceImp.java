package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.UsuarioModel;
import com.duoc.feriavirtualrest.repository.UsuarioRepository;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.service.UtilService;
import com.duoc.feriavirtualrest.util.SPDataIN;
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


    @Autowired
    @Qualifier("utilService")
    private UtilService utilService;



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
    public List<Usuario> SP_USUARIO_CONSULTAR(int id) throws ClassNotFoundException {

        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, id));
        return (List<Usuario>)(utilService.ejecutarSP(SPConstant.SP_USUARIO_CONSULTAR, Usuario.class, LISTA_SP_IN));

    }

    @Override
    public Object SP_USUARIO_CONSULTAR_CORREO(String correo) throws ClassNotFoundException {

        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_CORREO", String.class, correo));
        return (List<Usuario>)(utilService.ejecutarSP(SPConstant.SP_USUARIO_CONSULTAR_CORREO, Usuario.class, LISTA_SP_IN));

    }
}