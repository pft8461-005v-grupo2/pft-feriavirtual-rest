package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Contrato;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository("contratoRepository")
public interface ContratoRepository extends CrudRepository<Contrato, Long> {

    @Procedure(name = SPConstant.SP_CONTRATO_CREAR)
    Object SP_CONTRATO_CREAR(
            @Param("IN_FECHAINICIO") Date IN_FECHAINICIO,
            @Param("IN_FECHATERMINO") Date IN_FECHATERMINO,
            @Param("IN_FECHACREACION") Date IN_FECHACREACION
    );
}
