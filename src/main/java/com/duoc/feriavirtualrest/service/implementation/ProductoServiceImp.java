package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.repository.ProductoRepository;
import com.duoc.feriavirtualrest.service.ProcedureService;
import com.duoc.feriavirtualrest.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productoService")
public class ProductoServiceImp implements ProductoService {

    Logger log = LoggerFactory.getLogger(ProductoServiceImp.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProcedureService procedureService;

    @Override
    public Object SP_PRODUCTO_CREAR(Producto producto) {
        try{
            return productoRepository.SP_PRODUCTO_CREAR(
                    producto.getDescripcion()
            );
        }catch (Exception e) {
            log.error("Error al crear un nuevo producto", e);
            return new Object();
        }
    }

    @Override
    public Object SP_PRODUCTO_ACTUALIZAR(Producto producto) {
        try{
            return productoRepository.SP_PRODUCTO_ACTUALIZAR(
                    producto.getId(),
                    producto.getDescripcion(),
                    producto.getHabilitado()
            );
        }catch (Exception e) {
            log.error("Error al actualizar un nuevo producto", e);
            return new Object();
        }
    }

    @Override
    public List<Producto> SP_PRODUCTO_CONSULTAR(Producto producto) throws ClassNotFoundException {
        return (List<Producto>)(procedureService.ejecutarSP(SPConstant.SP_PRODUCTO_CONSULTAR, Producto.class, producto.generarDataIN()));

    }
}
