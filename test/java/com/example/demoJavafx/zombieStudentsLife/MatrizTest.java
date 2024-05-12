package com.example.demoJavafx.zombieStudentsLife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrizTest {
    @Test
    public void testConstructor() {
        int filas = 3;
        int columnas = 4;
        Matriz matriz = new Matriz(filas, columnas);

        assertEquals(filas, matriz.getFilas());
        assertEquals(columnas, matriz.getColumnas());
        assertFalse(matriz.estaOcupada());
        assertNotNull(matriz.getCelda(0, 0));
        assertNotNull(matriz.getCelda(filas - 1, columnas - 1));
    }

    @Test
    public void testInicializarMatriz() {
        int filas = 2;
        int columnas = 3;
        Matriz matriz = new Matriz(filas, columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                assertNotNull(matriz.getCelda(i, j));
            }
        }
    }

    @Test
    public void testGetSetFilas() {
        Matriz matriz = new Matriz(3, 4);

        matriz.setFilas(5);

        assertEquals(5, matriz.getFilas());
    }

    @Test
    public void testGetSetColumnas() {
        Matriz matriz = new Matriz(3, 4);

        matriz.setColumnas(6);

        assertEquals(6, matriz.getColumnas());
    }

    @Test
    public void testGetSetOcupada() {
        Matriz matriz = new Matriz(3, 4);

        matriz.setOcupada(true);

        assertTrue(matriz.estaOcupada());
    }

    @Test
    public void testGetSetCelda() {
        Matriz matriz = new Matriz(3, 4);
        Celda celda = new Celda();

        matriz.setCelda(1, 2, celda);

        assertEquals(celda, matriz.getCelda(1, 2));
    }
}