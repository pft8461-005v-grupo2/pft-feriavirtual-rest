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
import com.duoc.feriavirtualrest.model.IngresoCompleto;
import com.duoc.feriavirtualrest.model.ProcesoVentaCompleto;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.DetalleSubastaService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductorService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.TransportistaService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import com.duoc.feriavirtualrest.util.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/transportista")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRANSPORTISTA')")
public class TransportistaController {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProductorService productorService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransportistaService transportistaService;

    @Autowired
    private DetalleSubastaService detalleSubastaService;

    @GetMapping("/home")
    public String home (){
        return ViewConstant.V_T_HOME;
    }

    @RequestMapping("/subastas")
    public String subastas (Model model) throws ClassNotFoundException {

        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setEtapa(5);
        List<ProcesoVenta> listaProcesoVentaDisponibles = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar);

        List<ProcesoVentaCompleto> listaProcesoVentaCompleto = new ArrayList<>();;

        for(ProcesoVenta pvd : listaProcesoVentaDisponibles){
            ProcesoVentaCompleto pvc = new ProcesoVentaCompleto();
            pvc.setProcesoVenta(pvd);

            // Buscar Solicitud de compra asociada
            Solicitud_compra solicitud_compraABuscar = new Solicitud_compra();
            solicitud_compraABuscar.setId(pvd.getSolicitud_compra_id());
            Solicitud_compra solicitud_compraEncontrada = solicitudCompraService.SP_SOLICITUD_COMPRA_CONSULTAR(solicitud_compraABuscar).stream().findFirst().orElse(null);

            pvc.setSolicitud_compra(solicitud_compraEncontrada);

            listaProcesoVentaCompleto.add(pvc);
        }

        // Quitar en las que ya estoy participando
        Usuario usuario = usuarioService.obtenerUsuario();

        if(usuario.getRol_id() != UtilConstant.ROLE_ID_TRANSPORTISTA){
            model.addAttribute("usuario_no_permitido", true);
        }

        Transportista transportistaABuscar = new Transportista();
        transportistaABuscar.setUsuario_id(usuario.getId());
        Transportista transportistaEncontrado = transportistaService.SP_TRANSPORTISTA_CONSULTAR(transportistaABuscar).stream().findFirst().orElse(null);

        if(transportistaEncontrado != null){
            Detalle_subasta detalle_subastaABuscar = new Detalle_subasta();
            detalle_subastaABuscar.setTransportista_id(transportistaEncontrado.getId());
            List<Detalle_subasta> listaDetalleSubasta = detalleSubastaService.SP_DETALLE_SUBASTA_CONSULTAR(detalle_subastaABuscar);

            for(ProcesoVentaCompleto pvc : listaProcesoVentaCompleto){
                for(Detalle_subasta ds : listaDetalleSubasta){
                    if(pvc.getProcesoVenta().getSubasta_id() == ds.getSubasta_id()){
                        listaProcesoVentaCompleto.remove(pvc);
                    }
                }
            }
            model.addAttribute("usuario_no_permitido", false);
            model.addAttribute("listaProcesoVentaCompleto", listaProcesoVentaCompleto);
        }else{
            model.addAttribute("usuario_no_permitido", true);
        }

        return ViewConstant.V_T_SUBASTAS;
    }

    @RequestMapping(value = "/subastas/detalle", method = RequestMethod.GET)
    public @ResponseBody
    List<IngresoCompleto> consultarDetalleIngreso(@RequestParam int proceso_venta_id) throws ClassNotFoundException {

        ProcesoVentaIngreso procesoVentaIngresoABuscar = new ProcesoVentaIngreso();
        procesoVentaIngresoABuscar.setProceso_venta_id(proceso_venta_id);

        List<ProcesoVentaIngreso> listaProcesoVentaIngreso =
                procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngresoABuscar);
        List<IngresoCompleto> listaIngreso = new ArrayList<>();

        for (ProcesoVentaIngreso pvi : listaProcesoVentaIngreso) {

            IngresoCompleto ingresoCompleto = new IngresoCompleto();
            Ingreso ingresoABuscar = new Ingreso();
            ingresoABuscar.setId(pvi.getIngreso_id());

            ingresoCompleto.setIngreso(ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null));
            Productor productorABuscar = new Productor();
            productorABuscar.setId(ingresoCompleto.getIngreso().getProductor_id());
            ingresoCompleto.setProductor(productorService.SP_PRODUCTOR_CONSULTAR(productorABuscar).stream().findFirst().orElse(null));
            ingresoCompleto.setKilogramosocupados(pvi.getKilogramosocupados());
            listaIngreso.add(ingresoCompleto);
        }

        return listaIngreso;

    }

    @RequestMapping(value = "/subasta/ofertar", method = RequestMethod.GET)
    public @ResponseBody
    Integer ofertar(@RequestParam int proceso_venta_id,
                    @RequestParam int valoroferta) throws ClassNotFoundException, IOException {


        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(proceso_venta_id);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);
        if(procesoVentaEncontrado == null ){ return -1;}

        if(procesoVentaEncontrado.getSubasta_id() == null) { return -1; }

        Usuario usuario = usuarioService.obtenerUsuario();

        if(usuario.getRol_id() != UtilConstant.ROLE_ID_TRANSPORTISTA){
            return -2;
        }

        Transportista transportistaABuscar = new Transportista();
        transportistaABuscar.setUsuario_id(usuario.getId());
        Transportista transportistaEncontrado = transportistaService.SP_TRANSPORTISTA_CONSULTAR(transportistaABuscar).stream().findFirst().orElse(null);

        if(transportistaEncontrado == null){ return -1; }

        Detalle_subasta detalle_subastaACrear = new Detalle_subasta();
        detalle_subastaACrear.setTransportista_id(transportistaEncontrado.getId());
        detalle_subastaACrear.setValorpropuesta(valoroferta);
        detalle_subastaACrear.setSubasta_id(procesoVentaEncontrado.getSubasta_id());

        ResponseSP response = Utiles.objectToResponseSP(detalleSubastaService.SP_DETALLE_SUBASTA_CREAR(detalle_subastaACrear));
        if(response != null){
            if(response.getOUT_ID_SALIDA() == 0){
                return 1;
            } else{ return -1; } }
        else{ return -1; }
    }

    @RequestMapping("/procesos")
    public String procesos (Model model) {
        return ViewConstant.V_T_PROCESOS;
    }
}
