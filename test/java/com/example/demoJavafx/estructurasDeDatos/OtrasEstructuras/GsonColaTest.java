package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import com.google.gson.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GsonColaTest {
    @Test
    public void testSerializeDeserializeCola() {
        // Crear una Cola con algunos datos
        Cola<String> cola = new Cola<>();
        cola.add("dato1");
        cola.add("dato2");
        cola.add("dato3");

        // Configurar Gson con nuestro adaptador personalizado
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Cola.class, new GsonCola());
        Gson gson = gsonBuilder.create();

        // Serializar la Cola
        String json = gson.toJson(cola);
        System.out.println("Serialized JSON: " + json);

        // Deserializar la Cola
        Cola deserializedCola = gson.fromJson(json, Cola.class);

        // Verificar que los datos deserializados son correctos
        assertNotNull(deserializedCola);
        assertEquals(cola.getNumeroDatos(), deserializedCola.getNumeroDatos());
        assertEquals(cola.peek(), deserializedCola.peek());
    }
}