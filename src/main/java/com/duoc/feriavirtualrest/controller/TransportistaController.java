package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transportista")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRANSPORTISTA')")
public class TransportistaController {

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_T_HOME;
    }
}
