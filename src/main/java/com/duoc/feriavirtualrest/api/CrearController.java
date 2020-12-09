package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Subasta;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ContratoModel;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.ProductorService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.SubastaService;
import com.duoc.feriavirtualrest.service.TransportistaService;
import com.duoc.feriavirtualrest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class CrearController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductorService productorService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private TransportistaService transportistaService;

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Autowired
    private SubastaService subastaService;

    @RequestMapping(    value = "/usuario/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> usuario_crear(@RequestBody Usuario usuario){
        return new ResponseEntity<Object>(usuarioService.SP_USUARIO_CREAR(usuario), HttpStatus.OK);
    }

    @RequestMapping(    value = "/cliente/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cliente_crear(@RequestBody Cliente cliente){
        return new ResponseEntity<Object>(clienteService.SP_CLIENTE_CREAR(cliente), HttpStatus.OK);
    }

    @RequestMapping(    value = "/productor/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productor_crear(@RequestBody Productor productor){
        return new ResponseEntity<Object>(productorService.SP_PRODUCTOR_CREAR(productor), HttpStatus.OK);
    }

    @RequestMapping(    value = "/transportista/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transportista_crear(@RequestBody Transportista transportista){
        return new ResponseEntity<Object>(transportistaService.SP_TRANSPORTISTA_CREAR(transportista), HttpStatus.OK);
    }


    @RequestMapping(    value = "/contrato/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> contrato_crear(@RequestBody ContratoModel contratoModel) throws ParseException {
        Contrato contrato = new Contrato();
        contrato.setFechainicio(Date.valueOf(contratoModel.getFechacreacion()));
        contrato.setFechatermino(Date.valueOf(contratoModel.getFechatermino()));
        contrato.setFechacreacion(Date.valueOf(contratoModel.getFechacreacion()));
        return new ResponseEntity<Object>(contratoService.SP_CONTRATO_CREAR(contrato), HttpStatus.OK);
    }

    @RequestMapping(    value = "/solicitud-compra/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> solicitud_compra_crear(@RequestBody Solicitud_compra solicitud_compra){
        return new ResponseEntity<Object>(solicitudCompraService.SP_SOLICITUD_COMPRA_CREAR(solicitud_compra), HttpStatus.OK);
    }

    @RequestMapping(   value = "/proceso-venta/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_crear(@RequestBody ProcesoVenta procesoVenta){
        return new ResponseEntity<Object>(procesoVentaService.SP_PROCESO_VENTA_CREAR(procesoVenta), HttpStatus.OK);
    }

    @RequestMapping(   value = "/producto/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> producto_crear(@RequestBody Producto producto){
        return new ResponseEntity<Object>(productoService.SP_PRODUCTO_CREAR(producto), HttpStatus.OK);
    }

    @RequestMapping(   value = "/ingreso/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ingreso_crear(@RequestBody Ingreso ingreso){
        return new ResponseEntity<Object>(ingresoService.SP_INGRESO_CREAR(ingreso), HttpStatus.OK);
    }

    @RequestMapping(   value = "/proceso-venta-ingreso/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_ingreso_crear(@RequestBody ProcesoVentaIngreso procesoVentaIngreso){
        return new ResponseEntity<Object>(procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CREAR(procesoVentaIngreso), HttpStatus.OK);
    }

    @RequestMapping(   value = "/subasta/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> subasta_crear(@RequestBody Subasta subasta){
        return new ResponseEntity<Object>(subastaService.SP_SUBASTA_CREAR(subasta), HttpStatus.OK);
    }
}
