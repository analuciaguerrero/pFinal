package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoroTest {
    @Test
    public void testConstructor1() {
        Tesoro tesoro = new Tesoro(1, 0, 0, 3, 0.5, 0.2, 0.7);
        assertEquals(0.2, tesoro.getAumentoProbReproduccion(), 0.001);
        assertEquals(0.7, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testConstructor2() {
        Tesoro tesoro = new Tesoro(2, 1, 1, new DatosJuego());
        assertEquals(0, tesoro.getAumentoProbReproduccion(), 0.001);
        assertEquals(0.0, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testSetAumentoProbReproduccion() {
        Tesoro tesoro = new Tesoro();
        try {
            tesoro.setAumentoProbReproduccion(20);
            assertEquals(20, tesoro.getAumentoProbReproduccion(), 0.001);
        } catch (ProbabilidadNoValida e) {
            fail("No debería lanzar una excepción aquí");
        }
    }

    @Test
    public void testSetAumentoProbReproduccionInvalid() {
        Tesoro tesoro = new Tesoro();
        try {
            tesoro.setAumentoProbReproduccion(-5);
            fail("Debería lanzar una excepción ProbabilidadNoValida");
        } catch (ProbabilidadNoValida e) {
            // Excepción esperada, no hacer nada
        }
    }

    @Test
    public void testSetProbTesoro() {
        Tesoro tesoro = new Tesoro();
        tesoro.setProbTesoro(0.8);
        assertEquals(0.8, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testGetTipo() {
        Tesoro tesoro = new Tesoro();
        assertEquals(Tesoro.class, tesoro.getTipo());
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
        estudiante.setProbReproduccion(50); // Probabilidad inicial
        Celda celda = new Celda();
        Tesoro tesoro = new Tesoro(1, 0, 0, 3, 0.5, 20, 0.7);
        tesoro.aplicarEfecto(estudiante, celda);
        assertEquals(70, estudiante.getProbReproduccion(), 0.001); // Probabilidad aumentada
    }
}