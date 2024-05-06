package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodoTest {
    @Test
    void getDerecha() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a4 = new Nodo<>(6);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setDerecha(a4));
        assertEquals(a4,a3.getDerecha(), "Los nodos derecha no son iguales");
    }

    @Test
    void setDerecha() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a4 = new Nodo<>(6);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setDerecha(a4));
        assertEquals(a4,a3.getDerecha(), "Los nodos derecha no son iguales");

    }

    @Test
    void getIzquierda() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a4 = new Nodo<>(6);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setIzquierda(a4));
        assertEquals(a4,a3.getIzquierda(), "Los nodos izquierda no son iguales");
    }

    @Test
    void setIzquierda() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a4 = new Nodo<>(6);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setIzquierda(a4));
        assertEquals(a4,a3.getIzquierda(), "Los nodos izquierda no son iguales");
    }

    @Test
    void getDato() {
        Nodo<Integer> nodo = new Nodo<>();
        assertNull(nodo.getDato());
    }

    @Test
    void setDato() {
        Nodo<String> nodo = new Nodo<>();
        nodo.setDato("Hola");
        assertEquals("Hola", nodo.getDato());
    }

    @Test
    void comprarar() {
    }

    @Test
    void gradoNodo() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertEquals(2,a3.gradoNodo(),"Los grados no son iguales");

    }

    @Test
    void esHoja() {
        Nodo<Integer> a1 = new Nodo<>(3);
        Nodo<Integer> a2 = new Nodo<>(4);
        Nodo<Integer> a3 = new Nodo<>(a1,a2,5);
        assertFalse(a3.esHoja());
        Nodo<Integer> a6 = new Nodo<>(9);
        assertTrue(a6.esHoja());
    }

}