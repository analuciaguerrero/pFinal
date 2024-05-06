package com.example.demojavafx.estructurasDeDatos.ArbolAVL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demojavafx.excepciones.*;
class NodoAVLTest {
    @Test
    void alturaNodo() {
        NodoAVL<Integer> nodo1 = new NodoAVL<>(3);
        nodo1.setNodoDch(new NodoAVL<>(5));
        nodo1.getNodoDch().setNodoDch(new NodoAVL<>(7));
        assertEquals(3, nodo1.alturaNodo(nodo1));
    }

    @Test
    void getGrado() {
        NodoAVL<Integer> nodo1 = new NodoAVL<>(3);
        assertEquals(0, nodo1.getGrado());
        nodo1.setNodoDch(new NodoAVL<>(5));
        assertEquals(1, nodo1.getGrado());
        nodo1.setNodoIzq(new NodoAVL<>(2));
        assertEquals(2, nodo1.getGrado());
    }

    @Test
    void del() {
        NodoAVL<Integer> nodo1 = new NodoAVL<>(30);
        Integer[] listaDatosAñadir = new Integer[]{60, 10, 50, 40, 12, 20, 45, 42, 6, 59, 63};
        int contador1 = 0;
        while (listaDatosAñadir.length > contador1) {
            try {
                nodo1.add(nodo1, listaDatosAñadir[contador1]);
            } catch (DuplicateElement ex) {
                ex.printStackTrace();
            }
            contador1++;
        }

        Integer[] listaDatosBorrar = new Integer[]{5, 65, 30, 42, 6, 59, 63, 20, 12};
        int contador2 = 0;
        while (listaDatosBorrar.length > contador2) {
            try {
                nodo1.del(null, nodo1, listaDatosBorrar[contador2]);
            } catch (NonexistentElement ex) {
                ex.printStackTrace();
            }
            contador2++;
        }
    }

    @Test
    void add() {
        NodoAVL<Integer> nodo1 = new NodoAVL<>(30);
        try {
            nodo1.add(nodo1, 60);
            nodo1.add(nodo1, 10);
            nodo1.add(nodo1, 50);
            nodo1.add(nodo1, 40);
            nodo1.add(nodo1, -10);
            nodo1.add(nodo1, -5);
            nodo1.add(nodo1, 12);
            nodo1.add(nodo1, 13);
            nodo1.add(nodo1, 45);
            nodo1.add(nodo1, 42);
            nodo1.add(nodo1, 60);
        } catch (DuplicateElement ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void getDato() {
        NodoAVL<Integer> nodo1 = new NodoAVL<>();
        assertNull(nodo1.getDato());
    }

    @Test
    void setDato() {
        NodoAVL<String> nodo1 = new NodoAVL<>();
        nodo1.setDato("Hola");
        assertEquals("Hola", nodo1.getDato());
    }

    @Test
    void getNodoIzq() {
        NodoAVL<String> nodo1 = new NodoAVL<>("Hola");
        nodo1.setNodoIzq(new NodoAVL<>("Adios"));
        assertEquals("Adios", nodo1.getNodoIzq().getDato());
    }

    @Test
    void setNodoIzq() {
        NodoAVL<String> nodo1 = new NodoAVL<>("Hola");
        nodo1.setNodoIzq(new NodoAVL<>("Adios"));
        assertEquals("Adios", nodo1.getNodoIzq().getDato());
    }

    @Test
    void getNodoDch() {
        NodoAVL<String> nodo1 = new NodoAVL<>("Hola");
        nodo1.setNodoDch(new NodoAVL<>("Adios"));
        assertEquals("Adios", nodo1.getNodoDch().getDato());
    }

    @Test
    void setNodoDch() {
        NodoAVL<String> nodo1 = new NodoAVL<>("Hola");
        nodo1.setNodoDch(new NodoAVL<>("Adios"));
        assertEquals("Adios", nodo1.getNodoDch().getDato());
    }
}