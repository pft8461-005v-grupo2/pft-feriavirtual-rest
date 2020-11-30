package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("procesoVentaRepository")
public interface ProcesoVentaRepository extends CrudRepository<ProcesoVenta, Long> {

    @Procedure(name = SPConstant.SP_PROCESO_VENTA_CREAR)
    Object SP_PROCESO_VENTA_CREAR(
            @Param("IN_SOLICITUD_COMPRA_ID") int IN_SOLICITUD_COMPRA_ID
    );

}
