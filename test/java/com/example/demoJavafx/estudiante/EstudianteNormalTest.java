package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteNormalTest {
    @Test
    public void testMoverArriba() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(1);
        estudiante.setPosicionM(1);

        estudiante.mover(tablero);

        assertEquals(0, estudiante.getPosicionN());
    }

    @Test
    public void testMoverAbajo() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(1);

        estudiante.mover(tablero);

        assertEquals(1, estudiante.getPosicionN());
    }

    @Test
    public void testMoverIzquierda() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(1);

        estudiante.mover(tablero);

        assertEquals(0, estudiante.getPosicionM());
    }

    @Test
    public void testMoverDerecha() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(0);

        estudiante.mover(tablero);

        assertEquals(1, estudiante.getPosicionM());
    }
    // Prueba para el método reproducirse
    @Test
    public void testReproducirse() {
        EstudianteNormal estudiante1 = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        EstudianteNormal estudiante2 = new EstudianteNormal(2, 1, 8, 0.4, 0.3, 0.1);

        EstudianteNormal hijo = (EstudianteNormal) estudiante1.reproducirse(estudiante2);

        assertEquals(1, hijo.getGeneracion());
    }

    @Test
    public void testClonar() {
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);

        EstudianteNormal clon = (EstudianteNormal) estudiante.clonar();

        assertEquals(estudiante.getGeneracion(), clon.getGeneracion());
        assertEquals(estudiante.getTiempoDeVida(), clon.getTiempoDeVida());
    }
    @Test
    public void testMoverIzquierda2() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(1);

        estudiante.mover(tablero);

        assertEquals(0, estudiante.getPosicionM());
    }

    @Test
    public void testMoverDerecha2() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(0);

        estudiante.mover(tablero);

        assertEquals(1, estudiante.getPosicionM());
    }
    @Test
    public void testMoverArriba2() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(1);
        estudiante.setPosicionM(0);

        estudiante.mover(tablero);

        assertEquals(0, estudiante.getPosicionN());
    }
    @Test
    public void testMoverDerecha3() {
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        tablero.add(new Celda());
        tablero.add(new Celda());
        EstudianteNormal estudiante = new EstudianteNormal(1, 1, 10, 0.5, 0.2, 0.1);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(0);

        estudiante.mover(tablero);

        assertEquals(1, estudiante.getPosicionM());
    }
}