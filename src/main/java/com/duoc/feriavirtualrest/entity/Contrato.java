package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Id;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_CONTRATO_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_CONTRATO_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_FECHAINICIO", type=Date.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_FECHATERMINO", type=Date.class)
                })
})
@Entity
@Table(name = "CONTRATO")
public class Contrato {

    private int id;
    private int productor_id;
    private Date fechainicio;
    private Date fechatermino;
    private Date fechacreacion;
    private int vigencia;

    public Contrato() {
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    @Column(name = "PRODUCTOR_ID")
    public int getProductor_id() {
        return productor_id;
    }

    @Column(name = "VIGENCIA")
    public int getVigencia() {
        return vigencia;
    }

    @Column(name = "FECHAINICIO")
    public Date getFechainicio() {
        return fechainicio;
    }

    @Column(name = "FECHATERMINO")
    public Date getFechatermino() {
        return fechatermino;
    }

    @Column(name = "FECHACREACION")
    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductor_id(int productor_id) {
        this.productor_id = productor_id;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setFechatermino(Date fechatermino) {
        this.fechatermino = fechatermino;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_ID", Integer.class, this.id == 0 ? null : this.id));
        LISTA_SP_IN.add(new SPDataIN("IN_PRODUCTOR_ID", Integer.class, this.productor_id == 0 ? null : this.productor_id));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHAINICIO", Date.class, this.fechainicio == null ? null : this.fechainicio));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHATERMINO", Date.class, this.fechatermino == null ? null : this.fechatermino));
        LISTA_SP_IN.add(new SPDataIN("IN_FECHACREACION", Date.class, this.fechacreacion == null ? null : this.fechacreacion));
        LISTA_SP_IN.add(new SPDataIN("IN_VIGENCIA", Integer.class, this.vigencia == 0 ? null : this.vigencia));
        return LISTA_SP_IN;
    }
}

