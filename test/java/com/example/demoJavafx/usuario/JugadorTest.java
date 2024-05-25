package com.example.demoJavafx.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    private Jugador jugador;

    @BeforeEach
    void setUp() {
        jugador = new Jugador("12345678A");
    }

    @Test
    void testConstructor() {
        assertEquals("12345678A", jugador.getDni());
        assertEquals("BAJA", jugador.getDificultad());
        assertEquals(0, jugador.getPuntuacion());
        assertEquals(0, jugador.getPartidasJugadas());
        assertEquals(0, jugador.getPartidasGanadas());
        assertEquals(0, jugador.getPartidasPerdidas());
    }

    @Test
    void testGetDificultades() {
        assertNotNull(jugador.getDificultades());
    }

    @Test
    void testAddDificultad() {
        jugador.addDificultad("ALTA");
        assertEquals("ALTA", jugador.getDificultades().getElemento(0).getData());
    }

    @Test
    void testGetPuntosPartida() {
        assertNotNull(jugador.getPuntosPartida());
    }

    @Test
    void testAddPuntos() {
        jugador.addPuntos(10);
        assertEquals(10, jugador.getPuntosPartida().getElemento(0).getData());
    }

    @Test
    void testGetNombre() {
        assertNull(jugador.getNombre());
    }

    @Test
    void testSetNombre() {
        jugador.setNombre("John Doe");
        assertEquals("John Doe", jugador.getNombre());
    }

    @Test
    void testGetDni() {
        assertEquals("12345678A", jugador.getDni());
    }

    @Test
    void testSetDni() {
        jugador.setDni("87654321B");
        assertEquals("87654321B", jugador.getDni());
    }

    @Test
    void testGetPuntuacion() {
        assertEquals(0, jugador.getPuntuacion());
    }

    @Test
    void testAddPuntuacion() {
        jugador.addPuntuacion(20);
        assertEquals(20, jugador.getPuntuacion());
    }

    @Test
    void testSetPuntuacion() {
        jugador.setPuntuacion(30);
        assertEquals(30, jugador.getPuntuacion());
    }

    @Test
    void testGetDificultad() {
        assertEquals("BAJA", jugador.getDificultad());
    }

    @Test
    void testSetDificultad() {
        jugador.setDificultad("MEDIA");
        assertEquals("MEDIA", jugador.getDificultad());
    }

    @Test
    void testGetPartidasJugadas() {
        assertEquals(0, jugador.getPartidasJugadas());
    }

    @Test
    void testSetPartidasJugadas() {
        jugador.setPartidasJugadas(5);
        assertEquals(5, jugador.getPartidasJugadas());
    }

    @Test
    void testAddPartidaJugada() {
        jugador.addPartidaJugada();
        assertEquals(1, jugador.getPartidasJugadas());
    }

    @Test
    void testAddPartidaGanada() {
        jugador.addPartidaGanada();
        assertEquals(1, jugador.getPartidasGanadas());
    }

    @Test
    void testAddPartidaPerdida() {
        jugador.addPartidaPerdida();
        assertEquals(1, jugador.getPartidasPerdidas());
    }

    @Test
    void testGetPartidasGanadas() {
        assertEquals(0, jugador.getPartidasGanadas());
    }

    @Test
    void testSetPartidasGanadas() {
        jugador.setPartidasGanadas(3);
        assertEquals(3, jugador.getPartidasGanadas());
    }

    @Test
    void testGetPartidasPerdidas() {
        assertEquals(0, jugador.getPartidasPerdidas());
    }

    @Test
    void testSetPartidasPerdidas() {
        jugador.setPartidasPerdidas(2);
        assertEquals(2, jugador.getPartidasPerdidas());
    }
}