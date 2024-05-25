package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursosPropertiesTest {
    private Recursos originalRecursos;
    private RecursosProperties recursosProperties;

    @BeforeEach
    void setUp() {
        originalRecursos = new Recursos() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        };
        originalRecursos.setTurnosRestantes(5);
        originalRecursos.setPosicionN(10);
        originalRecursos.setPosicionM(15);
        recursosProperties = new RecursosProperties(originalRecursos);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalRecursos, recursosProperties.getOriginal());
        assertEquals(5, recursosProperties.turnosRestantesProperty().getValue());
        assertEquals(10, recursosProperties.posicionNProperty().getValue());
        assertEquals(15, recursosProperties.posicionMProperty().getValue());
    }

    @Test
    void testCommit() {
        recursosProperties.turnosRestantesProperty().setValue(7);
        recursosProperties.posicionNProperty().setValue(12);
        recursosProperties.posicionMProperty().setValue(18);
        recursosProperties.commit();

        assertEquals(7, originalRecursos.getTurnosRestantes());
        assertEquals(12, originalRecursos.getPosicionN());
        assertEquals(18, originalRecursos.getPosicionM());
    }

    @Test
    void testRollback() {
        originalRecursos.setTurnosRestantes(8);
        originalRecursos.setPosicionN(14);
        originalRecursos.setPosicionM(20);
        recursosProperties.rollback();

        assertEquals(8, recursosProperties.turnosRestantesProperty().getValue());
        assertEquals(14, recursosProperties.posicionNProperty().getValue());
        assertEquals(20, recursosProperties.posicionMProperty().getValue());
    }

    @Test
    void testSetOriginal() {
        Recursos newRecursos = new Recursos() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        };
        newRecursos.setTurnosRestantes(10);
        newRecursos.setPosicionN(22);
        newRecursos.setPosicionM(25);
        recursosProperties.setOriginal(newRecursos);

        assertEquals(newRecursos, recursosProperties.getOriginal());
        assertEquals(10, recursosProperties.turnosRestantesProperty().getValue());
        assertEquals(22, recursosProperties.posicionNProperty().getValue());
        assertEquals(25, recursosProperties.posicionMProperty().getValue());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        recursosProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.setValue(20);
        assertEquals(20, recursosProperties.turnosRestantesProperty().getValue());

        recursosProperties.turnosRestantesProperty().setValue(30);
        assertEquals(30, turnosRestantesProperty.getValue());
    }

    @Test
    void testPosicionNProperty() {
        IntegerProperty posicionNProperty = new SimpleIntegerProperty(10);
        recursosProperties.posicionNProperty().bindBidirectional(posicionNProperty);

        posicionNProperty.setValue(20);
        assertEquals(20, recursosProperties.posicionNProperty().getValue());

        recursosProperties.posicionNProperty().setValue(30);
        assertEquals(30, posicionNProperty.getValue());
    }

    @Test
    void testPosicionMProperty() {
        IntegerProperty posicionMProperty = new SimpleIntegerProperty(10);
        recursosProperties.posicionMProperty().bindBidirectional(posicionMProperty);

        posicionMProperty.setValue(20);
        assertEquals(20, recursosProperties.posicionMProperty().getValue());

        recursosProperties.posicionMProperty().setValue(30);
        assertEquals(30, posicionMProperty.getValue());
    }
}