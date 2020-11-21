package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.util.Utiles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/clienteext")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE_EXTERNO')")
public class ClienteExtController {


    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private UsuarioService usuarioService;


    @RequestMapping("/home")
    public String home (){
        return ViewConstant.V_CE_HOME;
    }

    @RequestMapping("/ingresar-pedido")
    public String ingresar_pedido (Model model,
                                   @RequestParam(name = "resultado", required = false, defaultValue = "0") int resultado,
                                   @RequestParam(name = "idNuevoPedido", required = false, defaultValue = "0") int idNuevoPedido
                                   ){
        Solicitud_compra solicitud = new Solicitud_compra();
        model.addAttribute("nuevaSolicitud", solicitud );

        if(resultado == 1){
            model.addAttribute("idNuevoPedido", idNuevoPedido);
        }
        model.addAttribute("resultado", resultado);
        return ViewConstant.V_CE_INGRESO;
    }


    @RequestMapping("/crear-solicitud-compra")
    public String crear_solicitud_compra(RedirectAttributes ra,
            @ModelAttribute(name = "nuevaSolicitud") @Valid Solicitud_compra scompra,
                                         BindingResult bindingResult) throws ClassNotFoundException, IOException {
        if (bindingResult.hasErrors()) {
            return ViewConstant.V_CE_INGRESO;
        }

        Usuario usuario = usuarioService.obtenerUsuario();
        scompra.setCliente_id(usuario.getId());
        scompra.setProducto(scompra.getProducto().toUpperCase());
        ResponseSP response = Utiles.objectToResponseSP(solicitudCompraService.SP_SOLICITUD_COMPRA_CREAR(scompra));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                ra.addAttribute("resultado", 1);
                ra.addAttribute("idNuevoPedido", response.getOUT_ID_SALIDA());
            } else{
                ra.addAttribute("resultado", -1);
            }

        } else{
            ra.addAttribute("resultado", -1);
        }
        return "redirect:/clienteext/ingresar-pedido";
    }

}
