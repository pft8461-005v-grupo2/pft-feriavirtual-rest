package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("solicitudCompraRepository")
public interface SolicitudCompraRepository extends CrudRepository<Solicitud_compra, Long> {

    @Procedure(name = SPConstant.SP_SOLICITUD_COMPRA_CREAR)
    Object SP_SOLICITUD_COMPRA_CREAR(
            @Param("IN_CLIENTE_ID") int IN_CLIENTE_ID,
            @Param("IN_PRODUCTO") String IN_PRODUCTO,
            @Param("IN_KILOGRAMOS") int IN_KILOGRAMOS
    );

    @Procedure(name = SPConstant.SP_SOLICITUD_COMPRA_ACTUALIZAR)
    Object SP_SOLICITUD_COMPRA_ACTUALIZAR(
            @Param("IN_ID_SOLICITUD_COMPRA") int IN_ID_SOLICITUD_COMPRA,
            @Param("IN_CLIENTE_ID") int IN_CLIENTE_ID,
            @Param("IN_PRODUCTO") String IN_PRODUCTO,
            @Param("IN_KILOGRAMOS") int IN_KILOGRAMOS,
            @Param("IN_HABILITADO") int IN_HABILITADO

    );
}