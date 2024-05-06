package com.example.demojavafx.estructurasDeDatos.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLETest {
    @Test
    void setSiguiente() {
        ElementoLE<Integer> elemento1 = new ElementoLE<>(1);
        ElementoLE<Integer> elemento2 = new ElementoLE<>(2);

        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());

        elemento1.setSiguiente(null);
        assertNull(elemento1.getSiguiente());
    }

    @Test
    void insertarmeEn() {
        ElementoLE<Integer> elemento1 = new ElementoLE<>(1);
        ElementoLE<Integer> elemento2 = new ElementoLE<>(2);
        ElementoLE<Integer> elemento3 = new ElementoLE<>(3);

        elemento1.insertarmeEn(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());

        elemento1.insertarmeEn(elemento3);
        assertEquals(elemento3, elemento1.getSiguiente());
        assertEquals(elemento2, elemento3.getSiguiente());
    }

    @Test
    void getSiguiente() {
        ElementoLE<Integer> elemento1 = new ElementoLE<>(1);
        ElementoLE<Integer> elemento2 = new ElementoLE<>(2);

        elemento1.setSiguiente(elemento2);
        assertEquals(elemento2, elemento1.getSiguiente());

        elemento1.setSiguiente(null);
        assertNull(elemento1.getSiguiente());
    }

    @Test
    void getData() {
        ElementoLE<String> elemento = new ElementoLE<>("Hola");
        assertEquals("Hola", elemento.getData());

        elemento.setData("Adiós");
        assertEquals("Adiós", elemento.getData());
    }

    @Test
    void setData() {
        ElementoLE<Integer> elemento = new ElementoLE<>(42);
        assertEquals(Integer.valueOf(42), elemento.getData());

        elemento.setData(99);
        assertEquals(Integer.valueOf(99), elemento.getData());
    }

}