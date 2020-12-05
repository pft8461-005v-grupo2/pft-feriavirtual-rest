package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Producto;

import java.util.List;

public interface ProductoService {
    Object  SP_PRODUCTO_CREAR(Producto producto);
    Object  SP_PRODUCTO_ACTUALIZAR(Producto producto);
    List<Producto> SP_PRODUCTO_CONSULTAR(Producto producto) throws ClassNotFoundException;

}
