package com.example.demojavafx.estructurasDeDatos.ArbolDeBusqueda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodoTest {
    @Test
    void getDerecha() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a4 = new Nodo<>(6);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setDerecha(a4));
        assertEquals(a4,a3.getDerecha(), "Los nodos derecha no son iguales");
    }

    @Test
    void setDerecha() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a4 = new Nodo<>(6);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setDerecha(a4));
        assertEquals(a4,a3.getDerecha(), "Los nodos derecha no son iguales");

    }

    @Test
    void getIzquierda() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a4 = new Nodo<>(6);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setIzquierda(a4));
        assertEquals(a4,a3.getIzquierda(), "Los nodos izquierda no son iguales");
    }

    @Test
    void setIzquierda() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a4 = new Nodo<>(6);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertDoesNotThrow(()->a3.setIzquierda(a4));
        assertEquals(a4,a3.getIzquierda(), "Los nodos izquierda no son iguales");
    }

    @Test
    void getDato() {
        Nodo a = new Nodo<>(3);
        Nodo b = new Nodo<>(4);
        assertDoesNotThrow(()->a.setDato(b));
        assertEquals(b,a.getDato(), "Los datos no son iguales");
    }

    @Test
    void setDato() {
        Nodo a = new Nodo<>(3);
        Nodo b = new Nodo<>(4);
        assertDoesNotThrow(()->a.setDato(b));
        assertEquals(b,a.getDato(), "Los datos no son iguales");

    }

    @Test
    void comprarar() {
    }

    @Test
    void gradoNodo() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertEquals(2,a3.gradoNodo(),"Los grados no son iguales");

    }

    @Test
    void esHoja() {
        Nodo a1 = new Nodo<>(3);
        Nodo a2 = new Nodo<>(4);
        Nodo a3 = new Nodo<>(a1,a2,5);
        assertFalse(a3.esHoja());
        Nodo a6 = new Nodo<>(9);
        assertTrue(a6.esHoja());
    }

}