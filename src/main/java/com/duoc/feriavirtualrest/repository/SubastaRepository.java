package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Subasta;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("subastaRepository")
public interface SubastaRepository extends CrudRepository<Subasta, Long> {

    @Procedure(name = SPConstant.SP_SUBASTA_CREAR)
    Object SP_SUBASTA_CREAR(
            @Param("IN_FECHATERMINO") Date IN_FECHATERMINO
    );

    @Procedure(name = SPConstant.SP_SUBASTA_ACTUALIZAR)
    Object SP_SUBASTA_ACTUALIZAR(
            @Param("IN_ID_SUBASTA") Integer IN_ID_CLIENTE,
            @Param("IN_FECHATERMINO") Date IN_IDENTIFICADOR,
            @Param("IN_PRECIO_GANADOR") Integer IN_RAZON_SOCIAL,
            @Param("IN_HABILITADO") Integer IN_HABILITADO
    );
}
