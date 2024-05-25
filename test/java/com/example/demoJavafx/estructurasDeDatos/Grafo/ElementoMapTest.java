package com.example.demoJavafx.estructurasDeDatos.Grafo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoMapTest {
    private ElementoMap<String, Integer> elemento;

    @BeforeEach
    void setUp() {
        elemento = new ElementoMap<>("claveInicial", 10);
    }

    @Test
    void testConstructor() {
        assertEquals("claveInicial", elemento.getClave());
        assertEquals(10, elemento.getDato());
    }

    @Test
    void testGetClave() {
        assertEquals("claveInicial", elemento.getClave());
    }

    @Test
    void testSetClave() {
        elemento.setClave("nuevaClave");
        assertEquals("nuevaClave", elemento.getClave());
    }

    @Test
    void testGetDato() {
        assertEquals(10, elemento.getDato());
    }

    @Test
    void testSetDato() {
        elemento.setDato(20);
        assertEquals(20, elemento.getDato());
    }
}