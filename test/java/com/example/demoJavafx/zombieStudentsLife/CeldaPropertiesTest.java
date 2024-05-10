package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaPropertiesTest {
    @Test
    void testCommit() {
        // Creamos la lista original y la lista de propiedades
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        ListaEnlazada<Celda> properties = new ListaEnlazada<>();

        // Agregamos algunas celdas a la lista de propiedades
        Celda celda1 = new Celda();
        Celda celda2 = new Celda();
        properties.add(celda1);
        properties.add(celda2);

        // Creamos un objeto CeldaProperties
        CeldaProperties celdaProperties = new CeldaProperties(original);

        // Establecemos las propiedades y luego confirmamos
        celdaProperties.setProperties(properties);
        celdaProperties.commit();

        // Verificamos si la lista original se actualizó correctamente
        assertEquals(properties, original);
    }

    @Test
    void testRollback() {
        // Creamos la lista original y la lista de propiedades
        ListaEnlazada<Celda> original = new ListaEnlazada<>();
        ListaEnlazada<Celda> properties = new ListaEnlazada<>();

        // Agregamos algunas celdas a la lista de propiedades
        Celda celda1 = new Celda();
        Celda celda2 = new Celda();
        properties.add(celda1);
        properties.add(celda2);

        // Creamos un objeto CeldaProperties
        CeldaProperties celdaProperties = new CeldaProperties(original);

        // Establecemos las propiedades, luego realizamos un rollback y verificamos
        celdaProperties.setProperties(properties);
        celdaProperties.rollback();

        // Verificamos si la lista original se mantuvo sin cambios
        assertEquals(original, celdaProperties.getProperties());
    }
}