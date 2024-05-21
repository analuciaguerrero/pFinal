package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaSimpleTest {
    private ListaSimple<Integer> lista;
    private static final int MAXIMO = 10;

    @BeforeEach
    public void setUp() {
        lista = new ListaSimple<>(MAXIMO);
    }

    @Test
    public void testConstructorDefault() {
        ListaSimple<Integer> listaDefault = new ListaSimple<>();
        assertNull(listaDefault.getDatos());
    }

    @Test
    public void testConstructorWithMaximo() {
        assertNotNull(lista.getDatos());
        assertEquals(MAXIMO, lista.getMaximo());
    }

    @Test
    public void testIsVacia() {
        ListaSimple<Integer> listaVacia = new ListaSimple<>();
        assertTrue(listaVacia.isVacia());

        assertFalse(lista.isVacia());
    }

    @Test
    public void testVaciar() {
        lista.add(1);
        lista.vaciar();
        assertNull(lista.getPrimero());
    }

    @Test
    public void testGetMaximo() {
        assertEquals(MAXIMO, lista.getMaximo());
    }

    @Test
    public void testSetMaximo() {
        lista.setMaximo(20);
        assertEquals(20, lista.getMaximo());
    }

    @Test
    public void testGetDatos() {
        ElementoLS<Integer>[] datos = lista.getDatos();
        assertNotNull(datos);
    }

    @Test
    public void testSetDatos() {
        ElementoLS<Integer>[] datos = new ElementoLS[MAXIMO];
        lista.setDatos(datos);
        assertSame(datos, lista.getDatos());
    }

    @Test
    public void testGetPrimero() {
        lista.add(1);
        assertEquals(1, lista.getPrimero().getData());
    }

    @Test
    public void testGetUltimo() {
        lista.add(1);
        lista.add(2);
        assertEquals(2, lista.getUltimo().getData());
    }

    @Test
    public void testGetElemento() {
        lista.add(1);
        assertEquals(1, lista.getElemento(0).getData());
    }

    @Test
    public void testSetElemento() {
        lista.setElemento(0, 1);
        assertEquals(1, lista.getElemento(0).getData());
    }

    @Test
    public void testGetNumeroElementos() {
        assertEquals(0, lista.getNumeroElementos());
        lista.add(1);
        assertEquals(1, lista.getNumeroElementos());
    }

    @Test
    public void testGetSiguiente() {
        lista.add(1);
        lista.add(2);
        ElementoLS<Integer> primero = lista.getPrimero();
        assertEquals(2, lista.getSiguiente(primero).getData());
    }

    @Test
    public void testGetPosicion() {
        lista.add(1);
        lista.add(2);
        ElementoLS<Integer> primero = lista.getPrimero();
        assertEquals(0, lista.getPosicion(primero));
    }

    @Test
    public void testAddElementoLS() {
        ElementoLS<Integer> elemento = new ElementoLS<>(1);
        int pos = lista.add(elemento);
        assertEquals(0, pos);
        assertEquals(1, lista.getElemento(0).getData());
    }

    @Test
    public void testAddTipoDedatos() {
        lista.add(1);
        assertEquals(1, lista.getElemento(0).getData());
    }

    @Test
    public void testInsert() {
        lista.add(1);
        lista.add(3);
        lista.insert(2, 1);
        assertEquals(1, lista.getElemento(0).getData());
        assertEquals(2, lista.getElemento(1).getData());
        assertEquals(3, lista.getElemento(2).getData());
    }

    @Test
    public void testDel() {
        lista.add(1);
        lista.add(2);
        lista.del(0);
        assertEquals(1, lista.getNumeroElementos());
        assertEquals(2, lista.getElemento(0).getData());
    }
}