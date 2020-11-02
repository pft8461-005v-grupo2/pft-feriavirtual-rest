package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.Usuario;
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

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsultasController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(    value = "/usuario/consultar",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultar(@RequestBody Usuario usuario) throws ClassNotFoundException {
        List<Usuario> resultado = usuarioService.SP_USUARIO_CONSULTAR(usuario);
        return new ResponseEntity<Object>(resultado, HttpStatus.OK);
    }


}
