package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Producto;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("productoRepository")
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    @Procedure(name = SPConstant.SP_PRODUCTO_CREAR)
    Object SP_PRODUCTO_CREAR(
            @Param("IN_DESCRIPCION") String IN_DESCRIPCION
    );

    @Procedure(name = SPConstant.SP_PRODUCTO_ACTUALIZAR)
    Object SP_PRODUCTO_ACTUALIZAR(
            @Param("IN_PRODUCTO_ID") Integer IN_PRODUCTO_ID,
            @Param("IN_DESCRIPCION") String IN_DESCRIPCION,
            @Param("IN_HABILITADO") Integer IN_HABILITADO
    );
}
