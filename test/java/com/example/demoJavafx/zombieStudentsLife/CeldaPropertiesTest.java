package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.CeldaProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaPropertiesTest {
    @Test
    public void testCommit() {
        // Crear una instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> lista = new ListaEnlazada<>();
        // Agregar una celda a la lista
        lista.add(new Celda());

        // Crear una instancia de CeldaProperties con la lista creada
        CeldaProperties properties = new CeldaProperties(lista);

        // Crear otra instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> otraLista = new ListaEnlazada<>();
        // Agregar una celda a la otra lista
        otraLista.add(new Celda());

        // Establecer la otra lista como la lista properties
        properties.setProperties(otraLista);

        // Realizar el commit
        properties.commit();

        // Verificar si la lista original se ha actualizado a la lista properties
        assertEquals(otraLista, properties.getOriginal());
    }

    @Test
    public void testRollback() {
        // Crear una instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> lista = new ListaEnlazada<>();
        // Agregar una celda a la lista
        lista.add(new Celda());

        // Crear una instancia de CeldaProperties con la lista creada
        CeldaProperties properties = new CeldaProperties(lista);

        // Crear otra instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> otraLista = new ListaEnlazada<>();
        // Agregar una celda a la otra lista
        otraLista.add(new Celda());

        // Establecer la otra lista como la lista properties
        properties.setProperties(otraLista);

        // Realizar el rollback
        properties.rollback();

        // Verificar si la lista properties se ha actualizado a la lista original
        assertEquals(lista, properties.getProperties());
    }
    @Test
    public void testGetOriginal() {
        // Crear una instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> lista = new ListaEnlazada<>();
        // Agregar una celda a la lista
        lista.add(new Celda());

        // Crear una instancia de CeldaProperties con la lista creada
        CeldaProperties properties = new CeldaProperties(lista);

        // Verificar si la lista original es la misma que la lista creada
        assertEquals(lista, properties.getOriginal());
    }

    @Test
    public void testSetOriginal() {
        // Crear una instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> lista = new ListaEnlazada<>();
        // Agregar una celda a la lista
        lista.add(new Celda());

        // Crear una instancia de CeldaProperties
        CeldaProperties properties = new CeldaProperties(lista);

        // Crear otra instancia de ListaEnlazada<Celda>
        ListaEnlazada<Celda> otraLista = new ListaEnlazada<>();
        // Agregar una celda a la otra lista
        otraLista.add(new Celda());

        // Establecer la otra lista como la lista original
        properties.setOriginal(otraLista);

        // Verificar si la lista original es la misma que la otra lista
        assertEquals(otraLista, properties.getOriginal());
    }
}