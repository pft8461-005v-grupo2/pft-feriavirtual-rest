package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clienteext")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE_EXTERNO')")
public class ClienteExtController {

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_CE_HOME;
    }
}
