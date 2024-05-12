package com.example.demoJavafx.zombieStudentsLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrizPropertiesTest {
    @Test
    public void testConstructor() {
        Matriz matriz = new Matriz(3, 4);
        MatrizProperties properties = new MatrizProperties(matriz);

        assertEquals(matriz, properties.getOriginal());
        assertEquals(matriz.getFilas(), properties.filasProperty().getValue().intValue());
        assertEquals(matriz.getColumnas(), properties.columnasProperty().getValue().intValue());
    }

    @Test
    public void testCommit() {
        Matriz matriz = new Matriz(3, 4);
        MatrizProperties properties = new MatrizProperties(matriz);

        properties.filasProperty().setValue(5);
        properties.columnasProperty().setValue(6);

        properties.commit();

        assertEquals(5, matriz.getFilas());
        assertEquals(6, matriz.getColumnas());
    }

    @Test
    public void testRollback() {
        Matriz matriz = new Matriz(3, 4);
        MatrizProperties properties = new MatrizProperties(matriz);

        properties.filasProperty().setValue(5);
        properties.columnasProperty().setValue(6);

        properties.rollback();

        assertEquals(3, matriz.getFilas());
        assertEquals(4, matriz.getColumnas());
    }
}