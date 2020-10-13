package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Object          SP_USUARIO_CREAR(UsuarioModel usuarioModel);
    List<Usuario>   SP_USUARIO_CONSULTAR(int id);
    Object          SP_USUARIO_CONSULTAR_CORREO(String correo);
}
