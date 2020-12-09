package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Subasta;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.DetalleSubastaService;
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

@RestController
@RequestMapping("/api")
public class ActualizarController {

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
    private ProductoService productoService;

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Autowired
    private SubastaService subastaService;

    @Autowired
    private DetalleSubastaService detalleSubastaService;

    @RequestMapping(    value = "/cliente/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> usuario_actualizar(@RequestBody Cliente cliente){
        return new ResponseEntity<Object>(clienteService.SP_CLIENTE_ACTUALIZAR(cliente), HttpStatus.OK);
    }

    @RequestMapping(    value = "/productor/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> productor_actualizar(@RequestBody Productor productor){
        return new ResponseEntity<Object>(productorService.SP_PRODUCTOR_ACTUALIZAR(productor), HttpStatus.OK);
    }

    @RequestMapping(    value = "/transportista/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transportista_actualizar(@RequestBody Transportista transportista){
        return new ResponseEntity<Object>(transportistaService.SP_TRANSPORTISTA_ACTUALIZAR(transportista), HttpStatus.OK);
    }

    @RequestMapping(    value = "/solicitud-compra/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> solicitud_compra_actualizar(@RequestBody Solicitud_compra solicitud_compra){
        return new ResponseEntity<Object>(solicitudCompraService.SP_SOLICITUD_COMPRA_ACTUALIZAR(solicitud_compra), HttpStatus.OK);
    }

    @RequestMapping(    value = "/proceso-venta/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_actualizar(@RequestBody ProcesoVenta procesoVenta){
        return new ResponseEntity<Object>(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVenta), HttpStatus.OK);
    }

    @RequestMapping(    value = "/producto/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> producto_actualizar(@RequestBody Producto producto){
        return new ResponseEntity<Object>(productoService.SP_PRODUCTO_ACTUALIZAR(producto), HttpStatus.OK);
    }

    @RequestMapping(    value = "/ingreso/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ingreso_actualizar(@RequestBody Ingreso ingreso){
        return new ResponseEntity<Object>(ingresoService.SP_INGRESO_ACTUALIZAR(ingreso), HttpStatus.OK);
    }

    @RequestMapping(    value = "/proceso-venta-ingreso/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_ingreso_actualizar(@RequestBody ProcesoVentaIngreso procesoVentaIngreso){
        return new ResponseEntity<Object>(procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_ACTUALIZAR(procesoVentaIngreso), HttpStatus.OK);
    }

    @RequestMapping(    value = "/subasta/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> subasta_actualizar(@RequestBody Subasta subasta){
        return new ResponseEntity<Object>(subastaService.SP_SUBASTA_ACTUALIZAR(subasta), HttpStatus.OK);
    }

    @RequestMapping(    value = "/detalle-subasta/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> detalle_subasta_actualizar(@RequestBody Detalle_subasta detalle_subasta){
        return new ResponseEntity<Object>(detalleSubastaService.SP_DET_SUBASTA_ACTUALIZAR(detalle_subasta), HttpStatus.OK);
    }

}
