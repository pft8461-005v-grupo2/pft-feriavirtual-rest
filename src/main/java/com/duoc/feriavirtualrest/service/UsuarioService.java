package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.UsuarioModel;

import java.util.List;

public interface UsuarioService {
    Object          SP_USUARIO_CREAR(UsuarioModel usuarioModel);
    List<Usuario>   SP_USUARIO_CONSULTAR(Usuario usuario) throws ClassNotFoundException;
}
