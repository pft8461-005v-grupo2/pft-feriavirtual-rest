package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Productor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("productorRepository")
public interface ProductorRepository extends CrudRepository<Productor, Long> {

    @Procedure(name = SPConstant.SP_PRODUCTOR_CREAR)
    Object SP_PRODUCTOR_CREAR(
            @Param("IN_USUARIO_ID") int IN_USUARIO_ID,
            @Param("IN_CONTRATO_ID") int IN_CONTRATO_ID,
            @Param("IN_RUT") String IN_RUT,
            @Param("IN_RAZONSOCIAL") String IN_RAZONSOCIAL,
            @Param("IN_DIRECCION") String IN_DIRECCION,
            @Param("IN_COMUNA") String IN_COMUNA,
            @Param("IN_CORREO") String IN_CORREO
    );

}
