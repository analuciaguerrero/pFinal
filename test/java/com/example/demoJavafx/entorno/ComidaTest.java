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

class ComidaTest {
    private DatosJuego datosJuego;
    private Comida comida;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        comida = new Comida(1, datosJuego);
        celda = new Celda(0, 0);
    }

    @Test
    void testConstructorWithFullParams() {
        Comida newComida = new Comida(1, 2, 3, datosJuego);
        assertEquals(1, newComida.getId());
        assertEquals(2, newComida.getPosicionN());
        assertEquals(3, newComida.getPosicionM());
        assertEquals(datosJuego.getAumentoVidaComida(), newComida.getAumentoVida());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Comida newComida = new Comida(1, datosJuego);
        assertEquals(1, newComida.getId());
        assertEquals(datosJuego.getAumentoVidaComida(), newComida.getAumentoVida());
    }

    @Test
    void testGetAumentoVida() {
        assertEquals(datosJuego.getAumentoVidaComida(), comida.getAumentoVida());
    }

    @Test
    void testSetAumentoVida() {
        try {
            comida.setAumentoVida(5);
            assertEquals(5, comida.getAumentoVida());
        } catch (IncrementoNoValido e) {
            fail("Exception should not be thrown for positive value");
        }
    }

    @Test
    void testSetAumentoVidaThrowsException() {
        assertThrows(IncrementoNoValido.class, () -> {
            comida.setAumentoVida(-1);
        });
    }

    @Test
    void testGetTipo() {
        assertEquals(Comida.class, comida.getTipo());
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

        int initialTiempoDeVida = estudiante.getTiempoDeVida();
        comida.aplicarEfecto(estudiante, celda, turno);

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudiante.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Comida, turno: " + turno, colaOperaciones.peek());

        // Verificar que el tiempo de vida del estudiante ha aumentado correctamente
        assertEquals(initialTiempoDeVida + comida.getAumentoVida(), estudiante.getTiempoDeVida());
    }
}