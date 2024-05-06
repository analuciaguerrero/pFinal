package com.example.demojavafx.estructurasDeDatos.ListaSimple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaSimpleTest {
    @Test
    void copiaLista() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        ListaSimple<Integer> copia = lista.copiaLista();

        assertEquals("[1, 2, 3]", copia.toString());
    }

    @Test
    void getMaximo() {
        ListaSimple<Integer> lista1 = new ListaSimple<>(6);
        assertEquals(10000,lista1.getMaximo());
    }

    @Test
    void setMaximo() {
        ListaSimple<Integer> lista1 = new ListaSimple<>(6);
        lista1.setMaximo(9);
        assertEquals(9,lista1.getMaximo());
    }

    @Test
    void isVacia() {
        ListaSimple<String> lista = new ListaSimple<>();
        assertTrue(lista.isVacia());

        lista.add("elemento");
        assertFalse(lista.isVacia());
    }

    @Test
    void vaciar() {
        ListaSimple<Double> lista = new ListaSimple<>(1.0);
        lista.add(2.0);
        lista.add(3.0);

        lista.vaciar();

        assertTrue(lista.isVacia());
    }

    @Test
    void add() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);

        assertEquals("[1, 2, 3]", lista.toString());
    }

    @Test
    void del() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        lista.del(1);

        assertEquals("[1, 3]", lista.toString());

    }

    @Test
    void getPosicion() {
        ListaSimple<String> lista = new ListaSimple<>("a");
        lista.add("b");
        lista.add("c");

        assertEquals(Integer.valueOf(1), lista.getPosicion("b"));
    }

    @Test
    void getPrimero() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        assertEquals(Integer.valueOf(1), lista.getPrimero().getData());
    }

    @Test
    void getUltimo() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        assertEquals(Integer.valueOf(3), lista.getUltimo().getData());
    }
    @Test
    public void testGetUltimoVacia() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        assertNull(lista.getUltimo());
    }

    @Test
    void getElemento() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        assertEquals(Integer.valueOf(2), lista.getElemento(1).getData());
    }
    @Test
    public void testGetElementoNoExistente() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.add(5);
        assertNull(lista.getElemento(1));
    }

    @Test
    public void testGetElementoPosicionNegativa() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.add(5);
        assertNull(lista.getElemento(-1));
    }

    @Test
    public void testGetElementoPosicionExcedida() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.add(5);
        assertNull(lista.getElemento(2));
    }

    @Test
    void getNumeroElementos() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        assertEquals(3, lista.getNumeroElementos().intValue());
    }

    @Test
    public void testVoltear() {
        ListaSimple<Integer> lista = new ListaSimple<>(1);
        lista.add(2);
        lista.add(3);

        ListaSimple<Integer> volteada = lista.voltear();

        assertEquals("[3, 2, 1]", volteada.toString());
    }

    @Test
    public void testToString() {
        ListaSimple<String> lista = new ListaSimple<>("a");
        lista.add("b");
        lista.add("c");

        assertEquals("[a, b, c]", lista.toString());
    }

    @Test
    public void testGetMaximo() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        assertEquals(Integer.valueOf(10000), lista.getMaximo());
    }

    @Test
    public void testSetMaximo() {
        ListaSimple<Integer> lista = new ListaSimple<>();
        lista.setMaximo(5000);
        assertEquals(Integer.valueOf(5000), lista.getMaximo());
    }
}