package com.example.demoJavafx.entorno;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterMontañaPropertiesTest {
    private Montaña originalMontaña;
    private ParameterMontañaProperties parameterMontañaProperties;

    @BeforeEach
    void setUp() {
        originalMontaña = new Montaña();
        originalMontaña.setTurnosRestantes(5);
        originalMontaña.setReduccionVida(10);
        parameterMontañaProperties = new ParameterMontañaProperties(originalMontaña);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalMontaña, parameterMontañaProperties.getOriginal());
        assertEquals(5, parameterMontañaProperties.turnosRestantesProperty().get());
        assertEquals(10, parameterMontañaProperties.disminucionVidaProperty().get());
    }

    @Test
    void testCommit() {
        parameterMontañaProperties.turnosRestantesProperty().set(7);
        parameterMontañaProperties.disminucionVidaProperty().set(15);
        parameterMontañaProperties.commit();

        assertEquals(7, originalMontaña.getTurnosRestantes());
        assertEquals(15, originalMontaña.getReduccionVida());
    }

    @Test
    void testRollback() {
        originalMontaña.setTurnosRestantes(8);
        originalMontaña.setReduccionVida(20);
        parameterMontañaProperties.rollback();

        assertEquals(8, parameterMontañaProperties.turnosRestantesProperty().get());
        assertEquals(20, parameterMontañaProperties.disminucionVidaProperty().get());
    }

    @Test
    void testSetOriginal() {
        Montaña newMontaña = new Montaña();
        newMontaña.setTurnosRestantes(12);
        newMontaña.setReduccionVida(25);
        parameterMontañaProperties.setOriginal(newMontaña);

        assertEquals(newMontaña, parameterMontañaProperties.getOriginal());
        assertEquals(12, parameterMontañaProperties.turnosRestantesProperty().get());
        assertEquals(25, parameterMontañaProperties.disminucionVidaProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterMontañaProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterMontañaProperties.turnosRestantesProperty().get());

        parameterMontañaProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }

    @Test
    void testDisminucionVidaProperty() {
        IntegerProperty disminucionVidaProperty = new SimpleIntegerProperty(10);
        parameterMontañaProperties.disminucionVidaProperty().bindBidirectional(disminucionVidaProperty);

        disminucionVidaProperty.set(20);
        assertEquals(20, parameterMontañaProperties.disminucionVidaProperty().get());

        parameterMontañaProperties.disminucionVidaProperty().set(30);
        assertEquals(30, disminucionVidaProperty.get());
    }
}