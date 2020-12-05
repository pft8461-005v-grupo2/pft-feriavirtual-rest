package com.duoc.feriavirtualrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "STOCK_DISPONIBLE")
public class StockDisponible {

    private Integer ingreso_id;
    private Integer producto_id;
    private String descripcion;
    private Integer kilogramos;
    private Integer preciokgventaunitario;
    private Integer preciokgcostounitario;
    private Date fechacreacion_ingreso;
    private Integer productor_id;
    private Integer usuario_id;
    private String rut_productor;
    private String razonsocial_productor;
    private String direccion_productor;
    private String comuna_productor;
    private String correo_productor;
    private Integer contrato_id;
    private Integer vigencia_contrato;

    public StockDisponible() {
    }

    @Id
    @Column(name = "INGRESO_ID")
    public Integer getIngreso_id() {
        return ingreso_id;
    }

    @Column(name = "PRODUCTO_ID")
    public Integer getProducto_id() {
        return producto_id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    @Column(name = "KILOGRAMOS")
    public Integer getKilogramos() {
        return kilogramos;
    }

    @Column(name = "PRECIOKGVENTAUNITARIO")
    public Integer getPreciokgventaunitario() {
        return preciokgventaunitario;
    }

    @Column(name = "PRECIOKGCOSTOUNITARIO")
    public Integer getPreciokgcostounitario() {
        return preciokgcostounitario;
    }

    @Column(name = "FECHACREACION_INGRESO")
    public Date getFechacreacion_ingreso() {
        return fechacreacion_ingreso;
    }

    @Column(name = "PRODUCTOR_ID")
    public Integer getProductor_id() {
        return productor_id;
    }

    @Column(name = "USUARIO_ID")
    public Integer getUsuario_id() {
        return usuario_id;
    }

    @Column(name = "RUT_PRODUCTOR")
    public String getRut_productor() {
        return rut_productor;
    }

    @Column(name = "RAZONSOCIAL_PRODUCTOR")
    public String getRazonsocial_productor() {
        return razonsocial_productor;
    }

    @Column(name = "DIRECCION_PRODUCTOR")
    public String getDireccion_productor() {
        return direccion_productor;
    }

    @Column(name = "COMUNA_PRODUCTOR")
    public String getComuna_productor() {
        return comuna_productor;
    }

    @Column(name = "CORREO_PRODUCTOR")
    public String getCorreo_productor() {
        return correo_productor;
    }

    @Column(name = "CONTRATO_ID")
    public Integer getContrato_id() {
        return contrato_id;
    }

    @Column(name = "VIGENCIA_CONTRATO")
    public Integer getVigencia_contrato() {
        return vigencia_contrato;
    }

    public void setIngreso_id(Integer ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setKilogramos(Integer kilogramos) {
        this.kilogramos = kilogramos;
    }

    public void setPreciokgventaunitario(Integer preciokgventaunitario) {
        this.preciokgventaunitario = preciokgventaunitario;
    }

    public void setPreciokgcostounitario(Integer preciokgcostounitario) {
        this.preciokgcostounitario = preciokgcostounitario;
    }

    public void setFechacreacion_ingreso(Date fechacreacion_ingreso) {
        this.fechacreacion_ingreso = fechacreacion_ingreso;
    }

    public void setProductor_id(Integer productor_id) {
        this.productor_id = productor_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setRut_productor(String rut_productor) {
        this.rut_productor = rut_productor;
    }

    public void setRazonsocial_productor(String razonsocial_productor) {
        this.razonsocial_productor = razonsocial_productor;
    }

    public void setDireccion_productor(String direccion_productor) {
        this.direccion_productor = direccion_productor;
    }

    public void setComuna_productor(String comuna_productor) {
        this.comuna_productor = comuna_productor;
    }

    public void setCorreo_productor(String correo_productor) {
        this.correo_productor = correo_productor;
    }

    public void setContrato_id(Integer contrato_id) {
        this.contrato_id = contrato_id;
    }

    public void setVigencia_contrato(Integer vigencia_contrato) {
        this.vigencia_contrato = vigencia_contrato;
    }
}
