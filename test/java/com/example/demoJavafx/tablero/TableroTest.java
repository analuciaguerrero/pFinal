package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {
    @Test
    public void testConstructorDatosJuego() {
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);
        Tablero tablero = new Tablero(datosJuego);

        assertNotNull(tablero);
        assertNotNull(tablero.getCeldas());
        assertEquals(datosJuego.getFilasDelTablero(), tablero.getFilas());
        assertEquals(datosJuego.getColumnasDelTablero(), tablero.getColumnas());
    }

    @Test
    public void testConstructorFilasColumnasDatosJuego() {
        int filas = 5;
        int columnas = 5;
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);
        Tablero tablero = new Tablero(filas, columnas, datosJuego);

        assertNotNull(tablero);
        assertNotNull(tablero.getCeldas());
        assertEquals(filas, tablero.getFilas());
        assertEquals(columnas, tablero.getColumnas());
    }

    @Test
    public void testGetCelda() {
        int filas = 5;
        int columnas = 5;
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);
        Tablero tablero = new Tablero(filas, columnas, datosJuego);

        Celda celda = tablero.getCelda(0, 0);

        assertNotNull(celda);
        // Asegúrate de realizar más aserciones según sea necesario
    }

    @Test
    public void testSetCelda() {
        int filas = 5;
        int columnas = 5;
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);
        Tablero tablero = new Tablero(filas, columnas, datosJuego);

        Celda nuevaCelda = new Celda(/* Aquí pasas los parámetros necesarios */);
        tablero.setCelda(0, 0, nuevaCelda);

        Celda celda = tablero.getCelda(0, 0);
        assertEquals(nuevaCelda, celda);
    }

    @Test
    public void testCrearTableroAleatorio() {
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);
        Tablero tablero = new Tablero(datosJuego);

        tablero.crearTableroAleatorio();

        // Asegúrate de realizar aserciones según sea necesario
    }
}