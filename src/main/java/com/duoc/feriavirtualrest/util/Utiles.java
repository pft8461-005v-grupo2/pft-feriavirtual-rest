package com.duoc.feriavirtualrest.util;

import com.duoc.feriavirtualrest.model.ResponseSP;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class Utiles {

    public static String objectToJson(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    public static ResponseSP jsonToResponseSP(String data) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, ResponseSP.class);
    }

    public static ResponseSP objectToResponseSP(Object data) throws IOException {
        return jsonToResponseSP(objectToJson(data));
    }

}
