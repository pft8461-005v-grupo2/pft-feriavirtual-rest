package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Productor;
import java.util.List;

public interface ProductorService {

    List<Productor> SP_PRODUCTOR_CONSULTAR(Productor productor) throws ClassNotFoundException;
}
