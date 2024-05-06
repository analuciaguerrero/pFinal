package com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras;

import org.junit.jupiter.api.Test;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.*;
import static org.junit.jupiter.api.Assertions.*;

class ColaTest {
    @Test
    void push() {
        Cola<Integer> cola = new Cola<>();
        cola.push(1);
        assertFalse(cola.esVacia());
    }

    @Test
    void pull() {
        Cola<Integer> cola = new Cola<>();
        cola.push(1);
        cola.push(2);
        assertEquals(1, cola.pull());
        assertEquals(2, cola.pull());
        assertTrue(cola.esVacia());
    }
    @Test
    public void testGetDatos() {
        Cola<Integer> cola = new Cola<>();
        ListaEnlazada<Integer> datos = cola.getDatos();
        assertNotNull(datos);
        assertSame(datos, cola.getDatos()); // Asegura que se devuelva la misma referencia
    }

    @Test
    void esVacia() {
        Cola<Integer> cola = new Cola<>();
        assertTrue(cola.esVacia());
        cola.push(1);
        assertFalse(cola.esVacia());
        cola.pull();
        assertTrue(cola.esVacia());
    }
}