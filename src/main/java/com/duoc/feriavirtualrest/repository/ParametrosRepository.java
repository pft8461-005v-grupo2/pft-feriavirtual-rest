package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.entity.Parametros;
import org.springframework.data.repository.CrudRepository;

public interface ParametrosRepository extends CrudRepository<Parametros, Long> {

    Parametros findById(Integer id);
}
