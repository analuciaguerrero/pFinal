package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.excepciones.ClaseErroneaException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonRecursosTest {
    private Gson gson;
    private GsonRecursos gsonRecursos;
    private DatosJuego datosJuego;

    @BeforeEach
    void setUp() {
        gsonRecursos = new GsonRecursos();
        gson = new GsonBuilder()
                .registerTypeAdapter(Recursos.class, gsonRecursos)
                .create();
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
    }

    @Test
    void testSerializeAgua() {
        Agua agua = new Agua(1, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(agua, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Agua", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializeAgua() {
        String json = """
                {
                    "tipo": "Agua",
                    "propiedades": {
                        "id": 1,
                        "posicionN": 0,
                        "posicionM": 0,
                        "aumentoVida": 1.1
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Agua);
        assertEquals(1, recurso.getId());
        assertEquals(1.1, ((Agua) recurso).getAumentoVida());
    }

    @Test
    void testSerializeComida() {
        Comida comida = new Comida(2, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(comida, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Comida", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializeComida() {
        String json = """
                {
                    "tipo": "Comida",
                    "propiedades": {
                        "id": 2,
                        "posicionN": 0,
                        "posicionM": 0,
                        "aumentoVida": 1.2
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Comida);
        assertEquals(2, recurso.getId());
        assertEquals(1.2, ((Comida) recurso).getAumentoVida());
    }

    @Test
    void testSerializeMontaña() {
        Montaña montaña = new Montaña(3, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(montaña, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Montaña", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializeMontaña() {
        String json = """
                {
                    "tipo": "Montaña",
                    "propiedades": {
                        "id": 3,
                        "posicionN": 0,
                        "posicionM": 0,
                        "reduccionVida": 0.8
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Montaña);
        assertEquals(3, recurso.getId());
        assertEquals(0.8, ((Montaña) recurso).getReduccionVida());
    }

    @Test
    void testSerializeBiblioteca() {
        Biblioteca biblioteca = new Biblioteca(4, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(biblioteca, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Biblioteca", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializeBiblioteca() {
        String json = """
                {
                    "tipo": "Biblioteca",
                    "propiedades": {
                        "id": 4,
                        "posicionN": 0,
                        "posicionM": 0,
                        "aumentoProbClonacion": 5.5
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Biblioteca);
        assertEquals(4, recurso.getId());
        assertEquals(5.5, ((Biblioteca) recurso).getAumentoProbClonacion());
    }

    @Test
    void testSerializeTesoro() {
        Tesoro tesoro = new Tesoro(5, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(tesoro, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Tesoro", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializeTesoro() {
        String json = """
                {
                    "tipo": "Tesoro",
                    "propiedades": {
                        "id": 5,
                        "posicionN": 0,
                        "posicionM": 0,
                        "aumentoProbReproduccion": 6.6
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Tesoro);
        assertEquals(5, recurso.getId());
        assertEquals(6.6, ((Tesoro) recurso).getAumentoProbReproduccion());
    }

    @Test
    void testSerializePozo() {
        Pozo pozo = new Pozo(6, datosJuego);
        JsonElement jsonElement = gson.toJsonTree(pozo, Recursos.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        assertEquals("Pozo", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testDeserializePozo() {
        String json = """
                {
                    "tipo": "Pozo",
                    "propiedades": {
                        "id": 6,
                        "posicionN": 0,
                        "posicionM": 0
                    }
                }
                """;
        Recursos recurso = gson.fromJson(json, Recursos.class);
        assertTrue(recurso instanceof Pozo);
        assertEquals(6, recurso.getId());
    }

    @Test
    void testDeserializeInvalidType() {
        String json = """
                {
                    "tipo": "InvalidType",
                    "propiedades": {
                        "id": 7
                    }
                }
                """;
        assertThrows(ClaseErroneaException.class, () -> {
            gson.fromJson(json, Recursos.class);
        });
    }
}