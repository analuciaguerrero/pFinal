package com.example.demoJavafx.estructurasDeDatos.Grafo;

import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ListaDoblementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NodoGrafoTest {
    private NodoGrafo<String> nodo;
    private ListaDoblementeEnlazada<Arista<String>> listaEntrada;
    private ListaDoblementeEnlazada<Arista<String>> listaSalida;

    @BeforeEach
    void setUp() {
        listaEntrada = new ListaDoblementeEnlazada<>();
        listaSalida = new ListaDoblementeEnlazada<>();
        nodo = new NodoGrafo<>("DatoInicial", listaEntrada, listaSalida);
    }

    @Test
    void testConstructorConDato() {
        NodoGrafo<String> nodoConDato = new NodoGrafo<>("NuevoDato");
        assertEquals("NuevoDato", nodoConDato.getDato());
        assertTrue(nodoConDato.getListaEntrada().isVacia());
        assertTrue(nodoConDato.getListaSalida().isVacia());
    }

    @Test
    void testConstructorConDatoYListas() {
        assertEquals("DatoInicial", nodo.getDato());
        assertEquals(listaEntrada, nodo.getListaEntrada());
        assertEquals(listaSalida, nodo.getListaSalida());
    }

    @Test
    void testGetDato() {
        assertEquals("DatoInicial", nodo.getDato());
    }

    @Test
    void testSetDato() {
        nodo.setDato("DatoModificado");
        assertEquals("DatoModificado", nodo.getDato());
    }

    @Test
    void testGetListaEntrada() {
        assertEquals(listaEntrada, nodo.getListaEntrada());
    }

    @Test
    void testSetListaEntrada() {
        ListaDoblementeEnlazada<Arista<String>> nuevaListaEntrada = new ListaDoblementeEnlazada<>();
        nodo.setListaEntrada(nuevaListaEntrada);
        assertEquals(nuevaListaEntrada, nodo.getListaEntrada());
    }

    @Test
    void testGetListaSalida() {
        assertEquals(listaSalida, nodo.getListaSalida());
    }

    @Test
    void testSetListaSalida() {
        ListaDoblementeEnlazada<Arista<String>> nuevaListaSalida = new ListaDoblementeEnlazada<>();
        nodo.setListaSalida(nuevaListaSalida);
        assertEquals(nuevaListaSalida, nodo.getListaSalida());
    }

    @Test
    void testGetAnotacion() {
        nodo.setAnotacion("AnotacionInicial");
        assertEquals("AnotacionInicial", nodo.getAnotacion());
    }

    @Test
    void testSetAnotacion() {
        nodo.setAnotacion("NuevaAnotacion");
        assertEquals("NuevaAnotacion", nodo.getAnotacion());
    }

    @Test
    void testGetPeso() {
        nodo.setPeso(1.5);
        assertEquals(1.5, nodo.getPeso());
    }

    @Test
    void testSetPeso() {
        nodo.setPeso(2.5);
        assertEquals(2.5, nodo.getPeso());
    }
}