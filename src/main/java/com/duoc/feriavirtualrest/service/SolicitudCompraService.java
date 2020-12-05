package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Solicitud_compra;

import java.util.List;

public interface SolicitudCompraService {
    Object SP_SOLICITUD_COMPRA_CREAR(Solicitud_compra solicitud_compra);
    Object  SP_SOLICITUD_COMPRA_ACTUALIZAR(Solicitud_compra solicitud_compra);
    List<Solicitud_compra> SP_SOLICITUD_COMPRA_CONSULTAR(Solicitud_compra solicitud_compra) throws ClassNotFoundException;
}
