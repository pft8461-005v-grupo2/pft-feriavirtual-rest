package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("procesoVentaIngresoRepository")
public interface ProcesoVentaIngresoRepository extends CrudRepository<ProcesoVentaIngreso, Long> {

    @Procedure(name = SPConstant.SP_PROCESO_VENTA_INGRESO_CREAR)
    Object SP_PROCESO_VENTA_INGRESO_CREAR(
            @Param("IN_INGRESO_ID") Integer IN_INGRESO_ID,
            @Param("IN_PROCESO_VENTA_ID") Integer IN_PROCESO_VENTA_ID,
            @Param("IN_KILOGRAMOSOCUPADOS") Integer IN_KILOGRAMOSOCUPADOS
    );

    @Procedure(name = SPConstant.SP_PROCESO_VENTA_INGRESO_ACTUALIZAR)
    Object SP_PROCESO_VENTA_INGRESO_ACTUALIZAR(
            @Param("IN_INGRESO_ID") Integer IN_INGRESO_ID,
            @Param("IN_PROCESO_VENTA_ID") Integer IN_PROCESO_VENTA_ID,
            @Param("IN_KILOGRAMOSOCUPADOS") Integer IN_KILOGRAMOSOCUPADOS,
            @Param("IN_HABILITADO") Integer IN_HABILITADO
    );
}
