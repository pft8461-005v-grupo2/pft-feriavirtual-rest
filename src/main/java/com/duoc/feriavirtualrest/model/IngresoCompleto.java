package com.duoc.feriavirtualrest.model;

import com.duoc.feriavirtualrest.entity.Ingreso;
import com.duoc.feriavirtualrest.entity.Productor;

public class IngresoCompleto {

    private Ingreso ingreso;
    private Productor productor;
    private Integer kilogramosocupados;

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Integer getKilogramosocupados() {
        return kilogramosocupados;
    }

    public void setKilogramosocupados(Integer kilogramosocupados) {
        this.kilogramosocupados = kilogramosocupados;
    }
}
