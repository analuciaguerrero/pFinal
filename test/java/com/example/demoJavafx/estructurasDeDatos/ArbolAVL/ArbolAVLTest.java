package com.example.demoJavafx.estructurasDeDatos.ArbolAVL;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolAVLTest {
    private ArbolAVL<Integer> arbol;

    @BeforeEach
    public void setUp() {
        arbol = new ArbolAVL<>();
    }

    @Test
    public void testInsertarUnNodo() {
        arbol.add(10);
        assertEquals(10, arbol.getRaiz().getDato());
        assertEquals(0, arbol.getAltura());
    }

    @Test
    public void testInsertarMultiplesNodos() {
        arbol.add(10);
        arbol.add(20);
        arbol.add(5);

        assertEquals(10, arbol.getRaiz().getDato());
        assertEquals(1, arbol.getAltura());

        assertEquals(5, arbol.getRaiz().getIzquierda().getDato());
        assertEquals(20, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testEliminarNodo() {
        arbol.add(10);
        arbol.add(20);
        arbol.add(5);

        arbol.del(10);
        assertEquals(5, arbol.getRaiz().getDato());
        assertEquals(20, arbol.getRaiz().getDerecha().getDato());
        assertNull(arbol.getRaiz().getIzquierda());
    }

    @Test
    public void testBalanceoSimpleIzquierda() {
        arbol.add(30);
        arbol.add(20);
        arbol.add(10);

        assertEquals(20, arbol.getRaiz().getDato());
        assertEquals(10, arbol.getRaiz().getIzquierda().getDato());
        assertEquals(30, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testBalanceoSimpleDerecha() {
        arbol.add(10);
        arbol.add(20);
        arbol.add(30);

        assertEquals(20, arbol.getRaiz().getDato());
        assertEquals(10, arbol.getRaiz().getIzquierda().getDato());
        assertEquals(30, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testBalanceoDobleIzquierdaDerecha() {
        arbol.add(30);
        arbol.add(10);
        arbol.add(20);

        assertEquals(20, arbol.getRaiz().getDato());
        assertEquals(10, arbol.getRaiz().getIzquierda().getDato());
        assertEquals(30, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testBalanceoDobleDerechaIzquierda() {
        arbol.add(10);
        arbol.add(30);
        arbol.add(20);

        assertEquals(20, arbol.getRaiz().getDato());
        assertEquals(10, arbol.getRaiz().getIzquierda().getDato());
        assertEquals(30, arbol.getRaiz().getDerecha().getDato());
    }

    @Test
    public void testPreOrden() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);
        arbol.add(5);
        arbol.add(15);

        ListaDoblementeEnlazada<Integer> preOrden = arbol.preOrden();
        assertEquals("[20, 10, 5, 15, 30]", preOrden.toString());
    }

    @Test
    public void testOrdenCentral() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);
        arbol.add(5);
        arbol.add(15);

        ListaDoblementeEnlazada<Integer> ordenCentral = arbol.ordenCentral();
        assertEquals("[5, 10, 15, 20, 30]", ordenCentral.toString());
    }

    @Test
    public void testPostOrden() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);
        arbol.add(5);
        arbol.add(15);

        ListaDoblementeEnlazada<Integer> postOrden = arbol.postOrden();
        assertEquals("[5, 15, 10, 30, 20]", postOrden.toString());
    }
    @Test
    public void testGetCamino() {
        arbol.add(20);
        arbol.add(10);
        arbol.add(30);
        arbol.add(5);
        arbol.add(15);
        arbol.add(25);
        arbol.add(35);

        // Obtener el camino al nodo con valor 7
        ListaDoblementeEnlazada<NodoAVL<Integer>> caminoNodos = arbol.getcamino(7);
        // Convertir la lista de nodos a una lista de datos
        ListaDoblementeEnlazada<Integer> caminoDatos = new ListaDoblementeEnlazada<>();
        for (ElementoLDE<NodoAVL<Integer>> elemento = caminoNodos.getPrimero(); elemento != null; elemento = elemento.getSiguiente()) {
            caminoDatos.add(elemento.getData().getDato());
        }
        // Crear la lista esperada
        ListaDoblementeEnlazada<Integer> caminoEsperado = new ListaDoblementeEnlazada<>();
        caminoEsperado.add(10);
        caminoEsperado.add(5);
        caminoEsperado.add(7);

        // Comparar el camino obtenido con el esperado
        assertEquals(caminoEsperado.toString(), caminoDatos.toString());
    }
}