package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> SP_CLIENTE_CONSULTAR(Cliente cliente) throws ClassNotFoundException;
}
