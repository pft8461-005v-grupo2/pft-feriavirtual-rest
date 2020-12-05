package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.Parametros;
import com.duoc.feriavirtualrest.repository.ParametrosRepository;
import com.duoc.feriavirtualrest.service.ParametrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("parametrosService")
public class ParametrosServiceImp implements ParametrosService {

    @Autowired
    private ParametrosRepository parametrosRepository;

    @Override
    public Parametros obtenerParametroPorId(Integer id) {
        return parametrosRepository.findById(id);
    }
}
