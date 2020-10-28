package com.duoc.feriavirtualrest.controller;


import com.duoc.feriavirtualrest.constant.UtilConstant;
import com.duoc.feriavirtualrest.constant.ViewConstant;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            HttpServletRequest request) {


        return "/login";
    }

    @GetMapping("/loginsuccess")
    public String loginCheck(Model model, HttpServletRequest request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = "";
        String pathRedirection = "";
        for (GrantedAuthority ga : user.getAuthorities()) {
            role = ga.getAuthority();
        }

        // Selector de ROL, para designar a que página de inicio irá
        switch (role) {
            case UtilConstant.ROLE_ADMIN:
                pathRedirection = "redirect:" + ViewConstant.V_A_HOME;
                break;
            case UtilConstant.ROLE_CLIENTE_EXT:
                pathRedirection = "redirect:" + ViewConstant.V_CE_HOME;
                break;
            case UtilConstant.ROLE_CLIENTE_INT:
                pathRedirection = "redirect:" + ViewConstant.V_CI_HOME;
                break;
            case UtilConstant.ROLE_PRODUCTOR:
                pathRedirection = "redirect:" + ViewConstant.V_P_HOME;
                break;
            case UtilConstant.ROLE_TRANSPORTISTA:
                pathRedirection = "redirect:" + ViewConstant.V_T_HOME;
                break;
            default:
                pathRedirection = "redirect:" + ViewConstant.V_LOGIN;
                break;
        }








        /*
        // Buscamos el usuario para obtener id desde bd
        UsuarioModel usuarioModel_encontrado = usuarioService.findByUsuarioAndDisponible(user.getUsername(), true);
        String rutaUrl = "";
        if(pathRedirection != null) rutaUrl = pathRedirection.substring(pathRedirection.lastIndexOf(":") + 1);
        if (usuarioModel_encontrado != null) {

            // Obtenemos la session
            HttpSession mi_sesion = request.getSession(true);
            mi_sesion.setAttribute("usuarioSession", usuarioModel_encontrado);
            // guardamos LogSesion
            guardarLogSesion(usuarioModel_encontrado, rutaUrl, ConfigurationConstant.LOGSESION_LOGIN);
        }


         */

        return pathRedirection;
    }

}
