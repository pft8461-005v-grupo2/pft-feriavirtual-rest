package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.constant.UtilConstant;
import com.duoc.feriavirtualrest.entity.Detalle_subasta;
import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.ProcesoVentaIngreso;
import com.duoc.feriavirtualrest.entity.Producto;
import com.duoc.feriavirtualrest.entity.Solicitud_compra;
import com.duoc.feriavirtualrest.entity.StockDisponible;
import com.duoc.feriavirtualrest.entity.Subasta;
import com.duoc.feriavirtualrest.model.IngresoCompleto;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.DetalleSubastaService;
import com.duoc.feriavirtualrest.service.GestionesService;
import com.duoc.feriavirtualrest.service.IngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaIngresoService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.ProductoService;
import com.duoc.feriavirtualrest.service.SolicitudCompraService;
import com.duoc.feriavirtualrest.service.StockDisponibleService;
import com.duoc.feriavirtualrest.service.SubastaService;
import com.duoc.feriavirtualrest.util.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private SubastaService subastaService;

    @Autowired
    private DetalleSubastaService detalleSubastaService;

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
                        if(crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId(), totalKilogramosPendientes)){
                            actualizarEtapaProcesoVenta(procesoVentaEnContexto.getId(), UtilConstant.ETAPA_PROCESO_EN_ACUERDO);
                            actualizarValoresProcesoVenta(procesoVentaEnContexto.getId());
                            desahabilitarSolicitudCompra(procesoVenta.getSolicitud_compra_id());
                            return 3;
                        } else { return -1; }
                    }
                }else{
                    // Se deshabilita
                    boolean ingresoDeshabilitado = deshabilitarIngreso(stockVentaMasBarato.getIngreso_id());
                    if(ingresoDeshabilitado){
                        if(crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId(), stockVentaMasBarato.getKilogramos())){
                            actualizarEtapaProcesoVenta(procesoVentaEnContexto.getId(), UtilConstant.ETAPA_PROCESO_EN_ACUERDO);
                            actualizarValoresProcesoVenta(procesoVentaEnContexto.getId());
                            desahabilitarSolicitudCompra(procesoVenta.getSolicitud_compra_id());
                            return 3;
                        } else { return -1; }
                    } else { return -1; }
                }
            }else{
                // Si el más barato no tiene suficiente stock
                // Se resta de ese ingreso
                // 1000 >= 2000
                // Se deshabilita lo que exista
                boolean ingresoDeshabilitado = deshabilitarIngreso(stockVentaMasBarato.getIngreso_id());
                if(!crearRegistroProcesoVentaIngreso(stockVentaMasBarato.getIngreso_id(), procesoVentaEnContexto.getId(), stockVentaMasBarato.getKilogramos())){
                    return -1;
                }
                totalKilogramosPendientes = totalKilogramosPendientes - stockVentaMasBarato.getKilogramos();
            }

            if(stockVentaMasBarato != null){
                stockDisponibleDelProducto.remove(stockVentaMasBarato);
            }

        }

        if(totalKilogramosPendientes > 0){
            desahabilitarSolicitudCompra(procesoVenta.getSolicitud_compra_id());
            return 2;
        }else{
            actualizarValoresProcesoVenta(procesoVentaEnContexto.getId());
            desahabilitarSolicitudCompra(procesoVenta.getSolicitud_compra_id());
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

    public boolean crearRegistroProcesoVentaIngreso(Integer ingreso_id, Integer procesoVenta_id, Integer kilogramosocupados) throws IOException {
        ProcesoVentaIngreso pvi = new ProcesoVentaIngreso();
        pvi.setIngreso_id(ingreso_id);
        pvi.setProceso_venta_id(procesoVenta_id);
        pvi.setKilogramosocupados(kilogramosocupados);
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
    public void desahabilitarSolicitudCompra(Integer solicitudCompraId){
        Solicitud_compra solicitud_compraAActualizar = new Solicitud_compra();
        solicitud_compraAActualizar.setId(solicitudCompraId);
        solicitud_compraAActualizar.setHabilitado(0);
        solicitudCompraService.SP_SOLICITUD_COMPRA_ACTUALIZAR(solicitud_compraAActualizar);
    }

    @Override
    public void actualizarValoresProcesoVenta(int procesoVentaId) throws ClassNotFoundException {

        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(procesoVentaId);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);

        if(procesoVentaEncontrado != null){
            ProcesoVentaIngreso procesoVentaIngresoABuscar = new ProcesoVentaIngreso();
            procesoVentaIngresoABuscar.setProceso_venta_id(procesoVentaId);
            List<ProcesoVentaIngreso> listaProcesoVentaIngresoEncontrados = procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CONSULTAR(procesoVentaIngresoABuscar);

            Integer precioVentaTotal = 0;
            Integer precioCostoTotal = 0;

            for (ProcesoVentaIngreso pvi : listaProcesoVentaIngresoEncontrados){
                Ingreso ingresoABuscar = new Ingreso();
                ingresoABuscar.setId(pvi.getIngreso_id());
                Ingreso ingresoEncontrado = ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null);

                precioCostoTotal += pvi.getKilogramosocupados()  * ingresoEncontrado.getPreciokgcostounitario();
                precioVentaTotal += pvi.getKilogramosocupados()  * ingresoEncontrado.getPreciokgventaunitario();
            }

            ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
            procesoVentaAActualizar.setId(procesoVentaId);
            procesoVentaAActualizar.setPreciocostototal(precioCostoTotal);
            procesoVentaAActualizar.setPrecioventatotal(precioVentaTotal);
            procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar);
        }
    }

    @Override
    public int iniciarSubasta(Subasta subasta) throws ClassNotFoundException, IOException {

        if(subasta == null){ return -1; }
        Integer procesoVenta_id = subasta.getId();
        if(procesoVenta_id == null){ return -1; }

        Date fechatermino = subasta.getFechatermino();
        if(fechatermino == null){ return -1; }


        // Obtenemos PROCESO_VENTA
        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setId(subasta.getId()); // Por temas de optimización, ID de SUBASTA se refiere a PROCESO_VENTA_ID
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);

        if(procesoVentaEncontrado == null) { return -1; }

        if(procesoVentaEncontrado.getEtapa() != UtilConstant.ETAPA_PROCESO_ACUERDO_ACEPTADO){
            return -2;
        }

        Subasta subastaACrear = new Subasta();
        subastaACrear.setFechatermino(fechatermino);
        ResponseSP response = Utiles.objectToResponseSP(subastaService.SP_SUBASTA_CREAR(subastaACrear));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                subastaACrear.setId(response.getOUT_ID_SALIDA());
            }else { return -1; }
        }else { return -1; }

        // Se actualiza procesoVenta
        ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
        procesoVentaAActualizar.setId(procesoVenta_id);
        procesoVentaAActualizar.setSubasta_id(subastaACrear.getId());
        procesoVentaAActualizar.setEtapa(UtilConstant.ETAPA_PROCESO_SUBASTA_INICIADA);
        ResponseSP responseActualizacion = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar));
        if(responseActualizacion != null){
            if(response.getOUT_ID_SALIDA() > 0){
                return 1;
            } else { return -1; }
        } else { return -1; }
    }

    @Override
    public int detenerSubasta(Subasta subasta) throws ClassNotFoundException, IOException {

        ProcesoVenta procesoVentaABuscar = new ProcesoVenta();
        procesoVentaABuscar.setSubasta_id(subasta.getId());
        procesoVentaABuscar.setEtapa(UtilConstant.ETAPA_PROCESO_SUBASTA_INICIADA);
        ProcesoVenta procesoVentaEncontrado = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(procesoVentaABuscar).stream().findFirst().orElse(null);

        if(procesoVentaEncontrado == null){ return -1; }

        Subasta subastaABuscar = new Subasta();
        subastaABuscar.setId(subasta.getId());
        Subasta subastaEncontrado = subastaService.SP_SUBASTA_CONSULTAR(subastaABuscar).stream().findFirst().orElse(null);

        if(subastaEncontrado == null){ return -1; }

        // Buscamos el precio más barato
        Detalle_subasta detalle_subastaABuscar = new Detalle_subasta();
        detalle_subastaABuscar.setSubasta_id(subasta.getId());
        List<Detalle_subasta> listaDetalleSubastaEncontrados = detalleSubastaService.SP_DETALLE_SUBASTA_CONSULTAR(detalle_subastaABuscar);

        if(listaDetalleSubastaEncontrados.isEmpty()){
            return -2;
        }

        Detalle_subasta ofertaMasBarata = listaDetalleSubastaEncontrados.stream()
                .reduce(new Detalle_subasta(0), (ofertaMemo, ofertaActual) ->
                        ofertaMemo.getValorpropuesta() == 0 ? ofertaActual :
                                ofertaActual.getValorpropuesta() < ofertaMemo.getValorpropuesta() ?
                                        ofertaActual : ofertaMemo );


        // TODO: Registrar el transportista ganador en Subasta

        // Actualizamos Subasta
        Subasta subastaAActualizar = new Subasta();
        subastaAActualizar.setId(subasta.getId());
        subastaAActualizar.setPrecioganador(ofertaMasBarata.getValorpropuesta());
        subastaAActualizar.setHabilitado(0);
        ResponseSP respuestaSubasta = Utiles.objectToResponseSP(subastaService.SP_SUBASTA_ACTUALIZAR(subastaAActualizar));
        if(respuestaSubasta != null){
            if(respuestaSubasta.getOUT_ID_SALIDA() > 0){
                // Actualizamos Proceso Venta y pasamos a etapa
                ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
                procesoVentaAActualizar.setId(procesoVentaEncontrado.getId());
                procesoVentaAActualizar.setEtapa(UtilConstant.ETAPA_PROCESO_EN_TRANSITO);
                ResponseSP respuestaProcesoVenta = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar));
                if(respuestaProcesoVenta != null){
                    if(respuestaProcesoVenta.getOUT_ID_SALIDA() > 0){
                        return 1;
                    } else { return -1; }
                } else { return -1; }
            } else { return -1; }
        } else { return -1; }
    }

    @Override
    public int iniciarProcesoVenta(Ingreso ingreso) throws IOException, ClassNotFoundException {

        int procesoVenta_generado = 0;

        // Se envía proceso de venta vacío
        ResponseSP respuestaProcesoVenta = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_CREAR(new ProcesoVenta()));
        if(respuestaProcesoVenta != null) {
            if (respuestaProcesoVenta.getOUT_ID_SALIDA() > 0) {
                procesoVenta_generado = respuestaProcesoVenta.getOUT_ID_SALIDA();
            } else {
                return -1;
            }
        } else { return -1; }

        // Se busca el Ingreso asociado
        Ingreso ingresoABuscar = new Ingreso();
        ingresoABuscar.setId(ingreso.getId());
        Ingreso ingresoEncontrado = ingresoService.SP_INGRESO_CONSULTAR(ingresoABuscar).stream().findFirst().orElse(null);

        if(ingresoEncontrado == null){ return -1; }

        ProcesoVenta procesoVentaAActualizar = new ProcesoVenta();
        procesoVentaAActualizar.setId(procesoVenta_generado);
        procesoVentaAActualizar.setEtapa(UtilConstant.ETAPA_PROCESO_NACIONAL_INICIADO);
        procesoVentaAActualizar.setPreciocostototal(ingresoEncontrado.getKilogramos() * ingresoEncontrado.getPreciokgcostounitario());
        procesoVentaAActualizar.setPrecioventatotal(ingresoEncontrado.getKilogramos() * ingresoEncontrado.getPreciokgventaunitario());
        ResponseSP respuestaProcesoVentaActualizar = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_ACTUALIZAR(procesoVentaAActualizar));
        if(respuestaProcesoVentaActualizar == null) { return -1; }
        if(respuestaProcesoVentaActualizar.getOUT_ID_SALIDA() <= 0) { return -1; }

        ProcesoVentaIngreso procesoVentaIngresoACrear = new ProcesoVentaIngreso();
        procesoVentaIngresoACrear.setProceso_venta_id(procesoVenta_generado);
        procesoVentaIngresoACrear.setIngreso_id(ingresoEncontrado.getId());
        procesoVentaIngresoACrear.setKilogramosocupados(ingresoEncontrado.getKilogramos());
        ResponseSP respuestaProcesoVentaIngreso = Utiles.objectToResponseSP(procesoVentaIngresoService.SP_PROCESO_VENTA_INGRESO_CREAR(procesoVentaIngresoACrear));
        if(respuestaProcesoVentaIngreso != null) {
            if (respuestaProcesoVentaIngreso.getOUT_ID_SALIDA() == 0) {
                // Actualizar Ingreso
                Ingreso ingresoAActualizar = new Ingreso();
                ingresoAActualizar.setId(ingresoEncontrado.getId());
                ingresoAActualizar.setHabilitado(0);
                ResponseSP respuestaIngresoActualizar = Utiles.objectToResponseSP(ingresoService.SP_INGRESO_ACTUALIZAR(ingresoAActualizar));
                if(respuestaIngresoActualizar == null) { return -1; }
                if(respuestaIngresoActualizar.getOUT_ID_SALIDA() <= 0){ return -1; }
                return 1;
            } else {
                return -1;
            }
        } else { return -1; }
    }
}
