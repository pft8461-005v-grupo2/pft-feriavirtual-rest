package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.model.IngresoCompleto;
import com.duoc.feriavirtualrest.model.ProcesoVentaCompleto;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;



    @GetMapping("/")
    public String index() {
        return ViewConstant.V_A_HOME;
    }

    @RequestMapping(value = "/detalle-general", method = RequestMethod.GET)
    public @ResponseBody
    ProcesoVentaCompleto detalleGeneral(@RequestParam int proceso_venta_id) throws ClassNotFoundException {

        ProcesoVentaCompleto pvc = new ProcesoVentaCompleto();

        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(proceso_venta_id);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);

        if(procesoVentaEncontrado == null){
            return new ProcesoVentaCompleto();
        }

        pvc.setProcesoVenta(procesoVentaEncontrado);

        return pvc;

    }

}
