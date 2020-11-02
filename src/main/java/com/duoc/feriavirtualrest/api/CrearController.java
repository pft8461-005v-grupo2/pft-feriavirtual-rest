package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.model.UsuarioModel;
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
public class CrearController {


    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(    value = "/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crear(@RequestBody UsuarioModel usuarioModel){
        return new ResponseEntity<Object>(usuarioService.SP_USUARIO_CREAR(usuarioModel), HttpStatus.OK);
    }

}
