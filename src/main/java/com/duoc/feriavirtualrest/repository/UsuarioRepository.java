package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.entity.Usuario;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("usuarioRepository")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Procedure(name = "SP_USUARIO_CREAR")
    Object SP_USUARIO_CREAR(
            @Param("IN_ROL_ID") int IN_ROL_ID,
            @Param("IN_CORREO") String IN_CORREO,
            @Param("IN_CONTRASENA") String IN_CONTRASENA
            );
}