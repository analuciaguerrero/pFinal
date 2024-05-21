package com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaDoblementeEnlazadaTest {
    @Test
    public void testIsVacia() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        assertTrue(lista.isVacia());
        lista.add(5);
        assertFalse(lista.isVacia());
    }

    @Test
    public void testVaciar() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        assertFalse(lista.isVacia());
        lista.vaciar();
        assertTrue(lista.isVacia());
    }

    @Test
    public void testAdd() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        assertEquals(5, lista.getPrimero().getData());
        assertEquals(5, lista.getUltimo().getData());
        lista.add(10);
        assertEquals(5, lista.getPrimero().getData());
        assertEquals(10, lista.getUltimo().getData());
    }

    @Test
    public void testInsert() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.insert(5, 0);
        assertEquals(5, lista.getPrimero().getData());
        assertEquals(5, lista.getUltimo().getData());
        lista.insert(10, 0);
        assertEquals(10, lista.getPrimero().getData());
        assertEquals(5, lista.getUltimo().getData());
        lista.insert(15, 1);
        assertEquals(10, lista.getPrimero().getData());
        assertEquals(15, lista.getUltimo().getData());
    }

    @Test
    public void testDel() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        lista.del(1);
        assertEquals(2, lista.getNumeroElementos());
        assertEquals(15, lista.getUltimo().getData());
        lista.del(0);
        assertEquals(1, lista.getNumeroElementos());
        assertEquals(15, lista.getPrimero().getData());
    }

    @Test
    public void testGetNumeroElementos() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        assertEquals(0, lista.getNumeroElementos());
        lista.add(5);
        assertEquals(1, lista.getNumeroElementos());
        lista.add(10);
        lista.add(15);
        assertEquals(3, lista.getNumeroElementos());
    }

    @Test
    public void testGetPosicion() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        assertEquals(0, lista.getPosicion(lista.getPrimero()));
        assertEquals(1, lista.getPosicion(lista.getPrimero().getSiguiente()));
        assertEquals(2, lista.getPosicion(lista.getUltimo()));
    }

    @Test
    public void testGetPrimero() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        assertNull(lista.getPrimero());
        lista.add(5);
        assertEquals(5, lista.getPrimero().getData());
    }

    @Test
    public void testGetUltimo() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        assertNull(lista.getUltimo());
        lista.add(5);
        assertEquals(5, lista.getUltimo().getData());
    }

    @Test
    public void testGetSiguiente() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        assertEquals(10, lista.getSiguiente(lista.getPrimero()).getData());
    }

    @Test
    public void testGetElemento() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        assertEquals(5, lista.getElemento(0).getData());
        assertEquals(10, lista.getElemento(1).getData());
    }

    @Test
    public void testSetElemento() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.setElemento(1, 15);
        assertEquals(15, lista.getElemento(1).getData());
    }

    @Test
    public void testToString() {
        ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<>();
        lista.add(5);
        lista.add(10);
        assertEquals("[5, 10]", lista.toString());
    }
}