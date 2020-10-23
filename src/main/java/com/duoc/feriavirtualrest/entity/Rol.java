package com.duoc.feriavirtualrest.entity;

import com.duoc.feriavirtualrest.constant.SPConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "SP_ROL_CONSULTAR_ID",
                procedureName = SPConstant.TABLE_SPACE + "SP_ROL_CONSULTAR_ID",
                resultClasses = Rol.class,
                parameters = {
                        @StoredProcedureParameter(
                                mode=ParameterMode.IN,
                                name="IN_ID",
                                type=Integer.class),
                        @StoredProcedureParameter(
                                mode=ParameterMode.REF_CURSOR,
                                name="OUT_RESULTADO",
                                type=Class.class)
                })
})
@Entity
public class Rol {

    private int id;
    private String descripcion;
    private String area;

    public Rol() {
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "AREA")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
