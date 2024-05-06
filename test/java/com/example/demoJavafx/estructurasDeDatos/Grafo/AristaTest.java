package com.example.demoJavafx.estructurasDeDatos.Grafo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AristaTest {
    @Test
    void getVerticeIni() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Arista a1 = new Arista(v1,v2);
        assertEquals(v1,a1.getVerticeIni());
    }

    @Test
    void setVerticeIni() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Vertice v3 = new Vertice(7,"hola");
        Arista a1 = new Arista(v1,v2);
        a1.setVerticeIni(v3);
        assertEquals("hola",a1.getVerticeIni().getDato());
    }

    @Test
    void getVerticeFin() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Vertice v3 = new Vertice(7,"hola");
        Arista a1 = new Arista(v1,v2);
        a1.setVerticeFin(v3);
        assertEquals("hola",a1.getVerticeFin().getDato());
    }

    @Test
    void setVerticeFin() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Vertice v3 = new Vertice(7,"hola");
        Arista a1 = new Arista(v1,v2);
        a1.setVerticeFin(v3);
        assertEquals("hola",a1.getVerticeFin().getDato());
    }

    @Test
    void getAnotacion() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Arista a1 = new Arista(v1,v2,"hola",3);
        assertEquals("hola",a1.getAnotacion());
    }

    @Test
    void setAnotacion() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Arista a1 = new Arista(v1,v2,3);
        a1.setAnotacion("Arista1");
        assertEquals("Arista1", a1.getAnotacion());
    }

    @Test
    void getPeso() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Arista a1 = new Arista(v1,v2,3);
        assertEquals(3,a1.getPeso());
    }

    @Test
    void setPeso() {
        Vertice v1 = new Vertice(2,23);
        Vertice v2 = new Vertice(8,89);
        Arista a1 = new Arista(v1,v2,3);
        a1.setPeso(67);
        assertEquals(67,a1.getPeso());
    }

}