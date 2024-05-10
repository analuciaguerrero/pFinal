package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterPozoPropertiesTest {
    private Pozo originalPozo;
    private ParameterPozoProperties parameterPozoProperties;
    @Test
    void testCommit() {
        originalPozo = new Pozo();
        parameterPozoProperties = new ParameterPozoProperties(originalPozo);

        parameterPozoProperties.turnosRestantesProperty().set(5);
        parameterPozoProperties.probPozoProperty().set(0.5);

        parameterPozoProperties.commit();

        assertEquals(5, originalPozo.getTurnosRestantes());
        assertEquals(0.5, originalPozo.getProbPozo());
    }

    @Test
    void testRollback() {
        originalPozo = new Pozo();
        parameterPozoProperties = new ParameterPozoProperties(originalPozo);

        originalPozo.setTurnosRestantes(3);
        originalPozo.setProbPozo(0.3);

        parameterPozoProperties.rollback();

        assertEquals(3, parameterPozoProperties.turnosRestantesProperty().get());
        assertEquals(0.3, parameterPozoProperties.probPozoProperty().get());
    }

    @Test
    void testSetOriginalPozo() {
        originalPozo = new Pozo();
        parameterPozoProperties = new ParameterPozoProperties(originalPozo);

        Pozo newPozo = new Pozo();
        newPozo.setTurnosRestantes(2);
        newPozo.setProbPozo(0.7);

        parameterPozoProperties.setOriginal(newPozo);

        assertEquals(2, parameterPozoProperties.turnosRestantesProperty().get());
        assertEquals(0.7, parameterPozoProperties.probPozoProperty().get());
    }
    @Test
    void testGetOriginal() {
            // Crear una instancia de Pozo para usar como original
        Pozo originalPozo = new Pozo(/* parámetros del constructor */);

        // Crear una instancia de ParameterPozoProperties usando el Pozo original
        ParameterPozoProperties pozoProperties = new ParameterPozoProperties(originalPozo);

        // Obtener el original utilizando el método getOriginal()
        Pozo obtenido = pozoProperties.getOriginal();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterPozoProperties
        assertEquals(originalPozo, obtenido);
    }
}