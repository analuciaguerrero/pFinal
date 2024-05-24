package com.example.demoJavafx.tablero;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CeldaPropertiesTest {
    private ListaEnlazada<Celda> originalList;
    private ListaEnlazada<Celda> propertiesList;
    private CeldaProperties celdaProperties;

    @BeforeEach
    public void setUp() {
        originalList = new ListaEnlazada<>();
        propertiesList = new ListaEnlazada<>();

        // Add some mock Celda objects to the lists
        originalList.add(new Celda());
        originalList.add(new Celda());

        propertiesList.add(new Celda());
        propertiesList.add(new Celda());

        celdaProperties = new CeldaProperties(originalList);
    }

    @Test
    public void testGetOriginal() {
        assertEquals(originalList, celdaProperties.getOriginal());
    }

    @Test
    public void testSetOriginal() {
        ListaEnlazada<Celda> newOriginalList = new ListaEnlazada<>();
        celdaProperties.setOriginal(newOriginalList);
        assertEquals(newOriginalList, celdaProperties.getOriginal());
    }

    @Test
    public void testGetProperties() {
        celdaProperties.setProperties(propertiesList);
        assertEquals(propertiesList, celdaProperties.getProperties());
    }

    @Test
    public void testSetProperties() {
        ListaEnlazada<Celda> newPropertiesList = new ListaEnlazada<>();
        celdaProperties.setProperties(newPropertiesList);
        assertEquals(newPropertiesList, celdaProperties.getProperties());
    }

    @Test
    public void testCommit() {
        celdaProperties.setProperties(propertiesList);
        celdaProperties.commit();
        assertEquals(propertiesList, celdaProperties.getOriginal());
    }

    @Test
    public void testRollback() {
        celdaProperties.setProperties(propertiesList);
        celdaProperties.rollback();
        assertEquals(originalList, celdaProperties.getProperties());
    }
}