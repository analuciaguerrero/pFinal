package com.example.demoJavafx.estudiante;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonEstudianteTest {
    @Test
    public void testSerializeEstudianteBasico() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        EstudianteBasico estudiante = new EstudianteBasico(1, 0, 5, 50, 20);
        String json = gson.toJson(estudiante);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        assertEquals("EstudianteBasico", jsonObject.get("tipo").getAsString());

        JsonObject propiedades = jsonObject.getAsJsonObject("propiedades");
        assertEquals(1, propiedades.get("id").getAsInt());
        assertEquals(0, propiedades.get("generacion").getAsInt());
        assertEquals(5, propiedades.get("tiempoDeVida").getAsInt());
        assertEquals(50, propiedades.get("probReproduccion").getAsDouble(), 0.0);
        assertEquals(20, propiedades.get("probClonacion").getAsDouble(), 0.0);
    }

    @Test
    public void testDeserializeEstudianteBasico() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        String json = """
            {
                "tipo": "EstudianteBasico",
                "propiedades": {
                    "id": 1,
                    "generacion": 0,
                    "tiempoDeVida": 5,
                    "probReproduccion": 50.0,
                    "probClonacion": 20.0
                }
            }
            """;

        Estudiante estudiante = gson.fromJson(json, Estudiante.class);
        assertTrue(estudiante instanceof EstudianteBasico);
        assertEquals(1, estudiante.getId());
        assertEquals(0, estudiante.getGeneracion());
        assertEquals(5, estudiante.getTiempoDeVida());
        assertEquals(50.0, estudiante.getProbReproduccion(), 0.0);
        assertEquals(20.0, estudiante.getProbClonacion(), 0.0);
    }

    @Test
    public void testSerializeEstudianteNormal() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        EstudianteNormal estudiante = new EstudianteNormal(2, 1, 10, 60, 30);
        String json = gson.toJson(estudiante);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        assertEquals("EstudianteNormal", jsonObject.get("tipo").getAsString());

        JsonObject propiedades = jsonObject.getAsJsonObject("propiedades");
        assertEquals(2, propiedades.get("id").getAsInt());
        assertEquals(1, propiedades.get("generacion").getAsInt());
        assertEquals(10, propiedades.get("tiempoDeVida").getAsInt());
        assertEquals(60, propiedades.get("probReproduccion").getAsDouble(), 0.0);
        assertEquals(30, propiedades.get("probClonacion").getAsDouble(), 0.0);
    }

    @Test
    public void testDeserializeEstudianteNormal() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        String json = """
            {
                "tipo": "EstudianteNormal",
                "propiedades": {
                    "id": 2,
                    "generacion": 1,
                    "tiempoDeVida": 10,
                    "probReproduccion": 60.0,
                    "probClonacion": 30.0
                }
            }
            """;

        Estudiante estudiante = gson.fromJson(json, Estudiante.class);
        assertTrue(estudiante instanceof EstudianteNormal);
        assertEquals(2, estudiante.getId());
        assertEquals(1, estudiante.getGeneracion());
        assertEquals(10, estudiante.getTiempoDeVida());
        assertEquals(60.0, estudiante.getProbReproduccion(), 0.0);
        assertEquals(30.0, estudiante.getProbClonacion(), 0.0);
    }

    @Test
    public void testSerializeEstudianteAvanzado() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        EstudianteAvanzado estudiante = new EstudianteAvanzado(3, 2, 15, 70, 40);
        String json = gson.toJson(estudiante);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        assertEquals("EstudianteAvanzado", jsonObject.get("tipo").getAsString());

        JsonObject propiedades = jsonObject.getAsJsonObject("propiedades");
        assertEquals(3, propiedades.get("id").getAsInt());
        assertEquals(2, propiedades.get("generacion").getAsInt());
        assertEquals(15, propiedades.get("tiempoDeVida").getAsInt());
        assertEquals(70, propiedades.get("probReproduccion").getAsDouble(), 0.0);
        assertEquals(40, propiedades.get("probClonacion").getAsDouble(), 0.0);
    }

    @Test
    public void testDeserializeEstudianteAvanzado() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Estudiante.class, new GsonEstudiante());
        Gson gson = gsonBuilder.create();

        String json = """
            {
                "tipo": "EstudianteAvanzado",
                "propiedades": {
                    "id": 3,
                    "generacion": 2,
                    "tiempoDeVida": 15,
                    "probReproduccion": 70.0,
                    "probClonacion": 40.0
                }
            }
            """;

        Estudiante estudiante = gson.fromJson(json, Estudiante.class);
        assertTrue(estudiante instanceof EstudianteAvanzado);
        assertEquals(3, estudiante.getId());
        assertEquals(2, estudiante.getGeneracion());
        assertEquals(15, estudiante.getTiempoDeVida());
        assertEquals(70.0, estudiante.getProbReproduccion(), 0.0);
        assertEquals(40.0, estudiante.getProbClonacion(), 0.0);
    }
}