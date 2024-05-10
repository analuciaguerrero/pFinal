package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursosTest {
    // Clase de prueba de Recursos
    public static class MockRecursos extends Recursos {
        // Constructor que llama al constructor de la clase base
        public MockRecursos(int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
            super(posicionN, posicionM, turnosRestantes, probRecurso);
        }
        public MockRecursos(int posicionN, int posicionM, int turnosRestantes){
            super(posicionN, posicionM, turnosRestantes);
        }
        public MockRecursos(){}

        @Override
        public void aplicarEfecto(Estudiante estudiante) {
        }
    }

    @Test
    public void testConstructorWithAllParameters() {
        MockRecursos recursos = new MockRecursos(1, 2, 3, 0.5);
        assertEquals(1, recursos.getPosicionN());
        assertEquals(2, recursos.getPosicionM());
        assertEquals(3, recursos.getTurnosRestantes());
        assertEquals(0.5, recursos.getProbRecurso(), 0.001);
    }

    @Test
    public void testConstructorWithThreeParameters() {
        Recursos recurso = new MockRecursos(1, 2, 3);
        assertEquals(1, recurso.getPosicionN());
        assertEquals(2, recurso.getPosicionM());
        assertEquals(3, recurso.getTurnosRestantes());
    }

    @Test
    public void testGettersAndSetters() {
        MockRecursos recursos = new MockRecursos(1, 2, 3, 0.0);
        recursos.setPosicionN(4);
        recursos.setPosicionM(5);
        recursos.setTurnosRestantes(6);
        recursos.setProbRecurso(0.7);

        assertEquals(4, recursos.getPosicionN());
        assertEquals(5, recursos.getPosicionM());
        assertEquals(6, recursos.getTurnosRestantes());
        assertEquals(0.7, recursos.getProbRecurso(), 0.001);
    }

    @Test
    public void testActualizarRecursos() {
        MockRecursos recursos = new MockRecursos(1, 2, 3, 0.0);
        recursos.actualizarRecursos();
        assertEquals(2, recursos.getTurnosRestantes());
    }
    @Test
    public void testAplicarEfecto() {
    }
}