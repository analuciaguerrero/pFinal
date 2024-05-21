package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ElementoLSTest {
    private ElementoLS<Integer> elemento;

    @BeforeEach
    public void setUp() {
        elemento = new ElementoLS<>();
    }

    @Test
    public void testConstructorDefault() {
        assertNull(elemento.getData());
    }

    @Test
    public void testConstructorWithDato() {
        ElementoLS<Integer> elementoConDato = new ElementoLS<>(10);
        assertEquals(10, elementoConDato.getData());
    }

    @Test
    public void testGetData() {
        elemento.setData(20);
        assertEquals(20, elemento.getData());
    }

    @Test
    public void testSetData() {
        elemento.setData(30);
        assertEquals(30, elemento.getData());
    }
}