package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("clienteRepository")
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Procedure(name = SPConstant.SP_CLIENTE_CREAR)
    Object SP_CLIENTE_CREAR(
            @Param("IN_USUARIO_ID") int IN_USUARIO_ID,
            @Param("IN_IDENTIFICADOR") String IN_IDENTIFICADOR,
            @Param("IN_RAZON_SOCIAL") String IN_RAZON_SOCIAL,
            @Param("IN_DIRECCION") String IN_DIRECCION,
            @Param("IN_CIUDAD") String IN_CIUDAD,
            @Param("IN_PAIS_ORIGEN") String IN_PAIS_ORIGEN,
            @Param("IN_TIPO_CLIENTE") int IN_TIPO_CLIENTE,
            @Param("IN_CORREO") String IN_CORREO
    );

    @Procedure(name = SPConstant.SP_CLIENTE_ACTUALIZAR)
    Object SP_CLIENTE_ACTUALIZAR(
            @Param("IN_ID_CLIENTE") int IN_ID_CLIENTE,
            @Param("IN_IDENTIFICADOR") String IN_IDENTIFICADOR,
            @Param("IN_RAZON_SOCIAL") String IN_RAZON_SOCIAL,
            @Param("IN_DIRECCION") String IN_DIRECCION,
            @Param("IN_CIUDAD") String IN_CIUDAD,
            @Param("IN_PAIS_ORIGEN") String IN_PAIS_ORIGEN,
            @Param("IN_TIPO_CLIENTE") int IN_TIPO_CLIENTE,
            @Param("IN_CORREO") String IN_CORREO,
            @Param("IN_HABILITADO") Integer IN_HABILITADO
    );
}