package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import com.google.gson.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonEstudianteTest {
    private GsonEstudiante gsonEstudiante;
    private Gson gson;

    @BeforeEach
    void setUp() {
        gsonEstudiante = new GsonEstudiante();
        gson = new GsonBuilder()
                .registerTypeAdapter(Estudiante.class, gsonEstudiante)
                .create();
    }

    @Test
    void testSerialize() {
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 100, 50, 30, 1);

        JsonElement jsonElement = gson.toJsonTree(estudiante, Estudiante.class);

        assertTrue(jsonElement.isJsonObject());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertEquals("EstudianteConcreto", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));
    }

    @Test
    void testSerializeWithParents() {
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 100, 50, 30, 1);
        Estudiante padre1 = new EstudianteBasico(2, 0, 0, 1, 100, 50, 30, 1);
        Estudiante padre2 = new EstudianteBasico(3, 0, 0, 1, 100, 50, 30, 1);
        estudiante.setPadres(padre1, padre2);

        JsonElement jsonElement = gson.toJsonTree(estudiante, Estudiante.class);

        assertTrue(jsonElement.isJsonObject());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertEquals("EstudianteBasico", jsonObject.get("tipo").getAsString());
        assertNotNull(jsonObject.get("propiedades"));

        JsonArray padresArray = jsonObject.getAsJsonArray("padres");
        assertNotNull(padresArray);
        assertEquals(2, padresArray.size());
    }

    @Test
    void testDeserialize() {
        String jsonString = "{\"tipo\":\"EstudianteBasico\",\"propiedades\":{\"posicionN\":0,\"posicionM\":0,\"id\":1,\"generacion\":1,\"tiempoDeVida\":100,\"probReproduccion\":50.0,\"probClonacion\":30.0,\"probMuerte\":50.0,\"isVivo\":true,\"colaDeOperaciones\":[],\"padres\":[]}}";

        Estudiante estudiante = gson.fromJson(jsonString, Estudiante.class);

        assertNotNull(estudiante);
        assertEquals(EstudianteBasico.class, estudiante.getClass());
        assertEquals(1, estudiante.getId());
        assertEquals(100, estudiante.getTiempoDeVida());
        assertEquals(50.0, estudiante.getProbReproduccion());
        assertEquals(30.0, estudiante.getProbClonacion());
    }

    @Test
    void testDeserializeWithParents() {
        String jsonString = "{\"tipo\":\"EstudianteBasico\",\"propiedades\":{\"posicionN\":0,\"posicionM\":0,\"id\":1,\"generacion\":1,\"tiempoDeVida\":100,\"probReproduccion\":50.0,\"probClonacion\":30.0,\"probMuerte\":50.0,\"isVivo\":true,\"colaDeOperaciones\":[],\"padres\":[{\"tipo\":\"EstudianteConcreto\",\"propiedades\":{\"posicionN\":0,\"posicionM\":0,\"id\":2,\"generacion\":1,\"tiempoDeVida\":100,\"probReproduccion\":50.0,\"probClonacion\":30.0,\"probMuerte\":50.0,\"isVivo\":true,\"colaDeOperaciones\":[],\"padres\":[]}},{\"tipo\":\"EstudianteConcreto\",\"propiedades\":{\"posicionN\":0,\"posicionM\":0,\"id\":3,\"generacion\":1,\"tiempoDeVida\":100,\"probReproduccion\":50.0,\"probClonacion\":30.0,\"probMuerte\":50.0,\"isVivo\":true,\"colaDeOperaciones\":[],\"padres\":[]}}]}}";

        Estudiante estudiante = gson.fromJson(jsonString, Estudiante.class);

        assertNotNull(estudiante);
        assertEquals(EstudianteBasico.class, estudiante.getClass());
        assertEquals(1, estudiante.getId());

        ListaSimple<Estudiante> padres = estudiante.getPadres();
        assertNotNull(padres);
        assertEquals(2, padres.getNumeroElementos());
        assertEquals(2, padres.getElemento(0).getData().getId());
        assertEquals(3, padres.getElemento(1).getData().getId());
    }
}