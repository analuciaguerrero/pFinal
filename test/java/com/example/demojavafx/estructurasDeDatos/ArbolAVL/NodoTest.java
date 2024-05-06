package com.example.demojavafx.estructurasDeDatos.ArbolAVL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.demojavafx.excepciones.*;
class NodoTest {
    @Test
    void alturaNodo() {
        Nodo<Integer> nodo1 = new Nodo<>(3);
        nodo1.setNodoDch(new Nodo<>(5));
        nodo1.getNodoDch().setNodoDch(new Nodo<>(7));
        assertEquals(3, nodo1.alturaNodo(nodo1));
    }

    @Test
    void getGrado() {
        Nodo<Integer> nodo1 = new Nodo<>(3);
        assertEquals(0, nodo1.getGrado());
        nodo1.setNodoDch(new Nodo<>(5));
        assertEquals(1, nodo1.getGrado());
        nodo1.setNodoIzq(new Nodo<>(2));
        assertEquals(2, nodo1.getGrado());
    }

    @Test
    void del() {
        Nodo<Integer> nodo1 = new Nodo<>(30);
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
        Nodo<Integer> nodo1 = new Nodo<>(30);
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
        Nodo<Integer> nodo1 = new Nodo<>();
        assertNull(nodo1.getDato());
    }

    @Test
    void setDato() {
        Nodo<String> nodo1 = new Nodo<>();
        nodo1.setDato("Hola");
        assertEquals("Hola", nodo1.getDato());
    }

    @Test
    void getNodoIzq() {
        Nodo<String> nodo1 = new Nodo<>("Hola");
        nodo1.setNodoIzq(new Nodo<>("Adios"));
        assertEquals("Adios", nodo1.getNodoIzq().getDato());
    }

    @Test
    void setNodoIzq() {
        Nodo<String> nodo1 = new Nodo<>("Hola");
        nodo1.setNodoIzq(new Nodo<>("Adios"));
        assertEquals("Adios", nodo1.getNodoIzq().getDato());
    }

    @Test
    void getNodoDch() {
        Nodo<String> nodo1 = new Nodo<>("Hola");
        nodo1.setNodoDch(new Nodo<>("Adios"));
        assertEquals("Adios", nodo1.getNodoDch().getDato());
    }

    @Test
    void setNodoDch() {
        Nodo<String> nodo1 = new Nodo<>("Hola");
        nodo1.setNodoDch(new Nodo<>("Adios"));
        assertEquals("Adios", nodo1.getNodoDch().getDato());
    }
}