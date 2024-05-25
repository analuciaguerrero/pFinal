package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolAVLTest {
    private ArbolAVL<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolAVL<>();
    }

    @Test
    void testConstructorVacio() {
        assertNotNull(arbol);
        assertNull(arbol.getRaiz());
    }

    @Test
    void testConstructorConDato() {
        ArbolAVL<Integer> arbolConDato = new ArbolAVL<>(10);
        assertNotNull(arbolConDato);
        assertEquals(10, arbolConDato.getRaiz().getDato());
    }

    @Test
    void testConstructorConNodo() {
        NodoAVL<Integer> nodo = new NodoAVL<>(10);
        ArbolAVL<Integer> arbolConNodo = new ArbolAVL<>(nodo);
        assertNotNull(arbolConNodo);
        assertEquals(nodo, arbolConNodo.getRaiz());
    }

    @Test
    void testGetRaiz() {
        arbol.add(10);
        assertEquals(10, arbol.getRaiz().getDato());
    }

    @Test
    void testGetSubArbolIzq() {
        arbol.add(10);
        arbol.add(5);
        assertNotNull(arbol.getSubArbolIzq());
        assertEquals(5, arbol.getSubArbolIzq().getRaiz().getDato());
    }

    @Test
    void testGetSubArbolDcha() {
        arbol.add(10);
        arbol.add(15);
        assertNotNull(arbol.getSubArbolDcha());
        assertEquals(15, arbol.getSubArbolDcha().getRaiz().getDato());
    }

    @Test
    void testGetGradoN() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertEquals(2, arbol.getGradoN(arbol.getRaiz()));
    }

    @Test
    void testGetAltura() {
        arbol.add(10);
        assertEquals(0, arbol.getAltura());
        arbol.add(5);
        assertEquals(1, arbol.getAltura());
    }

    @Test
    void testGetAlturaN() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertEquals(1, arbol.getAlturaN(arbol.getRaiz().getIzquierda()));
    }

    @Test
    void testPreOrden() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        ListaDoblementeEnlazada<Integer> preOrden = arbol.preOrden();
        assertEquals("[10, 5, 15]", preOrden.toString());
    }

    @Test
    void testOrdenCentral() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        ListaDoblementeEnlazada<Integer> ordenCentral = arbol.ordenCentral();
        assertEquals("[5, 10, 15]", ordenCentral.toString());
    }

    @Test
    void testPostOrden() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        ListaDoblementeEnlazada<Integer> postOrden = arbol.postOrden();
        assertEquals("[5, 15, 10]", postOrden.toString());
    }

    @Test
    void testAdd() {
        arbol.add(10);
        assertEquals(10, arbol.getRaiz().getDato());
        arbol.add(5);
        assertEquals(5, arbol.getRaiz().getIzquierda().getDato());
        arbol.add(15);
        assertEquals(15, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    void testDel() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.del(5);
        assertNull(arbol.getRaiz().getIzquierda());
    }

    @Test
    void testGetCamino() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        ListaDoblementeEnlazada<NodoAVL<Integer>> camino = arbol.getcamino(5);
        assertNotNull(camino);
        assertEquals(5, camino.getPrimero().getData().getDato());
    }

    @Test
    void testRotarSimpleIzquierda() {
        NodoAVL<Integer> raiz = new NodoAVL<>(10);
        NodoAVL<Integer> izquierda = new NodoAVL<>(5);
        raiz.setIzquierda(izquierda);
        NodoAVL<Integer> nuevaRaiz = arbol.rotar_s(raiz, true);
        assertEquals(5, nuevaRaiz.getDato());
        assertEquals(10, nuevaRaiz.getDerecha().getDato());
    }

    @Test
    void testRotarSimpleDerecha() {
        NodoAVL<Integer> raiz = new NodoAVL<>(10);
        NodoAVL<Integer> derecha = new NodoAVL<>(15);
        raiz.setDerecha(derecha);
        NodoAVL<Integer> nuevaRaiz = arbol.rotar_s(raiz, false);
        assertEquals(15, nuevaRaiz.getDato());
        assertEquals(10, nuevaRaiz.getIzquierda().getDato());
    }

    @Test
    void testRotarDobleIzquierda() {
        NodoAVL<Integer> raiz = new NodoAVL<>(10);
        NodoAVL<Integer> izquierda = new NodoAVL<>(5);
        NodoAVL<Integer> izquierdaDerecha = new NodoAVL<>(8);
        izquierda.setDerecha(izquierdaDerecha);
        raiz.setIzquierda(izquierda);
        NodoAVL<Integer> nuevaRaiz = arbol.rotar_d(raiz, true);
        assertEquals(8, nuevaRaiz.getDato());
    }

    @Test
    void testRotarDobleDerecha() {
        NodoAVL<Integer> raiz = new NodoAVL<>(10);
        NodoAVL<Integer> derecha = new NodoAVL<>(15);
        NodoAVL<Integer> derechaIzquierda = new NodoAVL<>(12);
        derecha.setIzquierda(derechaIzquierda);
        raiz.setDerecha(derecha);
        NodoAVL<Integer> nuevaRaiz = arbol.rotar_d(raiz, false);
        assertEquals(12, nuevaRaiz.getDato());
    }

    @Test
    void testBalanceo() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.balanceo();
        assertEquals(10, arbol.getRaiz().getDato());
    }

    @Test
    void testAddAux() {
        arbol.add(10);
        NodoAVL<Integer> nuevoNodo = new NodoAVL<>(5);
        arbol.addAux(arbol.getRaiz(), nuevoNodo);
        assertEquals(5, arbol.getRaiz().getIzquierda().getDato());
    }

    @Test
    void testDelAux1() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        NodoAVL<Integer> nuevaRaiz = arbol.delAux1(arbol.getRaiz(), 5);
        assertNotNull(nuevaRaiz);
        assertNull(arbol.getRaiz().getIzquierda());
    }

    @Test
    void testDelAux2() {
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        NodoAVL<Integer> nodo = arbol.delAux2(arbol.getRaiz().getDerecha());
        assertNotNull(nodo);
    }
}