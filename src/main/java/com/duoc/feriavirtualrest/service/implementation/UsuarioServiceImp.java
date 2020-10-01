package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.model.UsuarioModel;
import com.duoc.feriavirtualrest.repository.UsuarioRepository;
import com.duoc.feriavirtualrest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Object SP_CREAR_USUARIO(UsuarioModel usuarioModel) {
        return usuarioRepository.SP_CREAR_USUARIO(
                usuarioModel.getRol_id(),
                usuarioModel.getCorreo(),
                usuarioModel.getContrasena()
        );
    }
}