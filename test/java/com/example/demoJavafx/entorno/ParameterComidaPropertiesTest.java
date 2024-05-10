package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

class ParameterComidaPropertiesTest {
    private Comida originalComida;
    private ParameterComidaProperties parameterComidaProperties;
    @Test
    void testCommit() {
        originalComida = new Comida();
        parameterComidaProperties = new ParameterComidaProperties(originalComida);

        parameterComidaProperties.turnosRestantesProperty().set(5);
        parameterComidaProperties.aumentoVidaProperty().set(10);
        parameterComidaProperties.probComidaProperty().set(0.5);

        parameterComidaProperties.commit();

        assertEquals(5, originalComida.getTurnosRestantes());
        assertEquals(10, originalComida.getAumentoVida());
        assertEquals(0.5, originalComida.getProbComida());
    }

    @Test
    void testRollback() {
        originalComida = new Comida();
        parameterComidaProperties = new ParameterComidaProperties(originalComida);

        originalComida.setTurnosRestantes(3);
        originalComida.setAumentoVida(8);
        originalComida.setProbComida(0.3);

        parameterComidaProperties.rollback();

        assertEquals(3, parameterComidaProperties.turnosRestantesProperty().get());
        assertEquals(8, parameterComidaProperties.aumentoVidaProperty().get());
        assertEquals(0.3, parameterComidaProperties.probComidaProperty().get());
    }

    @Test
    void testSetOriginalComida() {
        originalComida = new Comida();
        parameterComidaProperties = new ParameterComidaProperties(originalComida);

        Comida newComida = new Comida();
        newComida.setTurnosRestantes(2);
        newComida.setAumentoVida(5);
        newComida.setProbComida(0.7);

        parameterComidaProperties.setOriginal(newComida);

        assertEquals(2, parameterComidaProperties.turnosRestantesProperty().get());
        assertEquals(5, parameterComidaProperties.aumentoVidaProperty().get());
        assertEquals(0.7, parameterComidaProperties.probComidaProperty().get());
    }
    @Test
    void testGetOriginal() {
        // Crear una instancia de Comida para usar como original
        Comida originalComida = new Comida(/* parámetros del constructor */);

        // Crear una instancia de ParameterComidaProperties usando la Comida original
        ParameterComidaProperties comidaProperties = new ParameterComidaProperties(originalComida);

        // Obtener el original utilizando el método getOriginal()
        Comida obtenido = comidaProperties.getOriginal();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterComidaProperties
        assertEquals(originalComida, obtenido);
    }
}