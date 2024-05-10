package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComidaTest {
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
        Comida comida = new Comida(1, 2, 3, 0.5, 10, 0.7);
        assertEquals(1, comida.getPosicionN());
        assertEquals(2, comida.getPosicionM());
        assertEquals(3, comida.getTurnosRestantes());
        assertEquals(0.5, comida.getProbRecurso(), 0.001);
        assertEquals(10, comida.getAumentoVida());
        assertEquals(0.7, Comida.getProbComida(), 0.001);
    }

    @Test
    public void testConstructorWithProbComidaParameter() {
        Comida comida = new Comida(0.8);
        assertEquals(0.8, Comida.getProbComida(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Comida comida = new Comida();
        comida.setPosicionN(4);
        comida.setPosicionM(5);
        comida.setTurnosRestantes(6);
        comida.setProbRecurso(0.7);
        comida.setAumentoVida(20);
        comida.setProbComida(0.8);

        assertEquals(4, comida.getPosicionN());
        assertEquals(5, comida.getPosicionM());
        assertEquals(6, comida.getTurnosRestantes());
        assertEquals(0.7, comida.getProbRecurso(), 0.001);
        assertEquals(20, comida.getAumentoVida());
        assertEquals(0.8, Comida.getProbComida(), 0.001);
    }

    @Test
    public void testAplicarEfecto() {
        Comida comida = new Comida(1, 2, 3, 0.5, 10, 0.7);

        // Creamos un estudiante de prueba
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1,0,0);

        // Aplicamos el efecto de la comida
        comida.aplicarEfecto(estudiante);

        // Verificamos que el tiempo de vida del estudiante se haya aumentado correctamente
        assertEquals(60, estudiante.getTiempoDeVida()); // Tiempo de vida esperado después del efecto
    }
}