package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.ProductorService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.util.Utiles;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/productor")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCTOR')")
public class ProductorController {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductorService productorService;

    @Autowired
    private IngresoService ingresoService;

        @GetMapping("/home")
    public String home () { return ViewConstant.V_P_HOME; }

    @GetMapping("/ingresarproducto")
    public String ingresarProducto (Model model,
                                    @RequestParam(name = "resultado", required = false, defaultValue = "0") int resultado,
                                    @RequestParam(name = "idNuevoIngreso", required = false, defaultValue = "0") int idNuevoIngreso){

        if(resultado == 1){
            model.addAttribute("idNuevoIngreso", idNuevoIngreso);
        }

        model.addAttribute("resultado", resultado);
        model.addAttribute("nuevoIngreso", new Ingreso());
        return ViewConstant.V_P_INGRESO;
    }

    @RequestMapping(value = "/buscar_fruta_existente", method = RequestMethod.GET)
    public void buscar_fruta_existente(@RequestParam String fruta, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            String g = new Gson().toJson(buscar_fruta(fruta));
            response.getWriter().write(g);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Producto> buscar_fruta(String fruta) throws ClassNotFoundException {
        List<Producto> result = new ArrayList<Producto>();
        Producto productoABuscar = new Producto();
        productoABuscar.setHabilitado(1);
        for (Producto productoItem : productoService.SP_PRODUCTO_CONSULTAR(productoABuscar)) {
            if (productoItem.getDescripcion().toUpperCase().contains(fruta.toUpperCase())) {
                result.add(productoItem);
            }
        }
        return result;
    }

    @RequestMapping("/ingresar-producto")
    public String ofrecer_fruta(RedirectAttributes ra,
                                @ModelAttribute(name = "nuevoIngreso") @Valid Ingreso ingreso,
                                BindingResult bindingResult,
                                Model model) throws ClassNotFoundException, IOException {

        if (bindingResult.hasErrors()) {
            return ViewConstant.V_P_INGRESO;
        }

        if(ingreso.getProducto_id() == -1){
            model.addAttribute("resultado", -2);
            return ViewConstant.V_P_INGRESO;
        }


        Usuario usuario = usuarioService.obtenerUsuario();
        Productor productorABuscar = new Productor();
        productorABuscar.setUsuario_id(usuario.getId());
        Productor productorEncontrado = productorService.SP_PRODUCTOR_CONSULTAR(productorABuscar).stream().findFirst().orElse(null);

        ingreso.setProductor_id(productorEncontrado.getId());
        ResponseSP response = Utiles.objectToResponseSP(ingresoService.SP_INGRESO_CREAR(ingreso));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                ra.addAttribute("resultado", 1);
                ra.addAttribute("idNuevoIngreso", response.getOUT_ID_SALIDA());
            } else{
                ra.addAttribute("resultado", -1);
            }

        } else{
            ra.addAttribute("resultado", -1);
        }
        return "redirect:/productor/ingresarproducto";
    }

}
