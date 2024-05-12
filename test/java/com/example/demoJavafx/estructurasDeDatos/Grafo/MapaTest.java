package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapaTest {
    @Test
    public void testIsVacio() {
        // Crear un mapa vacío
        Mapa<String, Integer> mapa = new Mapa<>();

        // Verificar que el mapa esté vacío
        assertTrue(mapa.isVacio());
    }

    @Test
    public void testAddAndGet() {
        // Crear un mapa
        Mapa<String, Integer> mapa = new Mapa<>();

        // Agregar elementos al mapa
        mapa.add("clave1", 10);
        mapa.add("clave2", 20);
        mapa.add("clave3", 30);

        // Verificar que los elementos se agregaron correctamente
        assertEquals(Integer.valueOf(10), mapa.get("clave1"));
        assertEquals(Integer.valueOf(20), mapa.get("clave2"));
        assertEquals(Integer.valueOf(30), mapa.get("clave3"));

        // Verificar que se sobrescribe un elemento existente
        mapa.add("clave1", 100);
        assertEquals(Integer.valueOf(100), mapa.get("clave1"));

        // Verificar que se agrega un nuevo elemento
        mapa.add("clave4", 40);
        assertEquals(Integer.valueOf(40), mapa.get("clave4"));
    }

    @Test
    public void testGetIndices() {
        // Crear un mapa
        Mapa<String, Integer> mapa = new Mapa<>();

        // Agregar elementos al mapa
        mapa.add("clave1", 10);
        mapa.add("clave2", 20);
        mapa.add("clave3", 30);

        // Obtener los índices del mapa
        ListaSimple<String> indices = mapa.getIndices();

        // Verificar que los índices se obtuvieron correctamente
        assertEquals(3, indices.getNumeroElementos());

        // Verificar que los índices son correctos
        assertTrue(indicesContiene(indices, "clave1"));
        assertTrue(indicesContiene(indices, "clave2"));
        assertTrue(indicesContiene(indices, "clave3"));
    }

    private boolean indicesContiene(ListaSimple<String> indices, String clave) {
        for (int i = 0; i < indices.getNumeroElementos(); i++) {
            if (indices.getElemento(i).equals(clave)) {
                return true;
            }
        }
        return false;
    }
}