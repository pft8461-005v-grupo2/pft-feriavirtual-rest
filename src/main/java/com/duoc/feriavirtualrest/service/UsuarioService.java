package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.UsuarioModel;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UsuarioService {
    Object          SP_USUARIO_CREAR(UsuarioModel usuarioModel);
    List<Usuario>   SP_USUARIO_CONSULTAR(int id) throws ClassNotFoundException;
    Object          SP_USUARIO_CONSULTAR_CORREO(String correo) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
