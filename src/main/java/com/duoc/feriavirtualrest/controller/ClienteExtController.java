package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clienteext")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE_EXTERNO')")
public class ClienteExtController {


    @Autowired
    private SolicitudCompraService solicitudCompraService;



    @RequestMapping("/home")
    public String home (){
        return ViewConstant.V_CE_HOME;
    }

    @RequestMapping("/ingresar_pedido")
    public String ingresar_pedido (Model model){

        Solicitud_compra solicitud = new Solicitud_compra();

        model.addAttribute("objeto_de_solicitud_para_llenar", solicitud );




        return "/clienteext/ingresarpedido";
    }


    @RequestMapping("/crear-solicitud-compra")
    public String crear_solicitud_compra(
            @ModelAttribute(name = "objeto_de_solicitud_para_llenar") Solicitud_compra scompra){

        // recibir la data
        /*
            Se pone en el parametro del  método
            @ModelAttribute(name = "objeto_de_solicitud_para_llenar") Solicitud_compra scompra

         */

        // validarla la data (SÓLO PARA CORRROBARAR QUE EXISTE DATA)

        System.out.println(scompra.getProducto());
        System.out.println(scompra.getKilogramos());


        // Sólo por un tema de  prueba
        scompra.setCliente_id(1);


        // enviar esta data a la base de datos

        solicitudCompraService.SP_SOLICITUD_COMPRA_CREAR(scompra);
        System.out.println("Creacion existosa");


        // tener una respuesta


        // enviar al usuario a alguna vista (HTML)

        return "/clienteext/home";
    }




}
