package com.duoc.feriavirtualrest.service.implementation;

import com.duoc.feriavirtualrest.entity.ProcesoVenta;
import com.duoc.feriavirtualrest.entity.StockDisponible;
import com.duoc.feriavirtualrest.model.ResponseSP;
import com.duoc.feriavirtualrest.service.GestionesService;
import com.duoc.feriavirtualrest.service.ProcesoVentaService;
import com.duoc.feriavirtualrest.service.StockDisponibleService;
import com.duoc.feriavirtualrest.util.Utiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("gestionesService")
public class GestionesServiceImp implements GestionesService {

    @Autowired
    private ProcesoVentaService procesoVentaService;

    @Autowired
    private StockDisponibleService stockDisponibleService;

    //@Autowired
    //private ProductoService productoService;

    @Override
    public int iniciarProcesoVenta(ProcesoVenta procesoVenta) throws IOException, ClassNotFoundException {
        ProcesoVenta procesoVentaEnContexto = new ProcesoVenta();

        ResponseSP response = Utiles.objectToResponseSP(procesoVentaService.SP_PROCESO_VENTA_CREAR(procesoVenta));
        if(response != null){
            if(response.getOUT_ID_SALIDA() > 0){
                ProcesoVenta pv = new ProcesoVenta();
                pv.setId(response.getOUT_ID_SALIDA());
                List<ProcesoVenta> responseConsultarProcesoVenta = procesoVentaService.SP_PROCESOVENTA_CONSULTAR(pv);
                if(responseConsultarProcesoVenta != null  && responseConsultarProcesoVenta.size() == 1){
                    procesoVentaEnContexto = responseConsultarProcesoVenta.get(0);
                }else{
                    return -1;
                }
            }else {
                return -1;
            }
        }else{
            return -1;
        }

        if(procesoVentaEnContexto == null){
            return -1; // ERROR
        }

        // Busqueda de stock disponible
        List<StockDisponible> stockDisponibleActual = stockDisponibleService.consultarStockDisponible();

        //List<StockDisponible> stockQueNosSirve =


        // Condicionar si existe o no existe stock

        // Si existe stock, debiese realizar el match con productor mas barato que tenga el producto,
        // si no cumple la cantidad, se busca otro productor, hasta llegar al total deseado.
        // y se genera la propuesta del pedido
        // PASA A LA ETAPA 2

        // Si no existe stock, no se genera ingreso, pero si se habilita para que los productores vean la "solicitud"
        // del producto que desea el cliente internacional y así puedan ofertar sus productos.


        return procesoVentaEnContexto != null ? 1 : 0;
    }


    @Override
    public boolean verificarExistenciaProducto(String producto) {
        return true;
    }
}
