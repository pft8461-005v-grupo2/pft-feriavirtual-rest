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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "SP_USUARIO_CREAR",
                procedureName = SPConstant.TABLE_SPACE + "SP_USUARIO_CREAR",
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_ROL_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CORREO", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CONTRASENA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private int id;
    private int rol_id;
    private String correo;
    private String contrasena;
    private char habilitado;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "ROL_ID")
    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    @Column(name = "CORREO")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Column(name = "CONTRASENA")
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        LISTA_SP_IN.add(new SPDataIN("IN_ROL_ID", Integer.class, this.rol_id == 0 ? null : this.rol_id));
        LISTA_SP_IN.add(new SPDataIN("IN_CORREO", String.class, this.correo == null ? null : this.correo));
        LISTA_SP_IN.add(new SPDataIN("IN_CONTRASENA", String.class, this.contrasena == null ? null : this.contrasena));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", String.class, this.habilitado == 0 ? null : this.habilitado));
        return LISTA_SP_IN;
    }

}
