package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.ProductorService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
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

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsultasController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductorService productorService;

    @Autowired
    private TransportistaService transportistaService;

    @Autowired
    private ContratoService contratoService;

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

    @RequestMapping(    value = "/usuario/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_usuario(@RequestBody Usuario usuario) throws ClassNotFoundException {
        List<Usuario> resultado = usuarioService.SP_USUARIO_CONSULTAR(usuario);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }


    @RequestMapping(    value = "/cliente/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_cliente(@RequestBody Cliente cliente) throws ClassNotFoundException {
        List<Cliente> resultado = clienteService.SP_CLIENTE_CONSULTAR(cliente);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }


    @RequestMapping(    value = "/productor/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_productor(@RequestBody Productor productor) throws ClassNotFoundException {
        List<Productor> resultado = productorService.SP_PRODUCTOR_CONSULTAR(productor);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/transportista/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_transportista(@RequestBody Transportista transportista) throws ClassNotFoundException {
        List<Transportista> resultado = transportistaService.SP_TRANSPORTISTA_CONSULTAR(transportista);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/contrato/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_contrato(@RequestBody Contrato contrato) throws ClassNotFoundException {
        List<Contrato> resultado = contratoService.SP_CONTRATO_CONSULTAR(contrato);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/solicitud-compra/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_solicitud_compra(@RequestBody Solicitud_compra solicitud_compra) throws ClassNotFoundException {
        List<Solicitud_compra> resultado = solicitudCompraService.SP_SOLICITUD_COMPRA_CONSULTAR(solicitud_compra);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/proceso-venta/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_compra(@RequestBody ProcesoVenta procesoVenta) throws ClassNotFoundException {
        List<ProcesoVenta> resultado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVenta);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/producto/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar_producto(@RequestBody Producto producto) throws ClassNotFoundException {
        List<Producto> resultado = productoService.SP_PRODUCTO_CONSULTAR(producto);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/ingreso/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> ingreso_producto(@RequestBody Ingreso ingreso) throws ClassNotFoundException {
        List<Ingreso> resultado = ingresoService.SP_INGRESO_CONSULTAR(ingreso);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }

    @RequestMapping(    value = "/proceso-venta-ingreso/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> proceso_venta_ingreso_consultar(@RequestBody ProcesoVentaIngreso procesoVentaIngreso) throws ClassNotFoundException {
        List<ProcesoVentaIngreso> resultado = procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngreso);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }
}
