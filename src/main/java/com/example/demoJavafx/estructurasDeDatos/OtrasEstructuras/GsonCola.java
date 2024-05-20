package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class GsonCola implements JsonSerializer<Cola>, JsonDeserializer<Cola> {
    private static final Logger log = LogManager.getLogger();

    @Override
    public JsonElement serialize(Cola src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getDatos());
    }

    @Override
    public Cola deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Cola<?> cola = new Cola<>();
        cola.setDatos(context.deserialize(json, ListaEnlazada.class));
        return cola;
    }
}
