package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Productor;
import java.util.List;

public interface ProductorService {
    Object  SP_PRODUCTOR_CREAR(Productor productor);
    Object  SP_PRODUCTOR_ACTUALIZAR(Productor productor);
    List<Productor> SP_PRODUCTOR_CONSULTAR(Productor productor) throws ClassNotFoundException;
}
