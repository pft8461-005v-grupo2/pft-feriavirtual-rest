package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.repository.UsuarioRepository;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {

    Logger log = LoggerFactory.getLogger(UsuarioServiceImp.class);

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;


    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;


    @Override
    public Object SP_USUARIO_CREAR(Usuario usuario) {
        try{
            return usuarioRepository.SP_USUARIO_CREAR(
                    usuario.getRol_id(),
                    usuario.getCorreo(),
                    usuario.getContrasena()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo usuario", e);
            return new Object();
        }
    }

    @Override
    public List<Usuario> SP_USUARIO_CONSULTAR(Usuario usuario) throws ClassNotFoundException {
        return (List<Usuario>)(procedureService.ejecutarSP(SPConstant.SP_USUARIO_CONSULTAR, Usuario.class, usuario.generarDataIN()));
    }


}