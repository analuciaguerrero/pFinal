package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoroTest {
    private DatosJuego datosJuego;
    private Tesoro tesoro;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        tesoro = new Tesoro(1, datosJuego);
        celda = new Celda(0, 0);
    }

    @Test
    void testConstructorWithFullParams() {
        Tesoro newTesoro = new Tesoro(1, 2, 3, datosJuego);
        assertEquals(1, newTesoro.getId());
        assertEquals(2, newTesoro.getPosicionN());
        assertEquals(3, newTesoro.getPosicionM());
        assertEquals(datosJuego.getAumentoProbReproduccion(), newTesoro.getAumentoProbReproduccion());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Tesoro newTesoro = new Tesoro(1, datosJuego);
        assertEquals(1, newTesoro.getId());
        assertEquals(datosJuego.getAumentoProbReproduccion(), newTesoro.getAumentoProbReproduccion());
    }

    @Test
    void testGetAumentoProbReproduccion() {
        assertEquals(datosJuego.getAumentoProbReproduccion(), tesoro.getAumentoProbReproduccion());
    }

    @Test
    void testSetAumentoProbReproduccion() {
        try {
            tesoro.setAumentoProbReproduccion(50);
            assertEquals(50, tesoro.getAumentoProbReproduccion());
        } catch (ProbabilidadNoValida e) {
            fail("Exception should not be thrown for valid probability");
        }
    }

    @Test
    void testSetAumentoProbReproduccionThrowsException() {
        assertThrows(ProbabilidadNoValida.class, () -> {
            tesoro.setAumentoProbReproduccion(-1);
        });
        assertThrows(ProbabilidadNoValida.class, () -> {
            tesoro.setAumentoProbReproduccion(101);
        });
    }

    @Test
    void testGetTipo() {
        assertEquals(Tesoro.class, tesoro.getTipo());
    }

    @Test
    void testAplicarEfecto() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        int turno = 1;

        double initialProbReproduccion = estudiante.getProbReproduccion();
        tesoro.aplicarEfecto(estudiante, celda, turno);

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudiante.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Tesoro, turno: " + turno, colaOperaciones.peek());

        // Verificar que la probabilidad de reproducción del estudiante ha aumentado correctamente
        if (initialProbReproduccion + tesoro.getAumentoProbReproduccion() > 100) {
            assertEquals(100, estudiante.getProbReproduccion());
        } else {
            assertEquals(initialProbReproduccion + tesoro.getAumentoProbReproduccion(), estudiante.getProbReproduccion());
        }
    }
}