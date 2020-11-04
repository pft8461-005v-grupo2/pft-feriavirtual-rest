package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Productor;
import com.duoc.feriavirtualrest.entity.Usuario;
import com.duoc.feriavirtualrest.service.ClienteService;
import com.duoc.feriavirtualrest.service.ProductorService;
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
public class CrearController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductorService productorService;

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

}
