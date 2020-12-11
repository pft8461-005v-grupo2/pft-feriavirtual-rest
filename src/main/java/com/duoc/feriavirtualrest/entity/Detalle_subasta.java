package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.keys.DetalleSubastaKeys;
import com.duoc.feriavirtualrest.util.SPDataIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_DETALLE_SUBASTA_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_DETALLE_SUBASTA_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_TRANSPORTISTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_SUBASTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_VALOR_PROPUESTA", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_DET_SUBASTA_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_DET_SUBASTA_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_TRANSPORTISTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_SUBASTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_VALOR_PROPUESTA", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@IdClass(DetalleSubastaKeys.class)
@Table(name ="DETALLE_SUBASTA")
public class Detalle_subasta {

    private Integer transportista_id;
    private Integer subasta_id;
    private Integer valorpropuesta;

    public Detalle_subasta() {
    }

    public Detalle_subasta(Integer valorpropuesta){
        this.valorpropuesta = valorpropuesta;
    }

    @Id
    @Column(name = "TRANSPORTISTA_ID")
    public Integer getTransportista_id() {
        return transportista_id;
    }

    public void setTransportista_id(Integer transportista_id) {
        this.transportista_id = transportista_id;
    }

    @Id
    @Column(name = "SUBASTA_ID")
    public Integer getSubasta_id() {
        return subasta_id;
    }

    public void setSubasta_id(Integer subasta_id) {
        this.subasta_id = subasta_id;
    }

    @Id
    @Column(name = "VALORPROPUESTA")
    public Integer getValorpropuesta() {
        return valorpropuesta;
    }

    public void setValorpropuesta(Integer valorpropuesta) {
        this.valorpropuesta = valorpropuesta;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_TRANSPORTISTA_ID", Integer.class, this.transportista_id == null ? null : this.transportista_id));
        LISTA_SP_IN.add(new SPDataIN("IN_SUBASTA_ID", Integer.class, this.subasta_id == null ? null : this.subasta_id));
        LISTA_SP_IN.add(new SPDataIN("IN_VALORPROPUESTA", Integer.class, this.valorpropuesta == null ? null : this.valorpropuesta));
        return LISTA_SP_IN;
    }
}
