package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {
    // Clase de prueba de Estudiante
    public static class MockEstudiante extends Estudiante {
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte, posicionN, posicionM);
        }
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
        }
        public MockEstudiante(int id){
            super(id);
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
        Agua agua = new Agua(1, 2, 3, 0.5, 10, 0.7);
        assertEquals(1, agua.getPosicionN());
        assertEquals(2, agua.getPosicionM());
        assertEquals(3, agua.getTurnosRestantes());
        assertEquals(0.5, agua.getProbRecurso(), 0.001);
        assertEquals(10, agua.getAumentoVida());
        assertEquals(0.7, agua.getProbAgua(), 0.001);
    }
    @Test
    public void testConstructorWithProbAguaParameter() {
        Agua agua = new Agua(0.7);
        assertEquals(0.7, Agua.getProbAgua(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Agua agua = new Agua();
        agua.setPosicionN(4);
        agua.setPosicionM(5);
        agua.setTurnosRestantes(6);
        agua.setProbRecurso(0.7);
        agua.setAumentoVida(20);
        agua.setProbAgua(0.8);

        assertEquals(4, agua.getPosicionN());
        assertEquals(5, agua.getPosicionM());
        assertEquals(6, agua.getTurnosRestantes());
        assertEquals(0.7, agua.getProbRecurso(), 0.001);
        assertEquals(20, agua.getAumentoVida());
        assertEquals(0.8, agua.getProbAgua(), 0.001);
    }

    @Test
    public void testAplicarEfecto() {
        Agua agua = new Agua(1, 2, 3, 0.5, 10, 0.7);
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1, 0, 0);

        agua.aplicarEfecto(estudiante);

        assertEquals(60, estudiante.getTiempoDeVida());
    }
}