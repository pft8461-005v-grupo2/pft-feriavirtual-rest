package com.duoc.feriavirtualrest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "SP_CREAR_USUARIO",
                procedureName = "PORTAFOLIO.SP_CREAR_USUARIO",
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.IN, name="IN_ROL_ID", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CORREO", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="IN_CONTRASENA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_GLOSA", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ESTADO", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="OUT_ID_SALIDA", type=Integer.class)
                })
})
public class Usuario implements Serializable {


    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
