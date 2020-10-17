package com.duoc.feriavirtualrest.model;

public class TransportistaModel {

    private int id;
    private int usuario_id;
    private String rut;
    private String razonSocial;
    private String direccion;
    private String comuna;
    private String correo;
    private char habilitado;

    public TransportistaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() { return usuario_id; }

    public void setRol_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getHabilitado() { return habilitado; }

    public void setHabilitado(char habilitado) { this.habilitado = habilitado; }
}
