package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {
    private DatosJuego datosJuego;
    private Pozo pozo;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        pozo = new Pozo(1, datosJuego);
        celda = new Celda(0, 0);
    }

    @Test
    void testConstructorWithFullParams() {
        Pozo newPozo = new Pozo(1, 2, 3, datosJuego);
        assertEquals(1, newPozo.getId());
        assertEquals(2, newPozo.getPosicionN());
        assertEquals(3, newPozo.getPosicionM());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Pozo newPozo = new Pozo(1, datosJuego);
        assertEquals(1, newPozo.getId());
    }

    @Test
    void testGetTipo() {
        assertEquals(Pozo.class, pozo.getTipo());
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

        celda.agregarEstudiante(estudiante, true);
        assertTrue(celda.getListaEstudiantes().getPosicion(estudiante) != null);

        pozo.aplicarEfecto(estudiante, celda, turno);

        // Verificar que el estudiante ha sido eliminado de la celda
        assertNull(celda.getListaEstudiantes().getPosicion(estudiante));

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudiante.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Pozo, turno: " + turno, colaOperaciones.peek());
    }
}