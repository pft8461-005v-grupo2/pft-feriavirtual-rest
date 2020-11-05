package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Cliente;
import com.duoc.feriavirtualrest.entity.Usuario;
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

    @RequestMapping(    value = "/cliente/actualizar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> usuario_actualizar(@RequestBody Cliente cliente){
        return new ResponseEntity<Object>(clienteService.SP_CLIENTE_ACTUALIZAR(cliente), HttpStatus.OK);
    }

}
