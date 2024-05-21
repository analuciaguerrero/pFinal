package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonListaSimpleTest {
    @Test
    public void testSerializationDeserialization() {
        // Crear una lista simple con algunos elementos
        ListaSimple<Integer> lista = new ListaSimple<>(5);
        lista.add(1);
        lista.add(2);
        lista.add(3);

        // Crear una instancia de GsonListaSimple
        GsonListaSimple gsonListaSimple = new GsonListaSimple();

        // Serializar la lista
        Gson gson = new GsonBuilder().registerTypeAdapter(ListaSimple.class, gsonListaSimple).create();
        String json = gson.toJson(lista);

        // Deserializar el JSON a una nueva lista
        ListaSimple<Integer> listaDeserializada = gson.fromJson(json, ListaSimple.class);

        // Verificar que la lista deserializada sea igual a la lista original
        assertEquals(lista.getNumeroElementos(), listaDeserializada.getNumeroElementos());
        for (int i = 0; i < lista.getNumeroElementos(); i++) {
            assertEquals(lista.getElemento(i).getData(), listaDeserializada.getElemento(i).getData());
        }
    }
}