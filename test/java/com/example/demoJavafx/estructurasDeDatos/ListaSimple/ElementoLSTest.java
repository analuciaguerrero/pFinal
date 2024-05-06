package com.example.demoJavafx.estructurasDeDatos.ListaSimple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementoLSTest {
    @Test
    void getData() {
        Integer expectedData = 10;
        ElementoLS<Integer> elemento = new ElementoLS<>(expectedData);
        assertEquals(expectedData, elemento.getData());
    }

    @Test
    void setData() {
        ElementoLS<Integer> elemento = new ElementoLS<>(null);
        Integer expectedData = 20;
        elemento.setData(expectedData);
        assertEquals(expectedData, elemento.getData());
    }
}