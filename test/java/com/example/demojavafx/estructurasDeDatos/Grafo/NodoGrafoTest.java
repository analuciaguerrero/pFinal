package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodoGrafoTest {
    @Test
    public void testConstructorConListas() {
        ListaSimple<NodoGrafo<Integer>> listaEntrada = new ListaSimple<>();
        ListaSimple<NodoGrafo<Integer>> listaSalida = new ListaSimple<>();
        NodoGrafo<Integer> nodo = new NodoGrafo<>(5, listaEntrada, listaSalida);
        assertNotNull(nodo);
        assertEquals(5, nodo.getDato());
        assertEquals(listaEntrada, nodo.getListaEntrada());
        assertEquals(listaSalida, nodo.getListaSalida());
    }
    @Test
    void getDato() {
        // Crear un dato de ejemplo
        Integer dato = 10;
        // Crear un nodo con el dato de ejemplo
        NodoGrafo<Integer> nodo = new NodoGrafo<>(dato);
        // Verificar que el dato obtenido sea igual al dato de ejemplo
        assertEquals(dato, nodo.getDato());
    }

    @Test
    void setDato() {
        // Crear un nodo
        NodoGrafo<Integer> nodo = new NodoGrafo<>(null);
        // Establecer un dato de ejemplo
        Integer dato = 20;
        // Establecer el dato en el nodo
        nodo.setDato(dato);
        // Verificar que el dato del nodo sea igual al dato establecido
        assertEquals(dato, nodo.getDato());
    }

    @Test
    void getListaEntrada() {
        // Crear un nodo con un dato de ejemplo y una lista de entrada
        NodoGrafo<Integer> nodo = new NodoGrafo<>(null);
        // Verificar que la lista de entrada no sea nula
        assertNotNull(nodo.getListaEntrada());
    }

    @Test
    void setListaEntrada() {
        // Crear un nodo
        NodoGrafo<Integer> nodo = new NodoGrafo<>(null);
        // Crear una lista de entrada de ejemplo
        ListaSimple<NodoGrafo<Integer>> listaEntrada = new ListaSimple<>();
        // Establecer la lista de entrada en el nodo
        nodo.setListaEntrada(listaEntrada);
        // Verificar que la lista de entrada del nodo sea igual a la lista establecida
        assertEquals(listaEntrada, nodo.getListaEntrada());
    }

    @Test
    void getListaSalida() {
        // Crear un nodo de prueba
        NodoGrafo<String> nodo = new NodoGrafo<>("Nodo de prueba");

        // Crear una lista simple para la lista de salida
        ListaSimple<NodoGrafo<String>> listaSalida = new ListaSimple<>();

        // Añadir algunos nodos a la lista de salida
        NodoGrafo<String> nodoSalida1 = new NodoGrafo<>("Nodo de salida 1");
        NodoGrafo<String> nodoSalida2 = new NodoGrafo<>("Nodo de salida 2");
        listaSalida.add(nodoSalida1);
        listaSalida.add(nodoSalida2);

        // Establecer la lista de salida en el nodo de prueba
        nodo.setListaSalida(listaSalida);

        // Obtener la lista de salida del nodo
        ListaSimple<NodoGrafo<String>> listaSalidaObtenida = nodo.getListaSalida();

        // Verificar si la lista de salida obtenida es la misma que la lista de salida de prueba
        assertEquals(listaSalida, listaSalidaObtenida);
    }

    @Test
    void setListaSalida() {
        // Crear un nodo
        NodoGrafo<Integer> nodo = new NodoGrafo<>(null);
        // Crear una lista de salida de ejemplo
        ListaSimple<NodoGrafo<Integer>> listaSalida = new ListaSimple<>();
        // Establecer la lista de salida en el nodo
        nodo.setListaSalida(listaSalida);
        // Verificar que la lista de salida del nodo sea igual a la lista establecida
        assertEquals(listaSalida, nodo.getListaSalida());
    }

}