package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.constant.UtilConstant;
import com.duoc.feriavirtualrest.constant.ViewConstant;
import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ProcesoVentaCompleto;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.ProductorService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.util.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clienteint")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE_INTERNO')")
public class ClienteIntController {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductorService productorService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_CI_HOME;
    }

    @RequestMapping("/procesos-disponibles")
    public String proceso_disponibles (Model model) throws ClassNotFoundException {


        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setEtapa(UtilConstant.ETAPA_PROCESO_NACIONAL_INICIADO);
        List<ProcesoVenta> listaProcesoVentaDisponibles = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar);

        List<ProcesoVentaCompleto> listaProcesoVentaCompleto = new ArrayList<>();

        for(ProcesoVenta pv : listaProcesoVentaDisponibles){
            ProcesoVentaCompleto pvc = new ProcesoVentaCompleto();

            pvc.setProcesoVenta(pv);

            // Buscar el procesoVentaIngreso asociado (para procesos nacionales, siempre será 1)
            ProcesoVentaIngreso procesoVentaIngresoABuscar = new ProcesoVentaIngreso();
            procesoVentaIngresoABuscar.setProceso_venta_id(pv.getId());
            ProcesoVentaIngreso procesoVentaIngresoEncontrado = procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngresoABuscar).stream().findFirst().orElse(null);

            if(procesoVentaIngresoEncontrado == null){ continue; }
            pvc.setProcesoVentaIngreso(procesoVentaIngresoEncontrado);

            // Buscamos el ingreso asociado
            Ingreso ingresoABuscar = new Ingreso();
            ingresoABuscar.setId(procesoVentaIngresoEncontrado.getIngreso_id());
            Ingreso ingresoEncontrado = ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null);

            if(ingresoEncontrado == null){ continue; }
            pvc.setIngreso(ingresoEncontrado);

            // Buscamos el producto
            Producto productoABuscar = new Producto();
            productoABuscar.setId(ingresoEncontrado.getProducto_id());
            Producto productoEncontrado = productoService.SP_PRODUCTO_CONSULTAR(productoABuscar).stream().findFirst().orElse(null);

            if(productoEncontrado == null){ continue; }
            pvc.setProducto(productoEncontrado);

            // Buscarmos el productor
            Productor productorABuscar = new Productor();
            productorABuscar.setId(ingresoEncontrado.getProductor_id());
            Productor productorEncontrado = productorService.SP_PRODUCTOR_CONSULTAR(productorABuscar).stream().findFirst().orElse(null);

            if(productoEncontrado == null){ continue; }
            pvc.setProductor(productorEncontrado);

            listaProcesoVentaCompleto.add(pvc);
        }


        model.addAttribute("listaProcesoVentaCompleto", listaProcesoVentaCompleto);

        return ViewConstant.V_CI_PROCESO_DISPONIBLES;
    }

    @RequestMapping(value = "/solicitar-oc", method = RequestMethod.GET)
    public @ResponseBody
    Integer solicitar_oc(@RequestParam int proceso_venta_id) throws ClassNotFoundException, IOException {


        Usuario usuario = usuarioService.obtenerUsuario();

        Cliente clienteABuscar = new Cliente();
        clienteABuscar.setUsuario_id(usuario.getId());
        Cliente clienteEncontrado = clienteService.SP_CLIENTE_CONSULTAR(clienteABuscar).stream().findFirst().orElse(null);

        if(clienteEncontrado == null){ return -1; }

        // Se busca el proceso de venta asociado
        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(proceso_venta_id);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);

        if(procesoVentaEncontrado == null) { return -2; }

        // Se busca el proceso venta ingreso
        ProcesoVentaIngreso procesoVentaIngresoABuscar = new ProcesoVentaIngreso();
        procesoVentaIngresoABuscar.setProceso_venta_id(procesoVentaEncontrado.getId());
        ProcesoVentaIngreso procesoVentaIngresoEncontrado = procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngresoABuscar).stream().findFirst().orElse(null);

        if(procesoVentaIngresoEncontrado == null){ return -1; }

        // Buscamos el ingreso asociado
        Ingreso ingresoABuscar = new Ingreso();
        ingresoABuscar.setId(procesoVentaIngresoEncontrado.getIngreso_id());
        Ingreso ingresoEncontrado = ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null);

        if(ingresoEncontrado == null){ return -1; }

        // Buscamos el producto
        Producto productoABuscar = new Producto();
        productoABuscar.setId(ingresoEncontrado.getProducto_id());
        Producto productoEncontrado = productoService.SP_PRODUCTO_CONSULTAR(productoABuscar).stream().findFirst().orElse(null);

        if(productoEncontrado == null){ return -1; }

        // Se genera la solicitud
        Solicitud_compra solicitud_compraACrear = new Solicitud_compra();
        solicitud_compraACrear.setCliente_id(clienteEncontrado.getId());
        solicitud_compraACrear.setProducto(productoEncontrado.getDescripcion());
        solicitud_compraACrear.setKilogramos(ingresoEncontrado.getKilogramos());
        ResponseSP response = Utiles.objectToResponseSP(solicitudCompraService.SP_SOLICITUD_COMPRA_CREAR(solicitud_compraACrear));
        if(response == null){ return -1; }
        if(response.getOUT_ID_SALIDA() <= 0){ return -1; }
        int solicitudCompraId = response.getOUT_ID_SALIDA();

        // Se actualiza el proceso de venta
        ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
        procesoVentaAActualizar.setId(proceso_venta_id);
        procesoVentaAActualizar.setEtapa(UtilConstant.ETAPA_PROCESO_NACIONAL_SOLCITUD_COMPRA);
        procesoVentaAActualizar.setSolicitud_compra_id(solicitudCompraId);
        ResponseSP responseProcesoVentaActualizar = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar));
        if(responseProcesoVentaActualizar == null){ return -1; }
        if(responseProcesoVentaActualizar.getOUT_ID_SALIDA() <= 0){ return -1; }
        return 1;
    }
}
