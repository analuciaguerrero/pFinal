package com.example.demojavafx.estructurasDeDatos.OtrasEstructuras;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;
import com.example.demojavafx.estructurasDeDatos.Grafo.*;
import static org.junit.jupiter.api.Assertions.*;

class DuplaTest {
    @Test
    void testGetClave() {
        Vertice v1 = new Vertice(34, "Hola");
        ListaSimple<Vertice> listaVertices = new ListaSimple<>(v1);
        Dupla<ListaSimple<Vertice>, Integer> dupla = new Dupla<>(listaVertices, 6);

        assertEquals(listaVertices, dupla.getClave());
    }

    @Test
    void testSetClave() {
        Vertice v1 = new Vertice(34, "Hola");
        Vertice v2 = new Vertice(2, 35);
        ListaSimple<Vertice> listaVertices = new ListaSimple<>(v1);
        Dupla<ListaSimple<Vertice>, Integer> dupla = new Dupla<>(listaVertices, 6);

        ListaSimple<Vertice> nuevaListaVertices = new ListaSimple<>(v2);
        dupla.setClave(nuevaListaVertices);

        assertEquals(nuevaListaVertices, dupla.getClave());
    }

    @Test
    void testGetDato() {
        Vertice v1 = new Vertice(34, "Hola");
        ListaSimple<Vertice> listaVertices = new ListaSimple<>(v1);
        Dupla<ListaSimple<Vertice>, Integer> dupla = new Dupla<>(listaVertices, 6);

        assertEquals(Integer.valueOf(6), dupla.getDato());
    }

    @Test
    void testSetDato() {
        Vertice v1 = new Vertice(34, "Hola");
        ListaSimple<Vertice> listaVertices = new ListaSimple<>(v1);
        Dupla<ListaSimple<Vertice>, Integer> dupla = new Dupla<>(listaVertices, 6);

        dupla.setDato(10);

        assertEquals(Integer.valueOf(10), dupla.getDato());
    }

}