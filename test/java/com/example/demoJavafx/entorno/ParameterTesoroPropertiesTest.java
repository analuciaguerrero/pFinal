package com.example.demoJavafx.entorno;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterTesoroPropertiesTest {
    private Tesoro originalTesoro;
    private ParameterTesoroProperties parameterTesoroProperties;

    @BeforeEach
    void setUp() {
        originalTesoro = new Tesoro();
        originalTesoro.setTurnosRestantes(5);
        originalTesoro.setAumentoProbReproduccion(15.5);
        parameterTesoroProperties = new ParameterTesoroProperties(originalTesoro);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalTesoro, parameterTesoroProperties.getOriginal());
        assertEquals(5, parameterTesoroProperties.turnosRestantesProperty().get());
        assertEquals(15.5, parameterTesoroProperties.aumentoPorcetajeRepProperty().get());
    }

    @Test
    void testCommit() {
        parameterTesoroProperties.turnosRestantesProperty().set(7);
        parameterTesoroProperties.aumentoPorcetajeRepProperty().set(20.5);
        parameterTesoroProperties.commit();

        assertEquals(7, originalTesoro.getTurnosRestantes());
        assertEquals(20.5, originalTesoro.getAumentoProbReproduccion());
    }

    @Test
    void testRollback() {
        originalTesoro.setTurnosRestantes(8);
        originalTesoro.setAumentoProbReproduccion(25.5);
        parameterTesoroProperties.rollback();

        assertEquals(8, parameterTesoroProperties.turnosRestantesProperty().get());
        assertEquals(25.5, parameterTesoroProperties.aumentoPorcetajeRepProperty().get());
    }

    @Test
    void testSetOriginal() {
        Tesoro newTesoro = new Tesoro();
        newTesoro.setTurnosRestantes(12);
        newTesoro.setAumentoProbReproduccion(30.5);
        parameterTesoroProperties.setOriginal(newTesoro);

        assertEquals(newTesoro, parameterTesoroProperties.getOriginal());
        assertEquals(12, parameterTesoroProperties.turnosRestantesProperty().get());
        assertEquals(30.5, parameterTesoroProperties.aumentoPorcetajeRepProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterTesoroProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterTesoroProperties.turnosRestantesProperty().get());

        parameterTesoroProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }

    @Test
    void testAumentoPorcetajeRepProperty() {
        DoubleProperty aumentoPorcetajeRepProperty = new SimpleDoubleProperty(10.0);
        parameterTesoroProperties.aumentoPorcetajeRepProperty().bindBidirectional(aumentoPorcetajeRepProperty);

        aumentoPorcetajeRepProperty.set(20.0);
        assertEquals(20.0, parameterTesoroProperties.aumentoPorcetajeRepProperty().get());

        parameterTesoroProperties.aumentoPorcetajeRepProperty().set(30.0);
        assertEquals(30.0, aumentoPorcetajeRepProperty.get());
    }
}