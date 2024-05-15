package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MontañaTest {
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
        Montaña montaña = new Montaña(1, 2, 3, 0.5, 10, 0.7);
        assertEquals(1, montaña.getPosicionN());
        assertEquals(2, montaña.getPosicionM());
        assertEquals(3, montaña.getTurnosRestantes());
        assertEquals(0.5, montaña.getProbRecurso(), 0.001);
        assertEquals(10, montaña.getReduccionVida());
        assertEquals(0.7, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testConstructorWithProbMontañaParameter() {
        Montaña montaña = new Montaña(0.8);
        assertEquals(0.8, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Montaña montaña = new Montaña();
        montaña.setPosicionN(4);
        montaña.setPosicionM(5);
        montaña.setTurnosRestantes(6);
        montaña.setProbRecurso(0.7);
        montaña.setReduccionVida(20);
        montaña.setProbMontaña(0.8);

        assertEquals(4, montaña.getPosicionN());
        assertEquals(5, montaña.getPosicionM());
        assertEquals(6, montaña.getTurnosRestantes());
        assertEquals(0.7, montaña.getProbRecurso(), 0.001);
        assertEquals(20, montaña.getReduccionVida());
        assertEquals(0.8, Montaña.getProbMontaña(), 0.001);
    }

    @Test
    public void testAplicarEfecto() {
        Montaña montaña = new Montaña(1, 2, 3, 0.5, 10, 0.7);

        // Creamos un estudiante de prueba
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1,0,0);

        // Aplicamos el efecto de la montaña
        montaña.aplicarEfecto(estudiante);

        // Verificamos que el tiempo de vida del estudiante se haya reducido correctamente
        assertEquals(40, estudiante.getTiempoDeVida()); // Tiempo de vida esperado después del efecto
    }
}