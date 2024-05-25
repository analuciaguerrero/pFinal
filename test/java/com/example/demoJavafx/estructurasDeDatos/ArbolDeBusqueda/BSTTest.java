package com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BST<>();
    }

    @Test
    void testConstructorConNodo() {
        Nodo<Integer> nodo = new Nodo<>(10);
        BST<Integer> bstConNodo = new BST<>(nodo);
        assertEquals(10, bstConNodo.getRaiz().getDato());
    }

    @Test
    void testConstructorConDato() {
        BST<Integer> bstConDato = new BST<>(10);
        assertEquals(10, bstConDato.getRaiz().getDato());
    }

    @Test
    void testAddDato() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertEquals(10, bst.getRaiz().getDato());
        assertEquals(5, bst.getRaiz().getIzquierda().getDato());
        assertEquals(15, bst.getRaiz().getDerecha().getDato());
    }

    @Test
    void testAddNodo() {
        Nodo<Integer> nodo = new Nodo<>(10);
        bst.add(nodo);
        assertEquals(10, bst.getRaiz().getDato());
    }

    @Test
    void testGetGradoN() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertEquals(2, bst.getGradoN(bst.getRaiz()));
    }

    @Test
    void testGetGrado() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertEquals(2, bst.getGrado());
    }

    @Test
    void testGetCamino() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        ListaDoblementeEnlazada<Nodo<Integer>> camino = bst.getCamino(5);
        assertNotNull(camino);
        assertEquals(5, camino.getUltimo().getData().getDato());
    }

    @Test
    void testGetSubArbolDcha() {
        bst.add(10);
        bst.add(15);
        BST<Integer> subArbolDcha = bst.getSubArbolDcha();
        assertEquals(15, subArbolDcha.getRaiz().getDato());
    }

    @Test
    void testGetSubArbolIzq() {
        bst.add(10);
        bst.add(5);
        BST<Integer> subArbolIzq = bst.getSubArbolIzq();
        assertEquals(5, subArbolIzq.getRaiz().getDato());
    }

    @Test
    void testPreOrden() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        ListaDoblementeEnlazada<Integer> lista = bst.preOrden();
        assertEquals("[10, 5, 15]", lista.toString());
    }

    @Test
    void testOrdenCentral() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        ListaDoblementeEnlazada<Integer> lista = bst.ordenCentral();
        assertEquals("[5, 10, 15]", lista.toString());
    }

    @Test
    void testPostOrden() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        ListaDoblementeEnlazada<Integer> lista = bst.postOrden();
        assertEquals("[5, 15, 10]", lista.toString());
    }

    @Test
    void testGetAlturaN() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertEquals(1, bst.getAlturaN(bst.getRaiz().getIzquierda()));
    }

    @Test
    void testGetAltura() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertEquals(1, bst.getAltura());
    }

    @Test
    void testGetDatos() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        ListaDoblementeEnlazada<Nodo<Integer>> lista = bst.getDatos(1);
        assertEquals("[10]", lista.toString());
    }

    @Test
    void testIsArbolHomogeneo() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertTrue(bst.isArbolHomogeneo());
    }

    @Test
    void testIsArbolCompleto() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertTrue(bst.isArbolCompleto());
    }

    @Test
    void testIsArbolCasiCompleto() {
        bst.add(10);
        bst.add(5);
        bst.add(15);
        assertTrue(bst.isArbolCasiCompleto());
    }

    @Test
    void testGetRaiz() {
        bst.add(10);
        assertEquals(10, bst.getRaiz().getDato());
    }
}