package com.example.demojavafx.estructurasDeDatos.ArbolDeBusqueda;

import org.junit.jupiter.api.Test;
import com.example.demojavafx.estructurasDeDatos.ListaEnlazada.*;
import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    Nodo<Integer> uno = new Nodo<>(1);
    Nodo<Integer> dos = new Nodo<>(2);
    Nodo<Integer> tres = new Nodo<>(3);
    Nodo<Integer> cuatro = new Nodo<>(4);
    Nodo<Integer> cinco = new Nodo<>(5);
    BST<Integer> arbol = new BST<>(dos);
    BST<Integer> arbol1 = new BST<>(cinco);
    Nodo<String> e = new Nodo<>("e");
    BST<String> arbol2 = new BST<>(e);
    @Test
    void isVacia() {
        BST<Integer> arbolNulo = new BST<>();
        assertTrue(arbolNulo.isVacia());
        arbolNulo.add(5);
        arbolNulo.preorden();

    }

    @Test
    void add() {
        arbol.add(1,arbol.raiz);
        arbol.add(3,arbol.raiz);
        arbol.add(4,arbol.raiz);
        assertEquals(2,arbol.getCamino(uno).getPrimero().getData());
        assertEquals(1,arbol.getCamino(uno).getUltimo().getData());
        assertEquals(2,arbol.getCamino(tres).getPrimero().getData());
        assertEquals(3,arbol.getCamino(tres).getUltimo().getData());
        assertEquals(4,arbol.getCamino(cuatro).getUltimo().getData());
    }

    @Test
    void getGrado() {
        arbol.add(1,arbol.raiz);
        assertEquals(1,arbol.getGrado(dos,0));
        arbol.add(3,arbol.raiz);
        arbol.add(4,arbol.raiz);
        assertEquals(2,arbol.getGrado(dos,0));
    }

    @Test
    void getCamino() {
        arbol.add(1,arbol.raiz);
        arbol.add(3,arbol.raiz);
        arbol.add(4,arbol.raiz);
        assertEquals(2,arbol.getCamino(uno).getPrimero().getData());
        assertEquals(1,arbol.getCamino(uno).getUltimo().getData());
        assertEquals(2,arbol.getCamino(tres).getPrimero().getData());
        assertEquals(3,arbol.getCamino(tres).getUltimo().getData());
        assertEquals(4,arbol.getCamino(cuatro).getUltimo().getData());
        assertEquals(3,arbol.getCamino(cuatro).getNumeroElementos());
        arbol1.add(7,cinco);
        arbol1.add(3,cinco);
        arbol1.add(2,cinco);
        arbol1.add(4,cinco);
        arbol1.add(8,cinco);
        arbol1.add(6,cinco);
        System.out.println(arbol1.getCamino(dos).getNumeroElementos());
        System.out.println(arbol1.getCamino(dos).getPrimero().getData());
        System.out.println(arbol1.getCamino(dos).getUltimo().getData());

    }

    @Test
    void getSubArbolDcha() {
        arbol1.add(7,cinco);
        arbol1.add(3,cinco);
        arbol1.add(2,cinco);
        arbol1.add(4,cinco);
        arbol1.add(8,cinco);
        arbol1.add(6,cinco);
        ListaEnlazada<Integer> lista = arbol1.getSubArbolDcha().preorden();
        assertEquals(7,lista.getElemento(0).getData());
        assertEquals(6,lista.getElemento(1).getData());
        assertEquals(8,lista.getElemento(2).getData());
    }

    @Test
    void getSubArbolIzq() {
        arbol1.add(7,cinco);
        arbol1.add(3,cinco);
        arbol1.add(2,cinco);
        arbol1.add(4,cinco);
        arbol1.add(8,cinco);
        arbol1.add(6,cinco);
        ListaEnlazada<Integer> lista = arbol1.getSubArbolIzq().preorden();
        assertEquals(3,lista.getElemento(0).getData());
        assertEquals(2,lista.getElemento(1).getData());
        assertEquals(4,lista.getElemento(2).getData());
    }

    @Test
    void preorden() {
        arbol1.add(7,cinco);
        arbol1.add(3,cinco);
        arbol1.add(2,cinco);
        arbol1.add(4,cinco);
        arbol1.add(8,cinco);
        arbol1.add(6,cinco);
        ListaEnlazada<Integer> lista = arbol1.preorden();
        assertEquals(5,lista.getElemento(0).getData());
        assertEquals(3,lista.getElemento(1).getData());
        assertEquals(2,lista.getElemento(2).getData());
        assertEquals(4,lista.getElemento(3).getData());
        assertEquals(7,lista.getElemento(4).getData());
        assertEquals(6,lista.getElemento(5).getData());
        assertEquals(8,lista.getElemento(6).getData());
    }

    @Test
    void ordenCentral() {
        arbol1.add(7,cinco);
        arbol1.add(3,cinco);
        arbol1.add(2,cinco);
        arbol1.add(4,cinco);
        arbol1.add(8,cinco);
        arbol1.add(6,cinco);
        ListaEnlazada<Integer> lista1 = arbol1.ordenCentral();
        assertEquals(2,lista1.getElemento(0).getData());
        assertEquals(3,lista1.getElemento(1).getData());
        assertEquals(4,lista1.getElemento(2).getData());
        assertEquals(5,lista1.getElemento(3).getData());
        assertEquals(6,lista1.getElemento(4).getData());
        assertEquals(7,lista1.getElemento(5).getData());
        assertEquals(8,lista1.getElemento(6).getData());
    }

    @Test
    void postOrder() {
        arbol2.add("g");
        arbol2.add("c");
        arbol2.add("b");
        arbol2.add("d");
        arbol2.add("h");
        arbol2.add("f");
        ListaEnlazada<String> lista = arbol2.postOrder();
        assertEquals("b",lista.getElemento(0).getData());
        assertEquals("d",lista.getElemento(1).getData());
        assertEquals("c",lista.getElemento(2).getData());
        assertEquals("f",lista.getElemento(3).getData());
        assertEquals("h",lista.getElemento(4).getData());
        assertEquals("g",lista.getElemento(5).getData());
        assertEquals("e",lista.getElemento(6).getData());
    }

    @Test
    void getAltura() {
        arbol1.add(7);
        arbol1.add(3);
        arbol1.add(2);
        assertEquals(3,arbol1.getAltura());
        arbol1.add(4);
        arbol1.add(8);
        arbol1.add(6);
        assertEquals(3,arbol1.getAltura());
        BST <Integer> a1 = new BST<>();
        a1.add(7);
        a1.add(8);
        a1.add(6);

        assertEquals(2,a1.getAltura());

    }

    @Test
    void isArbolHomogeneo(){
        arbol1.add(7);
        arbol1.add(3);
        arbol1.add(2);
        arbol1.add(4);
        arbol1.add(8);
        assertFalse(arbol1.isArbolHomogeneo());
        arbol1.add(6);
        assertTrue(arbol1.isArbolHomogeneo());

    }

    @Test
    void isArbolCompleto() {
        arbol1.add(7);
        arbol1.add(3);
        arbol1.add(2);
        arbol1.add(4);
        assertFalse(arbol1.isArbolCompleto());
        arbol1.add(8);
        arbol1.add(6);
        assertTrue(arbol1.isArbolCompleto());
    }

    @Test
    void getListaDatosNivel() {
        arbol1.add(7);
        arbol1.add(3);
        arbol1.add(2);
        arbol1.add(4);
        arbol1.add(8);
        arbol1.add(6);
        assertEquals(3,arbol1.getListaDatosNivel(2).getElemento(0).getData());
        assertEquals(7,arbol1.getListaDatosNivel(2).getElemento(1).getData());
        assertEquals(2,arbol1.getListaDatosNivel(3).getElemento(0).getData());
        assertEquals(4,arbol1.getListaDatosNivel(3).getElemento(1).getData());
        assertEquals(6,arbol1.getListaDatosNivel(3).getElemento(2).getData());
        assertEquals(8,arbol1.getListaDatosNivel(3).getElemento(3).getData());
    }

    @Test
    void isArbolCasiCompleto() {
        arbol1.add(7);
        arbol1.add(3);
        assertFalse(arbol1.isArbolCasiCompleto());
        arbol1.add(2);
        arbol1.add(4);
        assertTrue(arbol1.isArbolCasiCompleto());
        arbol1.add(8);
        arbol1.add(6);
    }

    @Test
    void getLongitud(){
        Nodo<Integer> n1 = new Nodo<>(7);
        Nodo<Integer> n2 = new Nodo<>(3);
        Nodo<Integer> n3 = new Nodo<>(6);
        BST<Integer> arbol3 = new BST<>(cinco,n1,n2);
        arbol3.add(2);
        arbol3.add(4);
        arbol3.add(8);
        arbol3.add(6);
        assertEquals(2,arbol3.getLongitud(n3));
        assertEquals(1,arbol3.getLongitud(n2));
        assertEquals(0,arbol3.getLongitud(cinco));
        BST<Integer> a = new BST<>(2);
        assertTrue(a.isArbolCompleto());
    }
}