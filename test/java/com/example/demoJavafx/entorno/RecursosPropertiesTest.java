package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursosPropertiesTest {
    private RecursosProperties recursosProperties;

    // Clase de prueba de Recursos
    private static class MockRecursos extends Recursos {
        // Constructor que llama al constructor de la clase base
        public MockRecursos(int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
            super(posicionN, posicionM, turnosRestantes, probRecurso);
        }
        @Override
        public int getTurnosRestantes() {
            return super.getTurnosRestantes();
        }

        @Override
        public void setTurnosRestantes(int turnosRestantes) {
            super.setTurnosRestantes(turnosRestantes);
        }

        @Override
        public int getPosicionN() {
            return super.getPosicionN();
        }

        @Override
        public void setPosicionN(int posicionN) {
            super.setPosicionN(posicionN);
        }

        @Override
        public int getPosicionM() {
            return super.getPosicionM();
        }

        @Override
        public void setPosicionM(int posicionM) {
            super.setPosicionM(posicionM);
        }

        @Override
        public double getProbRecurso() {
            return super.getProbRecurso();
        }

        @Override
        public void setProbRecurso(double probRecurso) {
            super.setProbRecurso(probRecurso);
        }

        @Override
        public void aplicarEfecto(Estudiante estudiante) {

        }
    }

    @BeforeEach
    void setUp() {
        // Crear una instancia de MockRecursos para cada prueba
        MockRecursos originalRecursos = new MockRecursos(0, 0, 0, 0.0);
        recursosProperties = new RecursosProperties(originalRecursos);
    }

    @Test
    void testCommit() {
        recursosProperties.turnosRestantesProperty().setValue(5);
        recursosProperties.posicionNProperty().setValue(3);
        recursosProperties.posicionMProperty().setValue(4);
        recursosProperties.probRecursoProperty().setValue(0.7);

        recursosProperties.commit();

        assertEquals(5, recursosProperties.getOriginal().getTurnosRestantes());
        assertEquals(3, recursosProperties.getOriginal().getPosicionN());
        assertEquals(4, recursosProperties.getOriginal().getPosicionM());
        assertEquals(0.7, recursosProperties.getOriginal().getProbRecurso());
    }

    @Test
    void testRollback() {
        recursosProperties.getOriginal().setTurnosRestantes(2);
        recursosProperties.getOriginal().setPosicionN(1);
        recursosProperties.getOriginal().setPosicionM(5);
        recursosProperties.getOriginal().setProbRecurso(0.5);

        recursosProperties.rollback();

        assertEquals(2, recursosProperties.getOriginal().getTurnosRestantes());
        assertEquals(1, recursosProperties.getOriginal().getPosicionN());
        assertEquals(5, recursosProperties.getOriginal().getPosicionM());
        assertEquals(0.5, recursosProperties.getOriginal().getProbRecurso());
    }

    @Test
    void testSetOriginalRecursos() {
        Recursos newRecursos = new MockRecursos(2, 3, 4, 0.6);

        recursosProperties.setOriginal(newRecursos);

        assertEquals(2, recursosProperties.getOriginal().getTurnosRestantes());
        assertEquals(3, recursosProperties.getOriginal().getPosicionN());
        assertEquals(4, recursosProperties.getOriginal().getPosicionM());
        assertEquals(0.6, recursosProperties.getOriginal().getProbRecurso());
    }
}