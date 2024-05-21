package com.example.demoJavafx.estructurasDeDatos.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaTest {
    @Test
    public void testIsVacia() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertTrue(lista.isVacia());
        lista.add(5);
        assertFalse(lista.isVacia());
    }

    @Test
    public void testVaciar() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.vaciar();
        assertTrue(lista.isVacia());
    }

    @Test
    public void testGetPrimero() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertNull(lista.getPrimero());
        lista.add(5);
        assertNotNull(lista.getPrimero());
        assertEquals(5, lista.getPrimero().getData());
    }

    @Test
    public void testGetUltimo() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertNull(lista.getUltimo());
        lista.add(5);
        assertEquals(5, lista.getUltimo().getData());
        lista.add(10);
        assertEquals(10, lista.getUltimo().getData());
    }

    @Test
    public void testGetElemento() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        assertEquals(5, lista.getElemento(0).getData());
        assertEquals(10, lista.getElemento(1).getData());
        assertEquals(15, lista.getElemento(2).getData());
    }

    @Test
    public void testGetNumeroElementos() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertEquals(0, lista.getNumeroElementos());
        lista.add(5);
        assertEquals(1, lista.getNumeroElementos());
        lista.add(10);
        assertEquals(2, lista.getNumeroElementos());
    }

    @Test
    public void testGetPosicionElementoLE() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        ElementoLE<Integer> elemento = lista.getElemento(1);
        assertEquals(1, lista.getPosicion(elemento));
    }

    @Test
    public void testGetPosicionDato() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        assertEquals(1, lista.getPosicion(10));
    }

    @Test
    public void testGetSiguiente() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        assertEquals(10, lista.getSiguiente(lista.getElemento(0)).getData());
    }

    @Test
    public void testAdd() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        assertEquals(5, lista.getPrimero().getData());
        lista.add(10);
        assertEquals(10, lista.getUltimo().getData());
    }

    @Test
    public void testInsert() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.insert(10, 0);
        assertEquals(10, lista.getPrimero().getData());
        lista.insert(15, 1);
        assertEquals(15, lista.getElemento(1).getData());
    }

    @Test
    public void testDelete() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        assertEquals(2, lista.delete(1));
        assertNull(lista.getElemento(1));
    }

    @Test
    public void testDel() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        lista.del(10);
        assertNull(lista.getElemento(1));
    }

    @Test
    public void testReverse() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        ListaEnlazada<Integer> listaInvertida = lista.reverse(lista);
        assertEquals(15, listaInvertida.getPrimero().getData());
        assertEquals(10, listaInvertida.getElemento(1).getData());
        assertEquals(5, listaInvertida.getUltimo().getData());
    }

    @Test
    public void testToString() {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.add(5);
        lista.add(10);
        lista.add(15);
        assertEquals("[5, 10, 15]", lista.toString());
    }
}