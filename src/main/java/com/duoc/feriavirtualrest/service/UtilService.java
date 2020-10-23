package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.util.SPDataIN;

import java.util.List;

public interface UtilService {

    // Ejecuta y devuelve una lista
    List<?> ejecutarSP(String SP_NAME, Class<?> SP_CLASS_REF, List<SPDataIN> SP_IN_DATA) throws ClassNotFoundException;

}
