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
import java.util.Date;
import java.util.List;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_SUBASTA_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_SUBASTA_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_FECHATERMINO", type=Date.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_SUBASTA_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_SUBASTA_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_ID_SUBASTA", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_FECHATERMINO", type=Date.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PRECIO_GANADOR", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_HABILITADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@Table(name = "SUBASTA")
public class Subasta {

    private Integer id;
    private Date fechatermino;
    private Integer precioganador;
    private Integer habilitado;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "FECHATERMINO")
    public Date getFechatermino() {
        return fechatermino;
    }

    public void setFechatermino(Date fechatermino) {
        this.fechatermino = fechatermino;
    }

    @Column(name = "PRECIOGANADOR")
    public Integer getPrecioganador() {
        return precioganador;
    }

    public void setPrecioganador(Integer precioganador) {
        this.precioganador = precioganador;
    }

    @Column(name = "HABILITADO")
    public Integer getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == null ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHATERMINO", java.sql.Timestamp.class, this.fechatermino == null ? null : this.fechatermino));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", Integer.class, this.habilitado == null ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
