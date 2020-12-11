package com.duoc.feriavirtualrest.constant;

public class ViewConstant {

    // [ Rutas Bases ]
    public static final String RUTABASE_ADMIN = "/admin/";
    public static final String RUTABASE_CLIENTEEXT = "/clienteext/";
    public static final String RUTABASE_CLIENTEINT = "/clienteint/";
    public static final String RUTABASE_PRODUCTOR = "/productor/";
    public static final String RUTABASE_TRANSPORTISTA = "/transportista/";



    // [ Login Page ]
    public static final String V_LOGIN = "/login";

    // [ Administración ]
    public static final String V_A_HOME = RUTABASE_ADMIN + "home";

    // [ Cliente Externo ]
    public static final String V_CE_HOME = RUTABASE_CLIENTEEXT + "home";
    public static final String V_CE_INGRESO = RUTABASE_CLIENTEEXT + "ingresarpedido";
    public static final String V_CE_MISPEDIDOS = RUTABASE_CLIENTEEXT + "mispedidos";

    // [ Cliente Interno ]
    public static final String V_CI_HOME = RUTABASE_CLIENTEINT + "home";
    public static final String V_CI_PROCESO_DISPONIBLES = RUTABASE_CLIENTEINT + "procesos_disponibles";

    // [ Productor ]
    public static final String V_P_HOME = RUTABASE_PRODUCTOR + "home";
    public static final String V_P_INGRESO = RUTABASE_PRODUCTOR + "ingresarproducto";
    public static final String V_P_PROCESOS = RUTABASE_PRODUCTOR + "procesos";

    // [ Transportista ]
    public static final String V_T_HOME = RUTABASE_TRANSPORTISTA + "home";
    public static final String V_T_SUBASTAS = RUTABASE_TRANSPORTISTA + "subastas";
    public static final String V_T_PROCESOS = RUTABASE_TRANSPORTISTA + "procesos";
    public static final String V_T_INFORMAR = RUTABASE_TRANSPORTISTA + "informar";

}
