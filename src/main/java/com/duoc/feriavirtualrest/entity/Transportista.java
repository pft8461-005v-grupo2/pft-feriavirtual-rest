package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "TRANSPORTISTA") que es esto? cuando va?
//falta un metodo que va antes de los campos a continuacion
//metodo usuario tiene un implement seriabizable o algo asi y el rol no lo tiene, cual es la diferencia?
@Entity
@Table(name = "TRANSPORTISTA")
public class Transportista {

    private int id;
    private int usuario_id;
    private String rut;
    private String razonsocial;
    private String direccion;
    private String comuna;
    private String correo;
    private char habilitado;

    public Transportista() {
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "USUARIO_ID")
    public int getUsuario_id() {
        return usuario_id;
    }

    @Column(name = "RUT")
    public String getRut() {
        return rut;
    }

    @Column(name = "RAZONSOCIAL")
    public String getRazonsocial() {
        return razonsocial;
    }

    @Column(name = "DIRECCION")
    public String getDireccion() {
        return direccion;
    }

    @Column(name = "COMUNA")
    public String getComuna() {
        return comuna;
    }

    @Column(name = "CORREO")
    public String getCorreo() {
        return correo;
    }

    @Column(name = "HABILITADO")
    public char getHabilitado() {
        return habilitado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setHabilitado(char habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_USUARIO_ID", Integer.class, this.usuario_id == 0 ? null : this.usuario_id));
        LISTA_SP_IN.add(new SPDataIN("IN_RUT", String.class, this.rut == null ? null : this.rut));
        LISTA_SP_IN.add(new SPDataIN("IN_RAZONSOCIAL", String.class, this.razonsocial == null ? null : this.razonsocial));
        LISTA_SP_IN.add(new SPDataIN("IN_DIRECCION", String.class, this.direccion == null ? null : this.direccion));
        LISTA_SP_IN.add(new SPDataIN("IN_COMUNA", String.class, this.comuna == null ? null : this.comuna));
        LISTA_SP_IN.add(new SPDataIN("IN_CORREO", String.class, this.correo == null ? null : this.correo));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", String.class, this.habilitado == 0 ? null : this.habilitado));
        return LISTA_SP_IN;
    }

}
