package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

import com.google.gson.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonListaSimpleTest {
    private GsonListaSimple gsonListaSimple;
    private Gson gson;
    private ListaSimple<ElementoLS> listaSimple;

    @BeforeEach
    void setUp() {
        gsonListaSimple = new GsonListaSimple();
        gson = new GsonBuilder()
                .registerTypeAdapter(ListaSimple.class, gsonListaSimple)
                .create();

        listaSimple = new ListaSimple<>(10);
        ElementoLS elem1 = new ElementoLS(1);
        ElementoLS elem2 = new ElementoLS(2);
        ElementoLS[] elementos = new ElementoLS[]{elem1, elem2};
        listaSimple.setDatos(elementos);
    }

    @Test
    void testSerialize() {
        JsonElement jsonElement = gson.toJsonTree(listaSimple, ListaSimple.class);
        assertTrue(jsonElement.isJsonObject());

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertTrue(jsonObject.has("maximo"));
        assertTrue(jsonObject.has("datos"));

        assertEquals(10, jsonObject.get("maximo").getAsInt());
        assertEquals(2, jsonObject.getAsJsonArray("datos").size());
    }

    @Test
    void testDeserialize() {
        String jsonString = gson.toJson(listaSimple, ListaSimple.class);
        ListaSimple deserializedListaSimple = gson.fromJson(jsonString, ListaSimple.class);

        assertEquals(listaSimple.getMaximo(), deserializedListaSimple.getMaximo());
        assertArrayEquals(listaSimple.getDatos(), deserializedListaSimple.getDatos());
    }

    @Test
    void testSerializeEmptyListaSimple() {
        ListaSimple<ElementoLS> emptyListaSimple = new ListaSimple<>(5);
        JsonElement jsonElement = gson.toJsonTree(emptyListaSimple, ListaSimple.class);

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertTrue(jsonObject.has("maximo"));
        assertTrue(jsonObject.has("datos"));

        assertEquals(5, jsonObject.get("maximo").getAsInt());
        assertEquals(0, jsonObject.getAsJsonArray("datos").size());
    }

    @Test
    void testDeserializeEmptyListaSimple() {
        String jsonString = "{\"maximo\":5,\"datos\":[]}";
        ListaSimple deserializedListaSimple = gson.fromJson(jsonString, ListaSimple.class);

        assertEquals(5, deserializedListaSimple.getMaximo());
        assertEquals(0, deserializedListaSimple.getDatos().length);
    }

    @Test
    void testDeserializeInvalidJson() {
        String jsonString = "{\"maximo\":\"invalid\",\"datos\":[]}";
        assertThrows(JsonSyntaxException.class, () -> {
            gson.fromJson(jsonString, ListaSimple.class);
        });
    }
}