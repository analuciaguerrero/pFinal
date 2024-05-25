package com.example.demoJavafx.tablero;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CeldaPropertiesTest {
    private CeldaProperties celdaProperties;
    private ListaEnlazada<Celda> originalList;
    private ListaEnlazada<Celda> propertiesList;

    @BeforeEach
    void setUp() {
        originalList = new ListaEnlazada<>();
        propertiesList = new ListaEnlazada<>();

        Celda celda1 = new Celda(1, 1);
        Celda celda2 = new Celda(2, 2);

        originalList.add(celda1);
        propertiesList.add(celda2);

        celdaProperties = new CeldaProperties(originalList);
    }

    @Test
    void testGetOriginal() {
        assertEquals(originalList, celdaProperties.getOriginal());
    }

    @Test
    void testSetOriginal() {
        ListaEnlazada<Celda> newOriginal = new ListaEnlazada<>();
        Celda celda = new Celda(3, 3);
        newOriginal.add(celda);
        celdaProperties.setOriginal(newOriginal);
        assertEquals(newOriginal, celdaProperties.getOriginal());
    }

    @Test
    void testGetProperties() {
        celdaProperties.setProperties(propertiesList);
        assertEquals(propertiesList, celdaProperties.getProperties());
    }

    @Test
    void testSetProperties() {
        ListaEnlazada<Celda> newProperties = new ListaEnlazada<>();
        Celda celda = new Celda(4, 4);
        newProperties.add(celda);
        celdaProperties.setProperties(newProperties);
        assertEquals(newProperties, celdaProperties.getProperties());
    }

    @Test
    void testCommit() {
        celdaProperties.setProperties(propertiesList);
        celdaProperties.commit();
        assertEquals(propertiesList, celdaProperties.getOriginal());
    }

    @Test
    void testRollback() {
        celdaProperties.setProperties(propertiesList);
        celdaProperties.rollback();
        assertEquals(originalList, celdaProperties.getProperties());
    }
}