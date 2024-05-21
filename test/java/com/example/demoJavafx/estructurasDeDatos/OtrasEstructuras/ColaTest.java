package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaTest {
    @Test
    public void testIsVacia() {
        Cola<Integer> cola = new Cola<>();
        assertTrue(cola.isVacia());
        cola.add(5);
        assertFalse(cola.isVacia());
    }

    @Test
    public void testAdd() {
        Cola<Integer> cola = new Cola<>();
        cola.add(5);
        cola.add(10);
        assertEquals("[5, 10]", cola.toString());
    }

    @Test
    public void testPoll() {
        Cola<Integer> cola = new Cola<>();
        cola.add(5);
        cola.add(10);
        assertEquals(5, cola.poll());
        assertEquals("[10]", cola.toString());
    }

    @Test
    public void testPeek() {
        Cola<Integer> cola = new Cola<>();
        cola.add(5);
        cola.add(10);
        assertEquals(5, cola.peek());
        assertEquals("[5, 10]", cola.toString());
    }

    @Test
    public void testGetNumeroDatos() {
        Cola<Integer> cola = new Cola<>();
        assertEquals(0, cola.getNumeroDatos());
        cola.add(5);
        cola.add(10);
        assertEquals(2, cola.getNumeroDatos());
    }

    @Test
    public void testToString() {
        Cola<Integer> cola = new Cola<>();
        cola.add(5);
        cola.add(10);
        assertEquals("[5, 10]", cola.toString());
    }
}