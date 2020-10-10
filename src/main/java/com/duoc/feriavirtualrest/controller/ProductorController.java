package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productor")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCTOR')")
public class ProductorController {

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_P_HOME;
    }

    @GetMapping("/ingresarproducto")
    public String ingresarProducto (){
        return ViewConstant.V_P_INGRESO;
    }

}
