package com.example.demoJavafx.entorno;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterBibliotecaPropertiesTest {
    private Biblioteca originalBiblioteca;
    private ParameterBibliotecaProperties parameterBibliotecaProperties;

    @BeforeEach
    void setUp() {
        originalBiblioteca = new Biblioteca();
        originalBiblioteca.setTurnosRestantes(5);
        originalBiblioteca.setAumentoProbClonacion(10.5);
        parameterBibliotecaProperties = new ParameterBibliotecaProperties(originalBiblioteca);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(originalBiblioteca, parameterBibliotecaProperties.getOriginal());
        assertEquals(5, parameterBibliotecaProperties.turnosRestantesProperty().get());
        assertEquals(10.5, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());
    }

    @Test
    void testCommit() {
        parameterBibliotecaProperties.turnosRestantesProperty().set(7);
        parameterBibliotecaProperties.aumentoPorentajeClonProperty().set(15.5);
        parameterBibliotecaProperties.commit();

        assertEquals(7, originalBiblioteca.getTurnosRestantes());
        assertEquals(15.5, originalBiblioteca.getAumentoProbClonacion());
    }

    @Test
    void testRollback() {
        originalBiblioteca.setTurnosRestantes(8);
        originalBiblioteca.setAumentoProbClonacion(20.5);
        parameterBibliotecaProperties.rollback();

        assertEquals(8, parameterBibliotecaProperties.turnosRestantesProperty().get());
        assertEquals(20.5, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());
    }

    @Test
    void testSetOriginal() {
        Biblioteca newBiblioteca = new Biblioteca();
        newBiblioteca.setTurnosRestantes(12);
        newBiblioteca.setAumentoProbClonacion(25.5);
        parameterBibliotecaProperties.setOriginal(newBiblioteca);

        assertEquals(newBiblioteca, parameterBibliotecaProperties.getOriginal());
        assertEquals(12, parameterBibliotecaProperties.turnosRestantesProperty().get());
        assertEquals(25.5, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = new SimpleIntegerProperty(10);
        parameterBibliotecaProperties.turnosRestantesProperty().bindBidirectional(turnosRestantesProperty);

        turnosRestantesProperty.set(20);
        assertEquals(20, parameterBibliotecaProperties.turnosRestantesProperty().get());

        parameterBibliotecaProperties.turnosRestantesProperty().set(30);
        assertEquals(30, turnosRestantesProperty.get());
    }

    @Test
    void testAumentoPorentajeClonProperty() {
        DoubleProperty aumentoPorentajeClonProperty = new SimpleDoubleProperty(10.0);
        parameterBibliotecaProperties.aumentoPorentajeClonProperty().bindBidirectional(aumentoPorentajeClonProperty);

        aumentoPorentajeClonProperty.set(20.0);
        assertEquals(20.0, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());

        parameterBibliotecaProperties.aumentoPorentajeClonProperty().set(30.0);
        assertEquals(30.0, aumentoPorentajeClonProperty.get());
    }
}