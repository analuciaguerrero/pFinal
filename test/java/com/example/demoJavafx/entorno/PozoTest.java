package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {
    @Test
    public void testConstructor1() {
        Pozo pozo = new Pozo(1, 0, 0, 3, 0.5, 0.7);
        assertEquals(0.7, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testConstructor2() {
        Pozo pozo = new Pozo(2, 1, 1, new DatosJuego());
        assertEquals(0.0, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testSetProbPozo() {
        Pozo pozo = new Pozo();
        pozo.setProbPozo(0.8);
        assertEquals(0.8, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testGetTipo() {
        Pozo pozo = new Pozo();
        assertEquals(Pozo.class, pozo.getTipo());
    }

    @Test
    public void testAplicarEfecto() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        };
        Celda celda = new Celda();
        Pozo pozo = new Pozo(1, 0, 0, 3, 0.5, 0.7);
        pozo.aplicarEfecto(estudiante, celda);
        assertTrue(celda.getListaEstudiantes().isVacia()); // Debería eliminar al estudiante
    }
}