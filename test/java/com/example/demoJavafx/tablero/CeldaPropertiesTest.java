package com.example.demoJavafx.tablero;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.CeldaProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaPropertiesTest {
    @Test
    public void testSetOriginal() {
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        CeldaProperties celdaProperties = new CeldaProperties(new ListaEnlazada<>());

        celdaProperties.setOriginal(original);

        assertEquals(original, celdaProperties.getOriginal());
    }

    @Test
    public void testSetters() {
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        ListaEnlazada<Celda> properties = new ListaEnlazada<>();
        CeldaProperties celdaProperties = new CeldaProperties(original);

        celdaProperties.setProperties(properties);

        assertEquals(properties, celdaProperties.getProperties());
    }

    @Test
    public void testCommit() {
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        ListaEnlazada<Celda> properties = new ListaEnlazada<>();
        CeldaProperties celdaProperties = new CeldaProperties(original);
        celdaProperties.setProperties(properties);

        celdaProperties.commit();

        assertEquals(properties, celdaProperties.getOriginal());
    }

    @Test
    public void testRollback() {
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        ListaEnlazada<Celda> properties = new ListaEnlazada<>();
        CeldaProperties celdaProperties = new CeldaProperties(original);
        celdaProperties.setProperties(properties);

        celdaProperties.rollback();

        assertEquals(original, celdaProperties.getProperties());
    }
}