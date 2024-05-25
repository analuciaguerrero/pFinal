package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {
    private Grafo<String> grafo;

    @BeforeEach
    void setUp() {
        grafo = new Grafo<>();
    }

    @Test
    void testConstructorVacio() {
        Grafo<String> grafoVacio = new Grafo<>();
        assertTrue(grafoVacio.getNodos().isVacia());
        assertTrue(grafoVacio.getAristas().isVacia());
    }

    @Test
    void testConstructorConParametros() {
        ListaDoblementeEnlazada<NodoGrafo<String>> nodos = new ListaDoblementeEnlazada<>();
        ListaDoblementeEnlazada<Arista<String>> aristas = new ListaDoblementeEnlazada<>();
        Grafo<String> grafoConParametros = new Grafo<>(nodos, aristas);
        assertEquals(nodos, grafoConParametros.getNodos());
        assertEquals(aristas, grafoConParametros.getAristas());
    }

    @Test
    void testIsDirigido() {
        assertFalse(grafo.isDirigido());
        grafo.setDirigido(true);
        assertTrue(grafo.isDirigido());
    }

    @Test
    void testAddNodo() {
        NodoGrafo<String> nodo = new NodoGrafo<>("A");
        grafo.addNodo(nodo);
        assertEquals(nodo, grafo.getNodos().getPrimero().getData());
    }

    @Test
    void testAddNodoConDato() {
        grafo.addNodo("A");
        assertEquals("A", grafo.getNodos().getPrimero().getData().getDato());
    }

    @Test
    void testAddNodoConArista() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("A");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("B");
        grafo.addNodo(nodo1, nodo2, 1.0);
        assertEquals(nodo1, grafo.getNodos().getPrimero().getData());
        assertEquals(nodo2, grafo.getNodos().getUltimo().getData());
        assertEquals(1.0, grafo.getAristas().getPrimero().getData().getPeso());
    }

    @Test
    void testAddAristaConNodos() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("A");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("B");
        grafo.addNodo(nodo1);
        grafo.addNodo(nodo2);
        grafo.addArista(1.0, nodo1, nodo2);
        assertEquals(1.0, grafo.getAristas().getPrimero().getData().getPeso());
    }

    @Test
    void testAddAristaConDatos() {
        grafo.addNodo("A");
        grafo.addNodo("B");
        grafo.addArista(1.0, "A", "B");
        assertEquals(1.0, grafo.getAristas().getPrimero().getData().getPeso());
    }

    @Test
    void testAddAristaConAnotacion() {
        grafo.addNodo("A");
        grafo.addNodo("B");
        grafo.addArista(1.0, "A", "B", "arista1");
        assertEquals("arista1", grafo.getAristas().getPrimero().getData().getAnotacion());
    }

    @Test
    void testDelNodo() {
        grafo.addNodo("A");
        grafo.delNodo("A");
        assertTrue(grafo.getNodos().isVacia());
    }

    @Test
    void testDelArista() {
        grafo.addNodo("A");
        grafo.addNodo("B");
        grafo.addArista(1.0, "A", "B", "arista1");
        grafo.delArista("arista1");
        assertTrue(grafo.getAristas().isVacia());
    }

    @Test
    void testGetNodoGrafo() {
        NodoGrafo<String> nodo = new NodoGrafo<>("A");
        grafo.addNodo(nodo);
        assertEquals(nodo, grafo.getNodoGrafo("A"));
    }

    @Test
    void testGetArista() {
        grafo.addNodo("A");
        grafo.addNodo("B");
        grafo.addArista(1.0, "A", "B", "arista1");
        assertEquals("arista1", grafo.getArista("arista1").getAnotacion());
    }

    @Test
    void testGetCaminoMinimo() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("A");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("B");
        NodoGrafo<String> nodo3 = new NodoGrafo<>("C");
        grafo.addNodo(nodo1);
        grafo.addNodo(nodo2);
        grafo.addNodo(nodo3);
        grafo.addArista(1.0, nodo1, nodo2);
        grafo.addArista(1.0, nodo2, nodo3);
        Camino<String> camino = grafo.getCaminoMinimo(nodo1, nodo3);
        assertEquals(2.0, camino.getPeso());
    }

    @Test
    void testListaToString() {
        NodoGrafo<String> nodo1 = new NodoGrafo<>("A");
        NodoGrafo<String> nodo2 = new NodoGrafo<>("B");
        grafo.addNodo(nodo1);
        grafo.addNodo(nodo2);
        String resultado = grafo.listaToString(grafo.getNodos());
        assertEquals("[A, B]", resultado);
    }
}