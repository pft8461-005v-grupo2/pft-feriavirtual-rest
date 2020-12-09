package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.IngresoCompleto;
import com.duoc.feriavirtualrest.model.ProcesoVentaCompleto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/transportista")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRANSPORTISTA')")
public class TransportistaController {

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_T_HOME;
    }

    @RequestMapping("/subastas")
    public String subastas (Model model) {
        return ViewConstant.V_T_SUBASTAS;
    }

    @RequestMapping("/procesos")
    public String procesos (Model model) {
        return ViewConstant.V_T_PROCESOS;
    }
}
