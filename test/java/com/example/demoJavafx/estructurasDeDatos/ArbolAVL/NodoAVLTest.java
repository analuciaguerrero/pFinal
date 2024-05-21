package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NodoAVLTest {
    private NodoAVL<Integer> nodo;

    @BeforeEach
    public void setUp() {
        nodo = new NodoAVL<>();
    }

    @Test
    public void testConstructorDefault() {
        assertNull(nodo.getDato());
        assertNull(nodo.getIzquierda());
        assertNull(nodo.getDerecha());
        assertEquals(0, nodo.getAltura());
    }

    @Test
    public void testConstructorWithDato() {
        NodoAVL<Integer> nodoConDato = new NodoAVL<>(10);
        assertEquals(10, nodoConDato.getDato());
        assertNull(nodoConDato.getIzquierda());
        assertNull(nodoConDato.getDerecha());
        assertEquals(0, nodoConDato.getAltura());
    }

    @Test
    public void testGetAndSetIzquierda() {
        NodoAVL<Integer> nodoIzquierda = new NodoAVL<>(5);
        nodo.setIzquierda(nodoIzquierda);
        assertEquals(nodoIzquierda, nodo.getIzquierda());
    }

    @Test
    public void testGetAndSetDerecha() {
        NodoAVL<Integer> nodoDerecha = new NodoAVL<>(15);
        nodo.setDerecha(nodoDerecha);
        assertEquals(nodoDerecha, nodo.getDerecha());
    }

    @Test
    public void testGetAndSetAltura() {
        nodo.setAltura(3);
        assertEquals(3, nodo.getAltura());
    }

    @Test
    public void testSetDato() {
        nodo.setDato(20);
        assertEquals(20, nodo.getDato());
    }
}