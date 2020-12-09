package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("detalleSubastaRepository")
public interface DetalleSubastaRepository extends CrudRepository<Detalle_subasta, Long> {

    @Procedure(name = SPConstant.SP_DETALLE_SUBASTA_CREAR)
    Object SP_DETALLE_SUBASTA_CREAR(
            @Param("IN_TRANSPORTISTA_ID") Integer IN_TRANSPORTISTA_ID,
            @Param("IN_SUBASTA_ID") Integer IN_SUBASTA_ID,
            @Param("IN_VALOR_PROPUESTA") Integer IN_VALOR_PROPUESTA
    );

    @Procedure(name = SPConstant.SP_DET_SUBASTA_ACTUALIZAR)
    Object SP_DET_SUBASTA_ACTUALIZAR(
            @Param("IN_TRANSPORTISTA_ID") Integer IN_TRANSPORTISTA_ID,
            @Param("IN_SUBASTA_ID") Integer IN_SUBASTA_ID,
            @Param("IN_VALOR_PROPUESTA") Integer IN_VALOR_PROPUESTA
    );

}
