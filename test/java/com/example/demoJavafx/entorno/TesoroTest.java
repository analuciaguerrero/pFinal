package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoroTest {
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
        Tesoro tesoro = new Tesoro(1, 2, 3, 0.5, 0.2, 0.7);
        assertEquals(1, tesoro.getPosicionN());
        assertEquals(2, tesoro.getPosicionM());
        assertEquals(3, tesoro.getTurnosRestantes());
        assertEquals(0.5, tesoro.getProbRecurso(), 0.001);
        assertEquals(0.2, tesoro.getAumentoProbReproduccion(), 0.001);
        assertEquals(0.7, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testConstructorWithProbTesoroParameter() {
        Tesoro tesoro = new Tesoro(0.8);
        assertEquals(0.8, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Tesoro tesoro = new Tesoro();
        tesoro.setPosicionN(4);
        tesoro.setPosicionM(5);
        tesoro.setTurnosRestantes(6);
        tesoro.setProbRecurso(0.7);
        tesoro.setAumentoProbReproduccion(0.3);
        tesoro.setProbTesoro(0.8);

        assertEquals(4, tesoro.getPosicionN());
        assertEquals(5, tesoro.getPosicionM());
        assertEquals(6, tesoro.getTurnosRestantes());
        assertEquals(0.7, tesoro.getProbRecurso(), 0.001);
        assertEquals(0.3, tesoro.getAumentoProbReproduccion(), 0.001);
        assertEquals(0.8, Tesoro.getProbTesoro(), 0.001);
    }

    @Test
    public void testAplicarEfecto() {
        Tesoro tesoro = new Tesoro(1, 2, 3, 0.5, 0.2, 0.7);

        // Creamos un estudiante de prueba
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1,0,0);

        // Aplicamos el efecto del tesoro
        tesoro.aplicarEfecto(estudiante);

        // Verificamos que la probabilidad de reproducción se haya incrementado correctamente
        assertEquals(0.7, estudiante.getProbReproduccion(), 0.001); // Probabilidad de reproducción esperada después del efecto
    }
}