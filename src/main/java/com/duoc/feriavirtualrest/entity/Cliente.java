package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_CLIENTE_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_CLIENTE_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_USUARIO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_IDENTIFICADOR", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_RAZON_SOCIAL", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_DIRECCION", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CIUDAD", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PAIS_ORIGEN", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_TIPO_CLIENTE", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CORREO", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    private int id;
    private int usuario_id;
    private String identificador;
    private String razonSocial;
    private String direccion;
    private String ciudad;
    private String pais_origen;
    private int tipo_cliente;
    private String correo;
    private char habilitado;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "USUARIO_ID")
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Column(name = "IDENTIFICADOR")
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    @Column(name = "RAZONSOCIAL")
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Column(name = "DIRECCION")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "CIUDAD")
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "PAIS_ORIGEN")
    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    @Column(name = "TIPO_CLIENTE")
    public int getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(int tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    @Column(name = "CORREO")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Column(name = "HABILITADO")
    public char getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(char habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_USUARIO_ID", Integer.class, this.usuario_id == 0 ? null : this.usuario_id));
        LISTA_SP_IN.add(new SPDataIN("IN_IDENTIFICADOR", String.class, this.identificador == null ? null : this.identificador));
        LISTA_SP_IN.add(new SPDataIN("IN_RAZONSOCIAL", String.class, this.razonSocial == null ? null : this.razonSocial));
        LISTA_SP_IN.add(new SPDataIN("IN_DIRECCION", String.class, this.direccion == null ? null : this.direccion));
        LISTA_SP_IN.add(new SPDataIN("IN_CIUDAD", String.class, this.ciudad == null ? null : this.ciudad));
        LISTA_SP_IN.add(new SPDataIN("IN_PAIS_ORIGEN", String.class, this.pais_origen == null ? null : this.pais_origen));
        LISTA_SP_IN.add(new SPDataIN("IN_TIPO_CLIENTE", Integer.class, this.tipo_cliente == 0 ? null : this.tipo_cliente));
        LISTA_SP_IN.add(new SPDataIN("IN_CORREO", String.class, this.correo == null ? null : this.correo));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", String.class, this.habilitado == 0 ? null : this.habilitado));
        return LISTA_SP_IN;
    }



}
