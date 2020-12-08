package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;
import com.duoc.feriavirtualrest.entity.keys.ProcesoVentaIngresoKeys;
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
                name = SPConstant.SP_PROCESO_VENTA_INGRESO_CREAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PROCESO_VENTA_INGRESO_CREAR,
                parameters = {
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_INGRESO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PROCESO_VENTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_KILOGRAMOSOCUPADOS", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                }),
        @NamedStoredProcedureQuery(
                name = SPConstant.SP_PROCESO_VENTA_INGRESO_ACTUALIZAR,
                procedureName = SPConstant.TABLE_SPACE + SPConstant.SP_PROCESO_VENTA_INGRESO_ACTUALIZAR,
                parameters = {
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_INGRESO_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_PROCESO_VENTA_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_KILOGRAMOSOCUPADOS", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_HABILITADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
@Entity
@IdClass(ProcesoVentaIngresoKeys.class)
@Table(name = "PROCESO_VENTA_INGRESO")
public class ProcesoVentaIngreso {

    private Integer ingreso_id;
    private Integer proceso_venta_id;
    private Integer kilogramosocupados;
    private Integer habilitado;

    public ProcesoVentaIngreso() {
    }

    @Id
    @Column(name = "INGRESO_ID")
    public Integer getIngreso_id() {
        return ingreso_id;
    }

    @Id
    @Column(name = "PROCESO_VENTA_ID")
    public Integer getProceso_venta_id() {
        return proceso_venta_id;
    }

    @Column(name = "HABILITADO")
    public Integer getHabilitado() {
        return habilitado;
    }

    @Column(name = "KILOGRAMOSOCUPADOS")
    public Integer getKilogramosocupados() {
        return kilogramosocupados;
    }

    public void setIngreso_id(Integer ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    public void setProceso_venta_id(Integer proceso_venta_id) {
        this.proceso_venta_id = proceso_venta_id;
    }

    public void setHabilitado(Integer habilitado) {
        this.habilitado = habilitado;
    }

    public void setKilogramosocupados(Integer kilogramosocupados) {
        this.kilogramosocupados = kilogramosocupados;
    }

    public List<SPDataIN> generarDataIN(){
        List<SPDataIN> LISTA_SP_IN = new ArrayList<>();
        LISTA_SP_IN.add(new SPDataIN("IN_INGRESO_ID", Integer.class, this.ingreso_id == null ? null : this.ingreso_id));
        LISTA_SP_IN.add(new SPDataIN("IN_PROCESO_VENTA_ID", Integer.class, this.proceso_venta_id == null ? null : this.proceso_venta_id));
        LISTA_SP_IN.add(new SPDataIN("IN_HABILITADO", Integer.class, this.habilitado == null ? null : this.habilitado));
        return LISTA_SP_IN;
    }
}
