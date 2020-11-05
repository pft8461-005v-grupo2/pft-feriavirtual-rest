package com.duoc.feriavirtualrest.service;

import com.duoc.feriavirtualrest.entity.Cliente;
import java.util.List;

public interface ClienteService {
    Object  SP_CLIENTE_CREAR(Cliente cliente);
    Object  SP_CLIENTE_ACTUALIZAR(Cliente cliente);
    List<Cliente> SP_CLIENTE_CONSULTAR(Cliente cliente) throws ClassNotFoundException;
}
