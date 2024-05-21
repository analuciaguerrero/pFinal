package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLDETest {
    @Test
    public void testInsertarmeEn() {
        ElementoLDE<Integer> elemento1 = new ElementoLDE<>(5);
        ElementoLDE<Integer> elemento2 = new ElementoLDE<>(10);
        elemento1.insertarmeEn(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
        assertEquals(elemento1, elemento2.getAnterior());
    }

    @Test
    public void testGetSiguiente() {
        ElementoLDE<Integer> elemento1 = new ElementoLDE<>(5);
        ElementoLDE<Integer> elemento2 = new ElementoLDE<>(10);
        assertNull(elemento1.getSiguiente());
        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
    }

    @Test
    public void testGetAnterior() {
        ElementoLDE<Integer> elemento1 = new ElementoLDE<>(5);
        ElementoLDE<Integer> elemento2 = new ElementoLDE<>(10);
        assertNull(elemento1.getAnterior());
        elemento1.setAnterior(elemento2);
        assertEquals(elemento2, elemento1.getAnterior());
    }

    @Test
    public void testSetSiguiente() {
        ElementoLDE<Integer> elemento1 = new ElementoLDE<>(5);
        ElementoLDE<Integer> elemento2 = new ElementoLDE<>(10);
        assertNull(elemento1.getSiguiente());
        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());
    }

    @Test
    public void testSetAnterior() {
        ElementoLDE<Integer> elemento1 = new ElementoLDE<>(5);
        ElementoLDE<Integer> elemento2 = new ElementoLDE<>(10);
        assertNull(elemento1.getAnterior());
        elemento1.setAnterior(elemento2);
        assertEquals(elemento2, elemento1.getAnterior());
    }

    @Test
    public void testGetData() {
        ElementoLDE<Integer> elemento = new ElementoLDE<>(5);
        assertEquals(5, elemento.getData());
    }

    @Test
    public void testSetData() {
        ElementoLDE<Integer> elemento = new ElementoLDE<>();
        elemento.setData(10);
        assertEquals(10, elemento.getData());
    }
}