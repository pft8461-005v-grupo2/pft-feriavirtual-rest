package com.duoc.feriavirtualrest.api;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.Subasta;
import com.duoc.feriavirtualrest.service.GestionesService;
import com.duoc.feriavirtualrest.service.StockDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class GestionesController {

    @Autowired
    private GestionesService gestionesService;

    @Autowired
    private StockDisponibleService stockDisponibleService;


    @RequestMapping(   value = "/consulta-stock-disponible",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> consulta_stock_disponible() {

        return new ResponseEntity<Object>(stockDisponibleService.consultarStockDisponible(), HttpStatus.OK);
    }

    @RequestMapping(   value = "/proceso-internacional/iniciar-proceso",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> iniciar_proceso_venta_internacional(@RequestBody ProcesoVenta procesoVenta) throws IOException, ClassNotFoundException {
        /*
            Retornos
            -3 = producto no encontrado
            -2 = Solicitud no encontrada
            -1 = Error
            1 = Se creo proceso y no hay stock
            2 = Se creo proceso y stock insuficiente
            3 = Se creo proceso y stock suficiente
         */
        return new ResponseEntity<Object>(gestionesService.iniciarProcesoVenta(procesoVenta), HttpStatus.OK);
    }


    @RequestMapping(   value = "/proceso-internacional/iniciar-subasta",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> iniciar_subasta(@RequestBody Subasta subasta) throws IOException, ClassNotFoundException {
        /*
            Entradas
            Por optimización, ID de SUBASTA trae la ID de PROCESO_VENTA
            Retornos
            -2 = Proceso de venta esta en una etapa no habilitada para iniciar subasta
            -1 = Error
            1 = Subasta iniciada
         */
        return new ResponseEntity<Object>(gestionesService.iniciarSubasta(subasta), HttpStatus.OK);
    }

    @RequestMapping(   value = "/proceso-internacional/detener-subasta",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> detener_subasta(@RequestBody Subasta subasta) throws IOException, ClassNotFoundException {
        /*
            Retornos
            -2 = No hay ofertas registradas, por ende no se puede poner un precio ganador
            -1 = Error
            1 = Subasta detenida
         */
        return new ResponseEntity<Object>(gestionesService.detenerSubasta(subasta), HttpStatus.OK);
    }
}
