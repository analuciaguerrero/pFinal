package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {
    private DatosJuego datosJuego;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        datosJuego = new DatosJuego();
        tablero = new Tablero(5, 5, datosJuego);
    }

    @Test
    public void testConstructor() {
        assertNotNull(tablero);
        assertEquals(5, tablero.getNumFilas());
        assertEquals(5, tablero.getNumColumnas());
    }

    @Test
    public void testGetCeldaByPositionArray() {
        int[] posicion = {2, 3};
        Celda celda = tablero.getCelda(posicion);
        assertNotNull(celda);
        assertEquals(2, celda.getPosicionN());
        assertEquals(3, celda.getPosicionM());
    }

    @Test
    public void testGetCeldaByInvalidPositionArray() {
        int[] posicion = {2};
        Celda celda = tablero.getCelda(posicion);
        assertNull(celda);
    }

    @Test
    public void testGetCeldaByRowCol() {
        Celda celda = tablero.getCelda(2, 3);
        assertNotNull(celda);
        assertEquals(2, celda.getPosicionN());
        assertEquals(3, celda.getPosicionM());
    }

    @Test
    public void testSetCelda() {
        Celda nuevaCelda = new Celda(2, 3, datosJuego, tablero);
        tablero.setCelda(2, 3, nuevaCelda);
        Celda celda = tablero.getCelda(2, 3);
        assertEquals(nuevaCelda, celda);
    }

    @Test
    public void testGetNumFilas() {
        assertEquals(5, tablero.getNumFilas());
    }

    @Test
    public void testGetNumColumnas() {
        assertEquals(5, tablero.getNumColumnas());
    }

    @Test
    public void testGetSetDato() {
        DatosJuego nuevoDatos = new DatosJuego();
        tablero.setDato(nuevoDatos);
        assertEquals(nuevoDatos, tablero.getDato());
    }

    @Test
    public void testDatosIniciales() {
        assertEquals(datosJuego, tablero.getDato());
    }

    @Test
    public void testInicializacionCeldas() {
        for (int i = 0; i < tablero.getNumFilas(); i++) {
            for (int j = 0; j < tablero.getNumColumnas(); j++) {
                Celda celda = tablero.getCelda(i, j);
                assertNotNull(celda);
                assertEquals(i, celda.getPosicionN());
                assertEquals(j, celda.getPosicionM());
                assertEquals(datosJuego, celda.getDatos());
                assertEquals(tablero, celda.getTablero());
            }
        }
    }
}