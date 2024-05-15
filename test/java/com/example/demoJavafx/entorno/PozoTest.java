package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PozoTest {
    private static class MockEstudiante extends Estudiante {
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte, posicionN, posicionM);
        }

        @Override
        public void mover(ListaEnlazada<Celda> tablero) {

        }

        @Override
        public Estudiante reproducirse(Estudiante pareja) {
            return null;
        }

        @Override
        public Estudiante clonar() {
            return null;
        }
    }
    @Test
    public void testConstructorWithAllParameters() {
        Pozo pozo = new Pozo(1, 2, 3, 0.5, 0.7);
        assertEquals(1, pozo.getPosicionN());
        assertEquals(2, pozo.getPosicionM());
        assertEquals(3, pozo.getTurnosRestantes());
        assertEquals(0.5, pozo.getProbRecurso(), 0.001);
        assertEquals(0.7, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testConstructorWithProbPozoParameter() {
        Pozo pozo = new Pozo(0.8);
        assertEquals(0.8, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Pozo pozo = new Pozo();
        pozo.setPosicionN(4);
        pozo.setPosicionM(5);
        pozo.setTurnosRestantes(6);
        pozo.setProbRecurso(0.7);
        pozo.setProbPozo(0.8);

        assertEquals(4, pozo.getPosicionN());
        assertEquals(5, pozo.getPosicionM());
        assertEquals(6, pozo.getTurnosRestantes());
        assertEquals(0.7, pozo.getProbRecurso(), 0.001);
        assertEquals(0.8, Pozo.getProbPozo(), 0.001);
    }

    @Test
    public void testAplicarEfecto() {
        Pozo pozo = new Pozo(1, 2, 3, 0.5, 0.7);

        // Creamos un estudiante de prueba
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1,0,0);

        // Aplicamos el efecto del pozo
        pozo.aplicarEfecto(estudiante);

        // Verificamos que el tiempo de vida del estudiante sea 0 (muerte instantánea)
        assertEquals(0, estudiante.getTiempoDeVida());
    }
}