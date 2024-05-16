package com.example.demoJavafx.entorno;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonRecursosTest {
    @Test
    public void testSerialize() {
        // Preparación de datos de prueba
        Recursos recurso = new Agua(1, 0, 0, 3, 0.5, 10, 0.7);
        GsonRecursos gsonRecursos = new GsonRecursos();

        // Llamada al método a probar
        JsonElement jsonElement = gsonRecursos.serialize(recurso, null, null);

        // Verificación de resultados
        assertNotNull(jsonElement);
        assertTrue(jsonElement.isJsonObject());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertEquals("Agua", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    public void testDeserialize() {
        // Preparación de datos de prueba
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("tipo", "Agua");
        JsonObject propiedades = new JsonObject();
        propiedades.addProperty("aumentoVida", 10);
        jsonObject.add("propiedades", propiedades);
        GsonRecursos gsonRecursos = new GsonRecursos();

        // Llamada al método a probar
        Recursos recurso = gsonRecursos.deserialize(jsonObject, null, null);

        // Verificación de resultados
        assertNotNull(recurso);
        assertEquals(Agua.class, recurso.getClass());
        assertEquals(10, ((Agua) recurso).getAumentoVida());
    }
}