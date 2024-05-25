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

public class AguaTest {
    private DatosJuego datosJuego;
    private Agua agua;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        agua = new Agua(1, datosJuego);
    }

    @Test
    void testConstructorWithFullParams() {
        Agua newAgua = new Agua(1, 2, 3, datosJuego);
        assertEquals(1, newAgua.getId());
        assertEquals(2, newAgua.getPosicionN());
        assertEquals(3, newAgua.getPosicionM());
        assertEquals(datosJuego.getAumentoVidaAgua(), newAgua.getAumentoVida());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Agua newAgua = new Agua(1, datosJuego);
        assertEquals(1, newAgua.getId());
        assertEquals(datosJuego.getAumentoVidaAgua(), newAgua.getAumentoVida());
    }

    @Test
    void testGetAumentoVida() {
        assertEquals(datosJuego.getAumentoVidaAgua(), agua.getAumentoVida());
    }

    @Test
    void testSetAumentoVida() {
        try {
            agua.setAumentoVida(5);
            assertEquals(5, agua.getAumentoVida());
        } catch (IncrementoNoValido e) {
            fail("Exception should not be thrown for positive value");
        }
    }

    @Test
    void testSetAumentoVidaThrowsException() {
        assertThrows(IncrementoNoValido.class, () -> {
            agua.setAumentoVida(-1);
        });
    }

    @Test
    void testGetTipo() {
        assertEquals(Agua.class, agua.getTipo());
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
        Celda celda = new Celda(1, 1);
        int turno = 1;

        int initialTiempoDeVida = estudiante.getTiempoDeVida();
        agua.aplicarEfecto(estudiante, celda, turno);

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudiante.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Agua, turno: " + turno, colaOperaciones.peek());

        // Verificar que el tiempo de vida del estudiante ha aumentado correctamente
        assertEquals(initialTiempoDeVida + agua.getAumentoVida(), estudiante.getTiempoDeVida());
    }
}