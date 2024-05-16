package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursosPropertiesTest {
    @Test
    public void testConstructorAndGetters() {
        Recursos recurso = new RecursosMock(1, 2, 3, 4, 0.5);
        RecursosProperties recursosProperties = new RecursosProperties(recurso);
        assertSame(recurso, recursosProperties.getOriginal());
    }

    @Test
    public void testSetOriginal() {
        Recursos recurso1 = new RecursosMock(1, 2, 3, 4, 0.5);
        Recursos recurso2 = new RecursosMock(2, 3, 4, 5, 0.7);
        RecursosProperties recursosProperties = new RecursosProperties(recurso1);
        recursosProperties.setOriginal(recurso2);
        assertSame(recurso2, recursosProperties.getOriginal());
        assertEquals(5, recursosProperties.turnosRestantesProperty().getValue());
        assertEquals(3, recursosProperties.posicionNProperty().getValue());
        assertEquals(4, recursosProperties.posicionMProperty().getValue());
        assertEquals(0.7, recursosProperties.probRecursoProperty().getValue().doubleValue(), 0.001);
    }

    @Test
    public void testRollback() {
        Recursos recurso = new RecursosMock(1, 2, 3, 4, 0.5);
        RecursosProperties recursosProperties = new RecursosProperties(recurso);
        recursosProperties.turnosRestantesProperty().setValue(10);
        recursosProperties.posicionNProperty().setValue(5);
        recursosProperties.posicionMProperty().setValue(6);
        recursosProperties.probRecursoProperty().setValue(0.8);
        recursosProperties.rollback();
        assertEquals(4, recursosProperties.turnosRestantesProperty().getValue());
        assertEquals(2, recursosProperties.posicionNProperty().getValue());
        assertEquals(3, recursosProperties.posicionMProperty().getValue());
        assertEquals(0.5, recursosProperties.probRecursoProperty().getValue().doubleValue(), 0.001);
    }

    @Test
    public void testCommit() {
        Recursos recurso = new RecursosMock(1, 2, 3, 4, 0.5);
        RecursosProperties recursosProperties = new RecursosProperties(recurso);
        recursosProperties.turnosRestantesProperty().setValue(10);
        recursosProperties.posicionNProperty().setValue(5);
        recursosProperties.posicionMProperty().setValue(6);
        recursosProperties.probRecursoProperty().setValue(0.8);
        recursosProperties.commit();
        assertEquals(10, recurso.getTurnosRestantes());
        assertEquals(5, recurso.getPosicionN());
        assertEquals(6, recurso.getPosicionM());
        assertEquals(0.8, recurso.getProbRecurso(), 0.001);
    }

    // Clase mock para Recursos
    class RecursosMock extends Recursos {
        public RecursosMock(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
            super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        }
        @Override
        public Class<?> getTipo() {
            return null;
        }
        @Override
        public void aplicarEfecto(Estudiante estudiante, Celda celda) {
            // Método ficticio para las pruebas
        }
    }
}