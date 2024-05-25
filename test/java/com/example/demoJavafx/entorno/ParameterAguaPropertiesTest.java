package com.example.demoJavafx.entorno;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterAguaPropertiesTest {
    private Agua originalAgua;
    private ParameterAguaProperties parameterAguaProperties;

    @BeforeEach
    void setUp() {
        originalAgua = new Agua();
        originalAgua.setTurnosRestantes(5);
        originalAgua.setAumentoVida(10);
        parameterAguaProperties = new ParameterAguaProperties(originalAgua);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalAgua, parameterAguaProperties.getOriginalAgua());
        assertEquals(5, parameterAguaProperties.turnosRestantesProperty().get());
        assertEquals(10, parameterAguaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testCommit() {
        parameterAguaProperties.turnosRestantesProperty().set(7);
        parameterAguaProperties.aumentoVidaProperty().set(15);
        parameterAguaProperties.commit();

        assertEquals(7, originalAgua.getTurnosRestantes());
        assertEquals(15, originalAgua.getAumentoVida());
    }

    @Test
    void testRollback() {
        originalAgua.setTurnosRestantes(8);
        originalAgua.setAumentoVida(20);
        parameterAguaProperties.rollback();

        assertEquals(8, parameterAguaProperties.turnosRestantesProperty().get());
        assertEquals(20, parameterAguaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testSetOriginalAgua() {
        Agua newAgua = new Agua();
        newAgua.setTurnosRestantes(12);
        newAgua.setAumentoVida(25);
        parameterAguaProperties.setOriginalAgua(newAgua);

        assertEquals(newAgua, parameterAguaProperties.getOriginalAgua());
        assertEquals(12, parameterAguaProperties.turnosRestantesProperty().get());
        assertEquals(25, parameterAguaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterAguaProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterAguaProperties.turnosRestantesProperty().get());

        parameterAguaProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }

    @Test
    void testAumentoVidaProperty() {
        IntegerProperty aumentoVidaProperty = new SimpleIntegerProperty(10);
        parameterAguaProperties.aumentoVidaProperty().bindBidirectional(aumentoVidaProperty);

        aumentoVidaProperty.set(20);
        assertEquals(20, parameterAguaProperties.aumentoVidaProperty().get());

        parameterAguaProperties.aumentoVidaProperty().set(30);
        assertEquals(30, aumentoVidaProperty.get());
    }
}