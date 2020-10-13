package com.duoc.feriavirtualrest.model;

public class UsuarioModel {

    private int id;
    private int rol_id;
    private String correo;
    private String contrasena;
    private char habilitado;

    public UsuarioModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public char getHabilitado() { return habilitado; }

    public void setHabilitado(char habilitado) { this.habilitado = habilitado; }
}
