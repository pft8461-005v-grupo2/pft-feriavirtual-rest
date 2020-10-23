package com.duoc.feriavirtualrest.util;

public class SPDataIN {

    private String SP_IN_PARAMETER;
    private Class<?> SP_IN_CLASS;
    private Object SP_IN_DATA;

    public SPDataIN() {
    }

    public SPDataIN(String SP_IN_PARAMETER, Class<?> SP_IN_CLASS, Object SP_IN_DATA) {
        this.SP_IN_PARAMETER = SP_IN_PARAMETER;
        this.SP_IN_CLASS = SP_IN_CLASS;
        this.SP_IN_DATA = SP_IN_DATA;
    }

    public String getSP_IN_PARAMETER() {
        return SP_IN_PARAMETER;
    }

    public Class<?> getSP_IN_CLASS() {
        return SP_IN_CLASS;
    }

    public Object getSP_IN_DATA() {
        return SP_IN_DATA;
    }

}
