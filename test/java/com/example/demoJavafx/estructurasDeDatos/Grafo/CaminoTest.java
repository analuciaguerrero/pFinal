package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaminoTest {
    private Camino<String> camino;
    private ListaDoblementeEnlazada<NodoGrafo<String>> listaNodos;
    private NodoGrafo<String> nodo1;
    private NodoGrafo<String> nodo2;
    private NodoGrafo<String> nodo3;

    @BeforeEach
    void setUp() {
        nodo1 = new NodoGrafo<>("Nodo 1");
        nodo2 = new NodoGrafo<>("Nodo 2");
        nodo3 = new NodoGrafo<>("Nodo 3");

        listaNodos = new ListaDoblementeEnlazada<>();
        listaNodos.add(nodo1);
        listaNodos.add(nodo2);
        listaNodos.add(nodo3);

        camino = new Camino<>(listaNodos, 10.0);
    }

    @Test
    void testConstructor() {
        assertEquals(10.0, camino.getPeso());
        assertEquals(listaNodos, camino.getCamino());
    }

    @Test
    void testGetPeso() {
        assertEquals(10.0, camino.getPeso());
    }

    @Test
    void testGetCamino() {
        assertEquals(listaNodos, camino.getCamino());
    }

    @Test
    void testToString() {
        String expected = "Volcado del camino desde [Nodo 1] hasta [Nodo 3] =======\n" +
                "Lista de datos en vértices: [Nodo 1,Nodo 2,Nodo 3] - Coste: 10.0\n";
        assertEquals(expected, camino.toString());
    }
}