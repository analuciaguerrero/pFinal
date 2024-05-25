package com.example.demoJavafx.entorno;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterComidaPropertiesTest {
    private Comida originalComida;
    private ParameterComidaProperties parameterComidaProperties;

    @BeforeEach
    void setUp() {
        originalComida = new Comida();
        originalComida.setTurnosRestantes(5);
        originalComida.setAumentoVida(10);
        parameterComidaProperties = new ParameterComidaProperties(originalComida);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalComida, parameterComidaProperties.getOriginal());
        assertEquals(5, parameterComidaProperties.turnosRestantesProperty().get());
        assertEquals(10, parameterComidaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testCommit() {
        parameterComidaProperties.turnosRestantesProperty().set(7);
        parameterComidaProperties.aumentoVidaProperty().set(15);
        parameterComidaProperties.commit();

        assertEquals(7, originalComida.getTurnosRestantes());
        assertEquals(15, originalComida.getAumentoVida());
    }

    @Test
    void testRollback() {
        originalComida.setTurnosRestantes(8);
        originalComida.setAumentoVida(20);
        parameterComidaProperties.rollback();

        assertEquals(8, parameterComidaProperties.turnosRestantesProperty().get());
        assertEquals(20, parameterComidaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testSetOriginal() {
        Comida newComida = new Comida();
        newComida.setTurnosRestantes(12);
        newComida.setAumentoVida(25);
        parameterComidaProperties.setOriginal(newComida);

        assertEquals(newComida, parameterComidaProperties.getOriginal());
        assertEquals(12, parameterComidaProperties.turnosRestantesProperty().get());
        assertEquals(25, parameterComidaProperties.aumentoVidaProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterComidaProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterComidaProperties.turnosRestantesProperty().get());

        parameterComidaProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }

    @Test
    void testAumentoVidaProperty() {
        IntegerProperty aumentoVidaProperty = new SimpleIntegerProperty(10);
        parameterComidaProperties.aumentoVidaProperty().bindBidirectional(aumentoVidaProperty);

        aumentoVidaProperty.set(20);
        assertEquals(20, parameterComidaProperties.aumentoVidaProperty().get());

        parameterComidaProperties.aumentoVidaProperty().set(30);
        assertEquals(30, aumentoVidaProperty.get());
    }
}