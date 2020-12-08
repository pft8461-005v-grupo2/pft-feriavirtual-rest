package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.UtilConstant;
import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.IngresoCompleto;
import com.duoc.feriavirtualrest.model.ProcesoVentaCompleto;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductorService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clienteext")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE_EXTERNO')")
public class ClienteExtController {


    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProductorService productorService;


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
        Cliente clienteABuscar = new Cliente();
        clienteABuscar.setUsuario_id(usuario.getId());
        Cliente clienteEncontrado = clienteService.SP_CLIENTE_CONSULTAR(clienteABuscar).stream().findFirst().orElse(null);

        scompra.setCliente_id(clienteEncontrado.getId());
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


    @RequestMapping("/mis-pedidos")
    public String mispedidos (Model model) throws ClassNotFoundException {


        Usuario usuario = usuarioService.obtenerUsuario();

        Cliente clienteABuscar = new Cliente();
        clienteABuscar.setUsuario_id(usuario.getId());
        Cliente clienteEncontrado = clienteService.SP_CLIENTE_CONSULTAR(clienteABuscar)
                .stream().findFirst().orElse(null);

        Solicitud_compra solicitud_compraABuscar = new Solicitud_compra();
        solicitud_compraABuscar.setCliente_id(clienteEncontrado.getId());
        solicitud_compraABuscar.setHabilitado(1);
        List<Solicitud_compra> listaSolicitudCompraVigente = solicitudCompraService.SP_SOLICITUD_COMPRA_CONSULTAR(solicitud_compraABuscar);



        List<ProcesoVenta> procesoVentaPendientes = new ArrayList<>();
        List<ProcesoVentaCompleto> listaProcesoVentaCompleto = new ArrayList<>();
        List<Solicitud_compra> listaSolicitudNoProcesadas = new ArrayList<>();

        for (Solicitud_compra solicitudAux : listaSolicitudCompraVigente) {
            ProcesoVentaCompleto procesoVentaCompleto = new ProcesoVentaCompleto();

            ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
            procesoVentaABuscar.setSolicitud_compra_id(solicitudAux.getId());

            procesoVentaCompleto.setCliente(clienteEncontrado);
            procesoVentaCompleto.setSolicitud_compra(solicitudAux);
            procesoVentaCompleto.setProcesoVenta(procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null));

            if(procesoVentaCompleto.getProcesoVenta() != null){
                listaProcesoVentaCompleto.add(procesoVentaCompleto);
            }else{
                listaSolicitudNoProcesadas.add(solicitudAux);
            }
        }

        List<ProcesoVentaCompleto> listaProcesoVentaCompletoEnAcuerdo = new ArrayList<>();

        listaProcesoVentaCompleto.forEach(pvc -> {
            if(pvc.getProcesoVenta().getEtapa() == UtilConstant.ETAPA_PROCESO_ACUERDO_PENDIENTE_RESPUESTA){
                listaProcesoVentaCompletoEnAcuerdo.add(pvc);
            }
        });

        model.addAttribute("listaProcesoVentaCompleto", listaProcesoVentaCompleto );
        model.addAttribute("listaProcesoVentaCompletoEnAcuerdo", listaProcesoVentaCompletoEnAcuerdo );
        model.addAttribute("listaSolicitudNoProcesadas", listaSolicitudNoProcesadas );


        return ViewConstant.V_CE_MISPEDIDOS;
    }

    @RequestMapping(value = "/mis-pedidos/detalle", method = RequestMethod.GET)
    public @ResponseBody
    List<IngresoCompleto> consultarDetalleIngreso(@RequestParam int proceso_venta_id) throws ClassNotFoundException {

        ProcesoVentaIngreso procesoVentaIngresoABuscar = new ProcesoVentaIngreso();
        procesoVentaIngresoABuscar.setProceso_venta_id(proceso_venta_id);

        List<ProcesoVentaIngreso> listaProcesoVentaIngreso =
                procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngresoABuscar);
        List<IngresoCompleto> listaIngreso = new ArrayList<>();

        for (ProcesoVentaIngreso pvi : listaProcesoVentaIngreso) {
            Ingreso ingresoABuscar = new Ingreso();
            ingresoABuscar.setId(pvi.getIngreso_id());

            IngresoCompleto ingresoCompleto = new IngresoCompleto();
            ingresoCompleto.setIngreso(ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null));
            Productor productorABuscar = new Productor();
            productorABuscar.setId(ingresoCompleto.getIngreso().getProductor_id());
            ingresoCompleto.setProductor(productorService.SP_PRODUCTOR_CONSULTAR(productorABuscar).stream().findFirst().orElse(null));
            listaIngreso.add(ingresoCompleto);
        }

        return listaIngreso;

    }


    @RequestMapping(value = "/mis-pedidos/respuestaAcuerdo", method = RequestMethod.GET)
    public @ResponseBody
    Integer respuestaAcuerdo(@RequestParam int proceso_venta_id,
                                          @RequestParam boolean tipo_respuesta) throws ClassNotFoundException, IOException {


        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(proceso_venta_id);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);
        if(procesoVentaEncontrado == null ){ return -1;}

        if(tipo_respuesta){
            procesoVentaEncontrado.setEtapa(UtilConstant.ETAPA_PROCESO_ACUERDO_ACEPTADO);
        }else{
            procesoVentaEncontrado.setEtapa(UtilConstant.ETAPA_PROCESO_ACUERDO_RECHAZADO);
        }

        ResponseSP response = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaEncontrado));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                return 1;
            } else{ return -1; } }
        else{ return -1; }
    }
}
