package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontañaTest {
    private DatosJuego datosJuego;
    private Montaña montaña;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        montaña = new Montaña(1, datosJuego);
        celda = new Celda(0, 0);
    }

    @Test
    void testConstructorWithFullParams() {
        Montaña newMontaña = new Montaña(1, 2, 3, datosJuego);
        assertEquals(1, newMontaña.getId());
        assertEquals(2, newMontaña.getPosicionN());
        assertEquals(3, newMontaña.getPosicionM());
        assertEquals(datosJuego.getReduccionVidaMontaña(), newMontaña.getReduccionVida());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Montaña newMontaña = new Montaña(1, datosJuego);
        assertEquals(1, newMontaña.getId());
        assertEquals(datosJuego.getReduccionVidaMontaña(), newMontaña.getReduccionVida());
    }

    @Test
    void testGetReduccionVida() {
        assertEquals(datosJuego.getReduccionVidaMontaña(), montaña.getReduccionVida());
    }

    @Test
    void testSetReduccionVida() {
        try {
            montaña.setReduccionVida(5);
            assertEquals(5, montaña.getReduccionVida());
        } catch (IncrementoNoValido e) {
            fail("Exception should not be thrown for positive value");
        }
    }

    @Test
    void testSetReduccionVidaThrowsException() {
        assertThrows(IncrementoNoValido.class, () -> {
            montaña.setReduccionVida(-1);
        });
    }

    @Test
    void testGetTipo() {
        assertEquals(Montaña.class, montaña.getTipo());
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

        estudiante.setTiempoDeVida(10, turno);
        montaña.aplicarEfecto(estudiante, celda, turno);

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudiante.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Montaña, turno: " + turno, colaOperaciones.peek());

        // Verificar que el tiempo de vida del estudiante ha disminuido correctamente
        assertEquals(10 - montaña.getReduccionVida(), estudiante.getTiempoDeVida());
    }

    @Test
    void testAplicarEfectoEliminaEstudiante() {
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

        estudiante.setTiempoDeVida(1, turno);
        celda.agregarEstudiante(estudiante, true);
        assertTrue(celda.getListaEstudiantes().getPosicion(estudiante) != null);

        montaña.aplicarEfecto(estudiante, celda, turno);

        // Verificar que el estudiante ha sido eliminado de la celda
        assertNull(celda.getListaEstudiantes().getPosicion(estudiante));
    }
}