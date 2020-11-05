package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Transportista;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("transportistaRepository")
public interface TransportistaRepository extends CrudRepository<Transportista, Long> {

    @Procedure(name = SPConstant.SP_TRANSPORTISTA_CREAR)
    Object SP_TRANSPORTISTA_CREAR(
            @Param("IN_USUARIO_ID") int IN_USUARIO_ID,
            @Param("IN_RUT") String IN_RUT,
            @Param("IN_RAZON_SOCIAL") String IN_RAZON_SOCIAL,
            @Param("IN_DIRECCION") String IN_DIRECCION,
            @Param("IN_COMUNA") String IN_COMUNA,
            @Param("IN_CORREO") String IN_CORREO
    );


}
