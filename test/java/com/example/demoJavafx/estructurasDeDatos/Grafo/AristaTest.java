package com.example.demoJavafx.estructurasDeDatos.Grafo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AristaTest {
    private Arista<String> arista;
    private NodoGrafo<String> nodoIni;
    private NodoGrafo<String> nodoFin;

    @BeforeEach
    void setUp() {
        nodoIni = new NodoGrafo<>("Inicio");
        nodoFin = new NodoGrafo<>("Fin");
        arista = new Arista<>(10.0, nodoIni, nodoFin, "Anotacion", true);
    }

    @Test
    void testConstructorSinParametros() {
        Arista<String> aristaSinParametros = new Arista<>();
        assertNull(aristaSinParametros.getNodoIni());
        assertNull(aristaSinParametros.getNodoFin());
        assertNull(aristaSinParametros.getAnotacion());
        assertEquals(0.0, aristaSinParametros.getPeso());
        assertFalse(aristaSinParametros.isDirigido());
    }

    @Test
    void testConstructorConPeso() {
        Arista<String> aristaConPeso = new Arista<>(5.0);
        assertEquals(5.0, aristaConPeso.getPeso());
    }

    @Test
    void testConstructorConAnotacion() {
        Arista<String> aristaConAnotacion = new Arista<>("Nota");
        assertEquals("Nota", aristaConAnotacion.getAnotacion());
    }

    @Test
    void testConstructorConParametros() {
        Arista<String> aristaConParametros = new Arista<>(20.0, nodoIni, nodoFin, "Nota", false);
        assertEquals(20.0, aristaConParametros.getPeso());
        assertEquals(nodoIni, aristaConParametros.getNodoIni());
        assertEquals(nodoFin, aristaConParametros.getNodoFin());
        assertEquals("Nota", aristaConParametros.getAnotacion());
        assertFalse(aristaConParametros.isDirigido());
    }

    @Test
    void testIsDirigido() {
        assertTrue(arista.isDirigido());
    }

    @Test
    void testSetDirigido() {
        arista.setDirigido(false);
        assertFalse(arista.isDirigido());
    }

    @Test
    void testGetNodoIni() {
        assertEquals(nodoIni, arista.getNodoIni());
    }

    @Test
    void testSetNodoIni() {
        NodoGrafo<String> nuevoNodoIni = new NodoGrafo<>("NuevoInicio");
        arista.setNodoIni(nuevoNodoIni);
        assertEquals(nuevoNodoIni, arista.getNodoIni());
    }

    @Test
    void testSetNodoIniNull() {
        arista.setNodoIni(null);
        assertEquals(nodoIni, arista.getNodoIni());
    }

    @Test
    void testGetNodoFin() {
        assertEquals(nodoFin, arista.getNodoFin());
    }

    @Test
    void testSetNodoFin() {
        NodoGrafo<String> nuevoNodoFin = new NodoGrafo<>("NuevoFin");
        arista.setNodoFin(nuevoNodoFin);
        assertEquals(nuevoNodoFin, arista.getNodoFin());
    }

    @Test
    void testSetNodoFinNull() {
        arista.setNodoFin(null);
        assertEquals(nodoFin, arista.getNodoFin());
    }

    @Test
    void testGetAnotacion() {
        assertEquals("Anotacion", arista.getAnotacion());
    }

    @Test
    void testSetAnotacion() {
        arista.setAnotacion("NuevaNota");
        assertEquals("NuevaNota", arista.getAnotacion());
    }

    @Test
    void testGetPeso() {
        assertEquals(10.0, arista.getPeso());
    }

    @Test
    void testSetPeso() {
        arista.setPeso(15.0);
        assertEquals(15.0, arista.getPeso());
    }

    @Test
    void testGetVertice() {
        assertEquals(nodoFin, arista.getVertice(nodoIni));
        assertEquals(nodoIni, arista.getVertice(nodoFin));
        assertNull(arista.getVertice(new NodoGrafo<>("Otro")));
    }
}