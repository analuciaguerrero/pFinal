package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLDETest {
    private ElementoLDE<Integer> elemento;

    @BeforeEach
    void setUp() {
        elemento = new ElementoLDE<>();
    }

    @Test
    void testConstructorWithAnterior() {
        ElementoLDE<Integer> anterior = new ElementoLDE<>(1);
        ElementoLDE<Integer> newElemento = new ElementoLDE<>(anterior);
        assertEquals(anterior, newElemento.getAnterior());
    }

    @Test
    void testConstructorWithData() {
        Integer data = 5;
        ElementoLDE<Integer> newElemento = new ElementoLDE<>(data);
        assertEquals(data, newElemento.getData());
    }

    @Test
    void testConstructorWithAnteriorSiguienteData() {
        ElementoLDE<Integer> anterior = new ElementoLDE<>(1);
        ElementoLDE<Integer> siguiente = new ElementoLDE<>(3);
        Integer data = 2;
        ElementoLDE<Integer> newElemento = new ElementoLDE<>(anterior, siguiente, data);
        assertEquals(anterior, newElemento.getAnterior());
        assertEquals(siguiente, newElemento.getSiguiente());
        assertEquals(data, newElemento.getData());
    }

    @Test
    void testInsertarmeEn() {
        ElementoLDE<Integer> el = new ElementoLDE<>(1);
        ElementoLDE<Integer> siguiente = new ElementoLDE<>(3);
        el.setSiguiente(siguiente);
        siguiente.setAnterior(el);

        ElementoLDE<Integer> newElemento = new ElementoLDE<>(2);
        newElemento.insertarmeEn(el);

        assertEquals(el, newElemento.getAnterior());
        assertEquals(siguiente, newElemento.getSiguiente());
        assertEquals(newElemento, el.getSiguiente());
        assertEquals(newElemento, siguiente.getAnterior());
    }

    @Test
    void testGetSiguiente() {
        ElementoLDE<Integer> siguiente = new ElementoLDE<>(3);
        elemento.setSiguiente(siguiente);
        assertEquals(siguiente, elemento.getSiguiente());
    }

    @Test
    void testGetAnterior() {
        ElementoLDE<Integer> anterior = new ElementoLDE<>(1);
        elemento.setAnterior(anterior);
        assertEquals(anterior, elemento.getAnterior());
    }

    @Test
    void testSetSiguiente() {
        ElementoLDE<Integer> siguiente = new ElementoLDE<>(3);
        elemento.setSiguiente(siguiente);
        assertEquals(siguiente, elemento.getSiguiente());
    }

    @Test
    void testSetAnterior() {
        ElementoLDE<Integer> anterior = new ElementoLDE<>(1);
        elemento.setAnterior(anterior);
        assertEquals(anterior, elemento.getAnterior());
    }

    @Test
    void testGetData() {
        Integer data = 5;
        elemento.setData(data);
        assertEquals(data, elemento.getData());
    }

    @Test
    void testSetData() {
        Integer data = 5;
        elemento.setData(data);
        assertEquals(data, elemento.getData());
    }
}