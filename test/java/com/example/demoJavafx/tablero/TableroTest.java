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
        datosJuego = new DatosJuego(10, 0.5, 0.3, 0.2, 0.1,
                0.6, 5, 0.4, 0.7, 0.8,
                0.9, 0.1, 0.2, 15, 20,
                25, 0.5, 0.3, 10, 10, 1);
        tablero = new Tablero(datosJuego);
    }

    @Test
    public void testConstructorDatosJuego() {
        assertEquals(10, tablero.getFilas());
        assertEquals(10, tablero.getColumnas());
        assertNotNull(tablero.getCeldas());
    }

    @Test
    public void testConstructorFilaColumna() {
        Tablero tablero = new Tablero(5, 5, datosJuego);
        assertEquals(5, tablero.getFilas());
        assertEquals(5, tablero.getColumnas());
        assertNotNull(tablero.getCeldas());
    }

    @Test
    public void testInicializarTablero() {
        Tablero tablero = new Tablero(datosJuego);
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j< tablero.getColumnas(); j++) {
                assertNotNull(tablero.getCelda(i, j));
            }
        }
    }

    @Test
    public void testGetCelda() {
        Celda celda = tablero.getCelda(0, 0);
        assertNotNull(celda);
        assertEquals(0, celda.getPosicionN());
        assertEquals(0, celda.getPosicionM());
    }

    @Test
    public void testSetCelda() {
        Celda nuevaCelda = new Celda(0, 0, datosJuego, tablero);
        tablero.setCelda(0, 0, nuevaCelda);
        assertEquals(nuevaCelda, tablero.getCelda(0, 0));
    }

    @Test
    public void testGetSetFilas() {
        tablero.setFilas(20);
        assertEquals(20, tablero.getFilas());
    }

    @Test
    public void testGetSetColumnas() {
        tablero.setColumnas(15);
        assertEquals(15, tablero.getColumnas());
    }

    @Test
    public void testGetSetDato() {
        DatosJuego nuevoDato = new DatosJuego();
        tablero.setDato(nuevoDato);
        assertEquals(nuevoDato, tablero.getDato());
    }

    @Test
    public void testCrearTableroAleatorio() {
        tablero.crearTableroAleatorio();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Celda celda = tablero.getCelda(i, j);
                if (!celda.getListaEstudiantes().isVacia()) {
                    assertNotNull(celda.getListaEstudiantes().getPrimero().getData());
                }
                if (!celda.getListaRecursos().isVacia()) {
                    assertNotNull(celda.getListaRecursos().getPrimero().getData());
                }
            }
        }
    }
}