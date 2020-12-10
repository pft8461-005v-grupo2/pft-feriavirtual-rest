package com.duoc.feriavirtualrest.constant;

public class UtilConstant {

    // [ Roles ]
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CLIENTE_EXT = "ROLE_CLIENTE_EXTERNO";
    public static final String ROLE_CLIENTE_INT = "ROLE_CLIENTE_INTERNO";
    public static final String ROLE_PRODUCTOR = "ROLE_PRODUCTOR";
    public static final String ROLE_TRANSPORTISTA = "ROLE_TRANSPORTISTA";

    public static final int ROLE_ID_ADMIN = 1;
    public static final int ROLE_ID_CLIENTE_EXT = 4;
    public static final int ROLE_ID_CLIENTE_INT = 5;
    public static final int ROLE_ID_PRODUCTOR = 2;
    public static final int ROLE_ID_TRANSPORTISTA = 3;


    // Etapas

    public static final int ETAPA_PROCESO_INICIADO = 1; // Se creo proceso de venta, pero no hay stock o stock insuficiente (pendiente de ingresos por parte del productor)
    public static final int ETAPA_PROCESO_EN_ACUERDO = 2; // Se crea proceso de venta y hay stock suficiente (esto quiere decir que hay ingresos y productores asociados)
    public static final int ETAPA_PROCESO_ACUERDO_PENDIENTE_RESPUESTA = 3; // El administrador ya reviso la propuesta y la esta enviando al cliente internacional
    public static final int ETAPA_PROCESO_ACUERDO_ACEPTADO = 4; // El cliente internacional acepta propuesta de venta y la envia al administrador para que este genere la subasta.
    public static final int ETAPA_PROCESO_SUBASTA_INICIADA = 5; // El administrador inicia subasta del proceso de venta (este estado significa además que esta en espera de encontrar un transportista ideal)
    public static final int ETAPA_PROCESO_EN_TRANSITO = 6; // Se encuentra transportista ideal con buen precio, ahora se esta transportando desde productor hasta bodega central
    public static final int ETAPA_PROCESO_ENTREGADO_EN_BODEGA_CENTRAL = 7; // Se entrega mercaderia en bodega central por transportista
    public static final int ETAPA_PROCESO_DESPACHO = 8; // Se despacha final
    public static final int ETAPA_PROCESO_RESPUESTA_CLIENTE = 9; // El cliente da feedback del producto recibido

    public static final int ETAPA_PROCESO_ACUERDO_RECHAZADO = -1;



    // Otros
    public static final int HABILITADO = 1;
    public static final int DESHABILITADO = 0;


}
