package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Ingreso;

import java.util.List;

public interface IngresoService {

    Object  SP_INGRESO_CREAR(Ingreso ingreso);
    Object  SP_INGRESO_ACTUALIZAR(Ingreso ingreso);
    List<Ingreso> SP_INGRESO_CONSULTAR(Ingreso ingreso) throws ClassNotFoundException;

}
