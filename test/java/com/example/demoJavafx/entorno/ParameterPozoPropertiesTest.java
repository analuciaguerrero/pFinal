package com.example.demoJavafx.entorno;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterPozoPropertiesTest {
    private Pozo originalPozo;
    private ParameterPozoProperties parameterPozoProperties;

    @BeforeEach
    void setUp() {
        originalPozo = new Pozo();
        originalPozo.setTurnosRestantes(5);
        parameterPozoProperties = new ParameterPozoProperties(originalPozo);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalPozo, parameterPozoProperties.getOriginal());
        assertEquals(5, parameterPozoProperties.turnosRestantesProperty().get());
    }

    @Test
    void testCommit() {
        parameterPozoProperties.turnosRestantesProperty().set(7);
        parameterPozoProperties.commit();

        assertEquals(7, originalPozo.getTurnosRestantes());
    }

    @Test
    void testRollback() {
        originalPozo.setTurnosRestantes(8);
        parameterPozoProperties.rollback();

        assertEquals(8, parameterPozoProperties.turnosRestantesProperty().get());
    }

    @Test
    void testSetOriginal() {
        Pozo newPozo = new Pozo();
        newPozo.setTurnosRestantes(12);
        parameterPozoProperties.setOriginal(newPozo);

        assertEquals(newPozo, parameterPozoProperties.getOriginal());
        assertEquals(12, parameterPozoProperties.turnosRestantesProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterPozoProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterPozoProperties.turnosRestantesProperty().get());

        parameterPozoProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }
}