package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("ingresoRepository")
public interface IngresoRepository  extends CrudRepository<Ingreso, Long> {

    @Procedure(name = SPConstant.SP_INGRESO_CREAR)
    Object SP_INGRESO_CREAR(
            @Param("IN_PRODUCTOR_ID") int IN_PRODUCTOR_ID,
            @Param("IN_PRODUCTO_ID") int IN_PRODUCTO_ID,
            @Param("IN_KILOGRAMOS") int IN_KILOGRAMOS,
            @Param("IN_PRECIO_KG_COSTO_UNITARIO") int IN_PRECIO_KG_COSTO_UNITARIO
    );

    @Procedure(name = SPConstant.SP_INGRESO_ACTUALIZAR)
    Object SP_INGRESO_ACTUALIZAR(
            @Param("IN_INGRESO_ID") Integer IN_INGRESO_ID,
            @Param("IN_PRODUCTO_ID") Integer IN_PRODUCTO_ID,
            @Param("IN_KILOGRAMOS") Integer IN_KILOGRAMOS,
            @Param("IN_PRECIO_KG_COSTO_UNITARIO") Integer IN_PRECIO_KG_COSTO_UNITARIO,
            @Param("IN_PRECIO_KG_VENTA_UNITARIO") Integer IN_PRECIO_KG_VENTA_UNITARIO,
            @Param("IN_HABILITADO") Integer IN_HABILITADO
    );

}
