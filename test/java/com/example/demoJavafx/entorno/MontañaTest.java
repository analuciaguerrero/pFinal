package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontañaTest {
    @Test
    public void testConstructor1() {
        Montaña montaña = new Montaña(1, 0, 0, 3, 0.5, 5, 0.7);
        assertEquals(5, montaña.getReduccionVida());
        assertEquals(0.7, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testConstructor2() {
        Montaña montaña = new Montaña(2, 1, 1, new DatosJuego());
        assertEquals(0, montaña.getReduccionVida());
        assertEquals(0.0, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testSetReduccionVida() {
        Montaña montaña = new Montaña();
        try {
            montaña.setReduccionVida(8);
            assertEquals(8, montaña.getReduccionVida());
        } catch (IncrementoNoValido e) {
            fail("No debería lanzar una excepción aquí");
        }
    }

    @Test
    public void testSetReduccionVidaNegativo() {
        Montaña montaña = new Montaña();
        try {
            montaña.setReduccionVida(-5);
            fail("Debería lanzar una excepción IncrementoNoValido");
        } catch (IncrementoNoValido e) {
            // Excepción esperada, no hacer nada
        }
    }

    @Test
    public void testSetProbMontaña() {
        Montaña montaña = new Montaña();
        montaña.setProbMontaña(0.8);
        assertEquals(0.8, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testGetTipo() {
        Montaña montaña = new Montaña();
        assertEquals(Montaña.class, montaña.getTipo());
    }

    @Test
    public void testAplicarEfecto_PositiveLifeReduction() {
        Estudiante estudiante = new Estudiante(100) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        }; // Tiene 100 de vida
        Celda celda = new Celda();
        Montaña montaña = new Montaña(1, 0, 0, 3, 0.5, 10, 0.7); // Reduce 10 de vida
        montaña.aplicarEfecto(estudiante, celda);
        assertEquals(90, estudiante.getTiempoDeVida()); // Se debe reducir la vida en 10
    }

    @Test
    public void testAplicarEfecto_ZeroLifeReduction() {
        Estudiante estudiante = new Estudiante(5) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        }; // Tiene 5 de vida
        Celda celda = new Celda();
        Montaña montaña = new Montaña(1, 0, 0, 3, 0.5, 10, 0.7); // Reduce 10 de vida
        montaña.aplicarEfecto(estudiante, celda);
        assertEquals(0, estudiante.getTiempoDeVida()); // Se debería eliminar al estudiante
    }
}