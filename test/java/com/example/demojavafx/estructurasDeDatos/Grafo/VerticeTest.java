package com.example.demojavafx.estructurasDeDatos.Grafo;

import com.example.demojavafx.estructurasDeDatos.ListaSimple.ListaSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerticeTest {
    @Test
    void getVerticesHijos() {
        Vertice v1 = new Vertice(34, "Hola");
        Vertice v2 = new Vertice(2, 35);
        ListaSimple<Vertice> listaVertices = new ListaSimple<>(v2);
        //v1.setVerticesHijos(listaVertices);
        //assertEquals(v2, v1.getVerticesHijos().getElemento(0).getData());
    }

    @Test
    void getStringVerticesHijos() {
        Vertice v1= new Vertice(34,"Hola");
        Vertice v2= new Vertice(2,35);
        Vertice v3= new Vertice(4,"Adios");
        ListaSimple<Vertice> listaVertices=new ListaSimple<>(v2);
        listaVertices.add(v3);
        //v1.setVerticesHijos(listaVertices);
        //assertEquals("[35, Adios].",v1.getStringVerticesHijos());
    }

    @Test
    void setVerticesHijos() {
        Vertice v1= new Vertice(34,"Hola");
        Vertice v2= new Vertice(2,35);
        Vertice v3= new Vertice(4,"Adios");
        ListaSimple<Vertice> listaVertices=new ListaSimple<>(v2);
        listaVertices.add(v3);
        //v1.setVerticesHijos(listaVertices);
        //assertNotNull(v1.getVerticesHijos());
        //assertEquals(2,v1.getVerticesHijos().getNumeroElementos());
    }

    @Test
    void getVerticesAntecesores() {
        Vertice v1= new Vertice(34,"Hola");
        Vertice v2= new Vertice(2,35);
        ListaSimple<Vertice> listaVertices= new ListaSimple<>(v2);
        //v1.setVerticesAntecesores(listaVertices);
        //assertEquals(1,v1.getVerticesAntecesores().getNumeroElementos());
    }

    @Test
    void setVerticesAntecesores() {
        Vertice v1= new Vertice(34,"Hola");
        Vertice v2= new Vertice(2,35);
        ListaSimple<Vertice> listaVertices= new ListaSimple<>(v2);
        //v1.setVerticesAntecesores(listaVertices);
        //assertEquals(1,v1.getVerticesAntecesores().getNumeroElementos());
        // assertNotNull(v1.getVerticesAntecesores());
    }

    @Test
    void getDato() {
        Vertice v1 = new Vertice(8,"Adios");
        assertEquals("Adios", v1.getDato());
    }

    @Test
    void setDato() {
        Vertice v1 = new Vertice(1,1);
        v1.setDato(23);
        assertEquals(23,v1.getDato());

    }

    @Test
    void getID() {
        Vertice v1 = new Vertice(1,1);
        assertEquals(1,v1.getID());
    }

    @Test
    void setID() {
        Vertice v1 = new Vertice(1,1);
        v1.setID(89);
        assertEquals(89,v1.getID());
    }
    @Test
    public void testSetAristasVAntecesores() {
        // Crear un vertice y algunas aristas de ejemplo
        Vertice<String> vertice = new Vertice<>(1, "dato");
        Arista arista1 = new Arista();
        Arista arista2 = new Arista();

        // Crear una lista de aristas antecesoras y establecerla en el vertice
        ListaSimple<Arista> listaAristas = new ListaSimple<>();
        listaAristas.add(arista1);
        listaAristas.add(arista2);
        vertice.setAristasVAntecesores(listaAristas);

        // Verificar si la lista establecida es igual a la lista original
        assertEquals(listaAristas, vertice.getAristasVAntecesores());
    }

    @Test
    public void testSetAristasVAntecesoresNull() {
        // Crear un vertice con una lista de aristas antecesoras preestablecida
        Vertice<String> vertice = new Vertice<>(1, "dato");
        Arista arista1 = new Arista();
        vertice.addAristaVAntecesor(arista1);

        // Establecer una lista nula de aristas antecesoras en el vertice
        vertice.setAristasVAntecesores(null);

        // Verificar si la lista de aristas antecesoras en el vertice sigue siendo nula
        assertNull(vertice.getAristasVAntecesores());
    }
    @Test
    public void testSetAristasVHijos() {
        // Crear un vertice y algunas aristas de ejemplo
        Vertice<String> vertice = new Vertice<>(1, "dato");
        Arista arista1 = new Arista();
        Arista arista2 = new Arista();

        // Crear una lista de aristas hijos y establecerla en el vertice
        ListaSimple<Arista> listaAristas = new ListaSimple<>();
        listaAristas.add(arista1);
        listaAristas.add(arista2);
        vertice.setAristasVHijos(listaAristas);

        // Verificar si los elementos de la lista establecida son iguales a los elementos de la lista original
        ListaSimple<Arista> aristasVHijos = vertice.getAristasVHijos();
        for (int i = 0; i < listaAristas.getNumeroElementos(); i++) {
            assertEquals(listaAristas.getElemento(i), aristasVHijos.getElemento(i));
        }
        assertEquals(listaAristas.isVacia(), aristasVHijos.isVacia());
    }

    @Test
    public void testSetAristasVHijosNull() {
        // Crear un vertice con una lista de aristas hijos preestablecida
        Vertice<String> vertice = new Vertice<>(1, "dato");
        Arista arista1 = new Arista();
        vertice.addAristaVHijo(arista1);

        // Establecer una lista nula de aristas hijos en el vertice
        vertice.setAristasVHijos(null);

        // Verificar si la lista de aristas hijos en el vertice sigue siendo nula
        assertNull(vertice.getAristasVHijos());
    }

}