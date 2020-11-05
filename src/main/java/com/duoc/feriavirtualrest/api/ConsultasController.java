package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Contrato;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Transportista;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.model.ContratoModel;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ContratoService;
import com.duoc.feriavirtualrest.service.ProductorService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
