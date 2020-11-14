package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ContratoModel;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ContratoService;
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

}
