package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.StockDisponible;
import com.duoc.feriavirtualrest.repository.StockDisponibleRepository;
import com.duoc.feriavirtualrest.service.StockDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockDisponibleService")
public class StockDisponibleServiceImp implements StockDisponibleService {

    @Autowired
    private StockDisponibleRepository stockDisponibleRepository;

    @Override
    public List<StockDisponible> consultarStockDisponible() {
        return stockDisponibleRepository.findAll();
    }
}
