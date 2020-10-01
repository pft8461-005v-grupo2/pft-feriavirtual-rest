package com.duoc.feriavirtualrest.controller;

import com.duoc.feriavirtualrest.model.UsuarioModel;
import com.duoc.feriavirtualrest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "usuario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @RequestMapping(    value = "/crear",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> add(@RequestBody UsuarioModel usuarioModel){
        return new ResponseEntity<Object>(usuarioService.SP_CREAR_USUARIO(usuarioModel), HttpStatus.OK);
    }

}
