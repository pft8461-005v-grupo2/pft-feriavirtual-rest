package com.duoc.feriavirtualrest.repository;

import com.duoc.feriavirtualrest.entity.StockDisponible;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockDisponibleRepository")
public interface StockDisponibleRepository extends CrudRepository<StockDisponible, Long> {

    List<StockDisponible> findAll();

}
