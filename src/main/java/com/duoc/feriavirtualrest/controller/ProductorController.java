package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/productor")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCTOR')")
public class ProductorController {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @GetMapping("/home")
    public ModelAndView home (Model model) throws ClassNotFoundException {
        ModelAndView vista = new ModelAndView(ViewConstant.V_P_HOME);

        // Se pone el dato que queremos buscar
        ProcesoVenta procesoVenta = new ProcesoVenta();
        procesoVenta.setEtapa(1);
        List<ProcesoVenta> respuesta = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVenta);

        // Se agrega a la vista la respuesta
        vista.addObject("lista_procesoventa", respuesta);

        return vista;
    }

    @GetMapping("/ingresarproducto")
    public String ingresarProducto (){
        return ViewConstant.V_P_INGRESO;
    }


}
