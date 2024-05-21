package com.example.demoJavafx.estructurasDeDatos.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ElementoLETest {
    @Test
    public void testConstructorWithData() {
        Integer dato = 5;
        ElementoLE<Integer> elemento = new ElementoLE<>(dato);
        assertEquals(dato, elemento.getData());
        assertNull(elemento.getSiguiente());
    }

    @Test
    public void testConstructorWithDataAndNext() {
        Integer dato1 = 5;
        Integer dato2 = 10;
        ElementoLE<Integer> siguiente = new ElementoLE<>(dato2);
        ElementoLE<Integer> elemento = new ElementoLE<>(dato1, siguiente);
        assertEquals(dato1, elemento.getData());
        assertEquals(siguiente, elemento.getSiguiente());
    }

    @Test
    public void testSetSiguiente() {
        ElementoLE<Integer> elemento1 = new ElementoLE<>(5);
        ElementoLE<Integer> elemento2 = new ElementoLE<>(10);
        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
    }

    @Test
    public void testInsertarmeEn() {
        ElementoLE<Integer> elemento1 = new ElementoLE<>(5);
        ElementoLE<Integer> elemento2 = new ElementoLE<>(10);
        elemento1.insertarmeEn(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
        assertNull(elemento2.getSiguiente());
    }

    @Test
    public void testGetData() {
        Integer dato = 5;
        ElementoLE<Integer> elemento = new ElementoLE<>(dato);
        assertEquals(dato, elemento.getData());
    }

    @Test
    public void testSetData() {
        ElementoLE<Integer> elemento = new ElementoLE<>();
        Integer dato = 10;
        elemento.setData(dato);
        assertEquals(dato, elemento.getData());
    }
}