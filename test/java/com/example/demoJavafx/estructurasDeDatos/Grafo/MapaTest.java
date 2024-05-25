package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapaTest {
    private Mapa<String, Integer> mapa;
    private ListaDoblementeEnlazada<ElementoMap<String, Integer>> listaDoblementeEnlazada;

    @BeforeEach
    void setUp() {
        listaDoblementeEnlazada = new ListaDoblementeEnlazada<>();
        mapa = new Mapa<>(listaDoblementeEnlazada);
    }

    @Test
    void testConstructorVacio() {
        Mapa<String, Integer> mapaVacio = new Mapa<>();
        assertTrue(mapaVacio.isVacio());
    }

    @Test
    void testIsVacio() {
        assertTrue(mapa.isVacio());
        mapa.put("clave1", 1);
        assertFalse(mapa.isVacio());
    }

    @Test
    void testGet() {
        mapa.put("clave1", 1);
        assertEquals(1, mapa.get("clave1"));
        assertNull(mapa.get("claveInexistente"));
    }

    @Test
    void testSetClave() {
        mapa.put("clave1", 1);
        mapa.put("clave2", 2);
        ListaDoblementeEnlazada<String> claves = mapa.SetClave();
        assertEquals(2, claves.getNumeroElementos());
        assertEquals("clave1", claves.getElemento(0).getData());
        assertEquals("clave2", claves.getElemento(1).getData());
    }

    @Test
    void testPut() {
        mapa.put("clave1", 1);
        assertEquals(1, mapa.get("clave1"));

        mapa.put("clave1", 2);
        assertEquals(2, mapa.get("clave1"));

        mapa.put("clave2", 3);
        assertEquals(3, mapa.get("clave2"));
    }

    @Test
    void testEliminar() {
        mapa.put("clave1", 1);
        mapa.put("clave2", 2);
        mapa.eliminar("clave1");
        assertNull(mapa.get("clave1"));
        assertEquals(2, mapa.get("clave2"));

        mapa.eliminar("claveInexistente");  // No debería lanzar excepción
    }
}