package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.repository.ClienteRepository;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ProcedureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clienteService")
public class ClienteServiceImp implements ClienteService {

    Logger log = LoggerFactory.getLogger(ClienteServiceImp.class);

    @Autowired
    @Qualifier("procedureService")
    private ProcedureService procedureService;

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Override
    public Object SP_CLIENTE_CREAR(Cliente cliente) {
        try{
            return clienteRepository.SP_CLIENTE_CREAR(
                    cliente.getUsuario_id(),
                    cliente.getIdentificador(),
                    cliente.getRazonSocial(),
                    cliente.getDireccion(),
                    cliente.getCiudad(),
                    cliente.getPais_origen(),
                    cliente.getTipo_cliente(),
                    cliente.getCorreo()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo cliente", e);
            return new Object();
        }
    }

    @Override
    public List<Cliente> SP_CLIENTE_CONSULTAR(Cliente cliente) throws ClassNotFoundException {
        return (List<Cliente>)(procedureService.ejecutarSP(SPConstant.SP_CLIENTE_CONSULTAR, Cliente.class, cliente.generarDataIN()));

    }
}
