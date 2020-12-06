package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.StockDisponible;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.GestionesService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.StockDisponibleService;
import com.duoc.feriavirtualrest.util.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("gestionesService")
public class GestionesServiceImp implements GestionesService {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private StockDisponibleService stockDisponibleService;

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private ProcesoVentaIngresoService procesoVentaIngresoService;

    @Override
    public int iniciarProcesoVenta(ProcesoVenta procesoVenta) throws IOException, ClassNotFoundException {

        // Verificamos que exista una solicitud de compra asociada
        Solicitud_compra solicitud_compraEnContexto = new Solicitud_compra();
        solicitud_compraEnContexto.setId(procesoVenta.getSolicitud_compra_id());
        solicitud_compraEnContexto = solicitudCompraService.SP_SOLICITUD_COMPRA_CONSULTAR(solicitud_compraEnContexto)
                .stream().findFirst().orElse(null);

        if(solicitud_compraEnContexto == null){ return -2; } // Solicitud NO encontrada


        // Verificamos si el producto existe
        Producto productoABuscar = new Producto();
        productoABuscar.setDescripcion(solicitud_compraEnContexto.getProducto().toUpperCase());
        productoABuscar = productoService.SP_PRODUCTO_CONSULTAR(productoABuscar).stream().findFirst().orElse(null);

        if(productoABuscar == null){ return -3; } // Producto no encontrado


        // Iniciamos el proceso de venta (ya que existe el producto y solicitud de compra)
        ProcesoVenta procesoVentaEnContexto = new ProcesoVenta();
        ResponseSP response = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_CREAR(procesoVenta));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                ProcesoVenta pv = new ProcesoVenta();
                pv.setId(response.getOUT_ID_SALIDA());
                procesoVentaEnContexto = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(pv).stream().findFirst().orElse(null);
                if(procesoVentaEnContexto == null) { return -1; } // Error, no se pudo generar el proceso de venta
            }else { return -1; }
        }else{ return -1; }

        // Buscamos stock disponible
        List<StockDisponible> todoStockDisponible = stockDisponibleService.consultarStockDisponible();
        List<StockDisponible> stockDisponibleDelProducto = new ArrayList<>();
        for (StockDisponible stockDisponible : todoStockDisponible) {
            if(stockDisponible.getProducto_id() == productoABuscar.getId()){
                stockDisponibleDelProducto.add(stockDisponible);
            }
        }

        Integer totalKilogramosPendientes = solicitud_compraEnContexto.getKilogramos();

        while(stockDisponibleDelProducto.size() > 0 ){
            // Expresion Lambda para ver producto más barato
            StockDisponible stockVentaMasBarato = stockDisponibleDelProducto.stream()
                    .reduce(new StockDisponible(0), (stockMemo, stockActual) ->
                            stockMemo.getPreciokgventaunitario() == 0 ? stockActual :
                            stockActual.getPreciokgventaunitario() < stockMemo.getPreciokgventaunitario() ?
                                    stockActual : stockMemo );

            // Si el más barato tiene suficiente stock
            // Se resta de ese ingreso
            if(stockVentaMasBarato.getKilogramos() >= totalKilogramosPendientes){
                // Se actualiza el ingreso en el caso que existan excedentes
                Integer excedentes = stockVentaMasBarato.getKilogramos() - totalKilogramosPendientes;

                if(excedentes > 0){
                    // Se deshabilita
                    boolean ingresoDeshabilitado = deshabilitarIngreso(stockVentaMasBarato.getIngreso_id());

                    // Se habilita ingreso nuevo de excedentes
                    if(ingresoDeshabilitado){
                        Ingreso ingresoAConsultar = new Ingreso();
                        ingresoAConsultar.setId(stockVentaMasBarato.getIngreso_id());
                        Ingreso ingresoEncontrado = ingresoService.SP_INGRESO_CONSULTAR(ingresoAConsultar)
                                .stream().findFirst().orElse(null);
                        if(ingresoEncontrado == null){ return -1; }

                        ingresoEncontrado.setId(null);
                        ingresoEncontrado.setHabilitado(1);
                        ingresoEncontrado.setKilogramos(excedentes);
                        habilitarIngresoDeExcedentes(ingresoEncontrado);
                        if(crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId())){
                            actualizarEtapaProcesoVenta(procesoVentaEnContexto.getId(), 2);
                            return 3;
                        } else { return -1; }
                    }
                }else{
                    // Se deshabilita
                    boolean ingresoDeshabilitado = deshabilitarIngreso(stockVentaMasBarato.getIngreso_id());
                    if(ingresoDeshabilitado){
                        if(crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId())){
                            actualizarEtapaProcesoVenta(procesoVentaEnContexto.getId(), 2);
                            return 3;
                        } else { return -1; }
                    } else { return -1; }
                }
            }else{
                // Si el más barato no tiene suficiente stock
                // Se resta de ese ingreso
                // 2000 >= 5000
                // Se deshabilita lo que exista
                boolean ingresoDeshabilitado = deshabilitarIngreso(stockVentaMasBarato.getIngreso_id());
                if(!crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId())){
                    return -1;
                }
                totalKilogramosPendientes = totalKilogramosPendientes - stockVentaMasBarato.getKilogramos();
            }

            if(stockVentaMasBarato != null){
                stockDisponibleDelProducto.remove(stockVentaMasBarato);
            }

        }

        if(totalKilogramosPendientes > 0){
            return 2;
        }else{
            return 3;
        }
    }

    public boolean deshabilitarIngreso(Integer ingreso_id) throws IOException {
        Ingreso ingresoADeshabilitar = new Ingreso();
        ingresoADeshabilitar.setId(ingreso_id);
        ingresoADeshabilitar.setHabilitado(0);
        ResponseSP response = Utiles.objectToResponseSP(ingresoService.SP_INGRESO_ACTUALIZAR(ingresoADeshabilitar));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){ return true; }
        }
        return false;
    }

    public boolean habilitarIngresoDeExcedentes(Ingreso ingreso) throws IOException {
        ResponseSP response = Utiles.objectToResponseSP(ingresoService.SP_INGRESO_CREAR(ingreso));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){ return true; }
        }
        return false;
    }

    public boolean crearRegistroProcesoVentaIngreso(Integer ingreso_id, Integer procesoVenta_id) throws IOException {
        ProcesoVentaIngreso pvi = new ProcesoVentaIngreso();
        pvi.setIngreso_id(ingreso_id);
        pvi.setProceso_venta_id(procesoVenta_id);
        pvi.setHabilitado(1);
        ResponseSP response = Utiles.objectToResponseSP(procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CREAR(pvi));
        if(response != null){
            if(response.getOUT_ID_SALIDA() == 0){ return true; }
        }
        return false;
    }

    public boolean actualizarEtapaProcesoVenta(Integer procesoVenta_id, Integer etapa) throws IOException {
        ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
        procesoVentaAActualizar.setId(procesoVenta_id);
        procesoVentaAActualizar.setEtapa(etapa);
        ResponseSP response = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){ return true; }
        }
        return false;
    }
}
