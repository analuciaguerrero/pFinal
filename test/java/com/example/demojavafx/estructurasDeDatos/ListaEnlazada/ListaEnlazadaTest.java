package com.example.demojavafx.estructurasDeDatos.ListaEnlazada;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaTest {
    ElementoLE aux = new ElementoLE<String>("a");
    ListaEnlazada lista = new ListaEnlazada(aux);
    ElementoLE c = new ElementoLE("c");
    ElementoLE b = new ElementoLE("b");
    @Test
    void isVacia() {
        assertEquals("a",lista.getEl().getData());
        assertNull(lista.getEl().getSiguiente());
        assertFalse(lista.isVacia());
    }

    @Test
    void vaciar() {
        lista.vaciar();
        assertTrue(lista.isVacia());
    }
    @Test
    void add() {
        lista.add("a");
        lista.add("b");
        assertEquals(3,lista.getNumeroElementos(),"las listas no tienen el mismo numero de elementos");
        ListaEnlazada lista2 = new ListaEnlazada();
        lista2.add("hu");
        assertEquals("hu",lista2.getElemento(0).getData(),"los elementos no son iguales");

    }


    @Test
    void insert() {
        lista.delete(0);
        lista.insert("a",0);
        lista.insert("b",1);
        lista.insert("c",2);
        assertEquals("a",lista.getPrimero().getData());
        assertEquals("b",lista.getElemento(1).getData());
        assertEquals("c",lista.getElemento(2).getData());

    }

    @Test
    void testInsert() {
        Object a = "a";
        Object b = "b";
        Object c = "c";
        ElementoLE z = new ElementoLE<>("z");
        ListaEnlazada lista = new ListaEnlazada(z);
        lista.insert(b,1);
        lista.insert(c,2);
        assertEquals("b",lista.getElemento(1).getData());
        assertEquals("c",lista.getElemento(2).getData());
    }

    @Test
    void delete() {
        lista.insert("a",0);
        lista.insert("b",1);
        lista.insert("c",2);
        lista.delete(2);
        assertNull(lista.getElemento(3));
    }

    @Test
    void getNumeroElementos() {
        lista.delete(0);
        assertEquals(0,lista.getNumeroElementos());
        lista.add("a");
        lista.add("a");
        lista.add("b");
        assertEquals(3,lista.getNumeroElementos(),"las listas no tienen el mismo numero de elementos");
        //hay tres elementos porque iniciamos la lista metiendo ya un elemento
    }

    @Test
    void getPosicion() {
        lista.delete(0);
        assertEquals(-1,lista.getPosicion(aux));
        lista.add("a");
        lista.add(c.getData());
        lista.add(b.getData());
        assertEquals(2,lista.getPosicion(aux));
        assertEquals(1,lista.getPosicion(c));
        assertEquals(0,lista.getPosicion(b));

    }

    @Test
    void getPrimero() {
        lista.add("a");
        assertEquals("a",lista.getPrimero().getData());
        lista.add("b");
        assertEquals("b",lista.getPrimero().getData());

    }

    @Test
    void getUltimo() {
        lista.add("c");
        lista.add("d");
        assertEquals("a",lista.getUltimo().getData());
    }

    @Test
    void getSiguiente() {
        ElementoLE el = new ElementoLE<>(null);
        assertNull(lista.getSiguiente(el));
        assertNull(lista.getSiguiente(aux));
        lista.add(b.getData());
        lista.add(c.getData());
        lista.add(b.getData());
        lista.add(c.getData());
        assertEquals("c",lista.getSiguiente(b).getData());

    }

    @Test
    void testGetElemento() {
        lista.delete(0);
        assertNull(lista.getElemento(1));
        lista.add("a");
        lista.add("c");
        lista.add("b");
        assertEquals("b",lista.getElemento(0).getData(), "Los elementos no son iguales");
        assertEquals("c",lista.getElemento(1).getData(), "Los elementos no son iguales");
        assertEquals("a",lista.getElemento(2).getData(), "Los elementos no son iguales");
        lista.add("e");
        lista.add("f");
        lista.add("g");
        lista.delete(2);
        assertEquals("g",lista.getElemento(0).getData(), "Los elementos no son iguales");
        assertEquals("f",lista.getElemento(1).getData(), "Los elementos no son iguales");
        assertEquals("b",lista.getElemento(2).getData(), "Los elementos no son iguales");
        assertEquals("c",lista.getElemento(3).getData(), "Los elementos no son iguales");
        assertEquals("a",lista.getElemento(4).getData(), "Los elementos no son iguales");
        lista.delete(0);
        assertEquals("f",lista.getElemento(0).getData(), "Los elementos no son iguales");
        assertEquals("b",lista.getElemento(1).getData(), "Los elementos no son iguales");
        assertEquals("c",lista.getElemento(2).getData(), "Los elementos no son iguales");
        assertEquals("a",lista.getElemento(3).getData(), "Los elementos no son iguales");
        lista.insert("k",2);
        assertEquals("f",lista.getElemento(0).getData(), "Los elementos no son iguales");
        assertEquals("b",lista.getElemento(1).getData(), "Los elementos no son iguales");
        assertEquals("k",lista.getElemento(2).getData(), "Los elementos no son iguales");
        assertEquals("c",lista.getElemento(3).getData(), "Los elementos no son iguales");
        assertEquals("a",lista.getElemento(4).getData(), "Los elementos no son iguales");


    }

    @Test
    void testAdd() {
        Object a = "a";
        Object b = "b";
        Object c = "c";
        lista.add(c);
        lista.add(b);
        lista.add(a);
        assertEquals("a",lista.getElemento(0).getData());
        assertEquals("b",lista.getElemento(1).getData());
        assertEquals("c",lista.getElemento(2).getData());
    }
    @Test
    void invertir(){
        ListaEnlazada lista = new ListaEnlazada<>();
        Object a = "a";
        Object b = "b";
        Object c = "c";
        lista.add(c);
        lista.add(b);
        lista.add(a);
        assertEquals("a",lista.invertir().getElemento(2).getData());
        assertEquals("b",lista.invertir().getElemento(1).getData());
        assertEquals("c",lista.invertir().getElemento(0).getData());
    }
    @Test
    void suma(){
        ListaEnlazada lista = new ListaEnlazada<>();
        Object a = 2;
        Object b = 3;
        Object c = 5;
        lista.add(c);
        lista.add(b);
        lista.add(a);
        assertEquals(10,lista.suma());
    }
}