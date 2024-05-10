package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterAguaPropertiesTest {
    private Agua originalAgua;
    private ParameterAguaProperties parameterAguaProperties;
    @Test
    void testCommit() {
        originalAgua = new Agua();
        parameterAguaProperties = new ParameterAguaProperties(originalAgua);
        parameterAguaProperties.turnosRestantesProperty().set(5);
        parameterAguaProperties.aumentoVidaProperty().set(10);
        parameterAguaProperties.probAguaProperty().set(0.5);

        parameterAguaProperties.commit();

        assertEquals(5, originalAgua.getTurnosRestantes());
        assertEquals(10, originalAgua.getAumentoVida());
        assertEquals(0.5, originalAgua.getProbAgua());
    }

    @Test
    void testRollback() {
        originalAgua = new Agua();
        parameterAguaProperties = new ParameterAguaProperties(originalAgua);
        originalAgua.setTurnosRestantes(3);
        originalAgua.setAumentoVida(8);
        originalAgua.setProbAgua(0.3);

        parameterAguaProperties.rollback();

        assertEquals(3, parameterAguaProperties.turnosRestantesProperty().get());
        assertEquals(8, parameterAguaProperties.aumentoVidaProperty().get());
        assertEquals(0.3, parameterAguaProperties.probAguaProperty().get());
    }

    @Test
    void testSetOriginalAgua() {
        originalAgua = new Agua();
        parameterAguaProperties = new ParameterAguaProperties(originalAgua);
        Agua newAgua = new Agua();
        newAgua.setTurnosRestantes(2);
        newAgua.setAumentoVida(5);
        newAgua.setProbAgua(0.7);

        parameterAguaProperties.setOriginalAgua(newAgua);

        assertEquals(2, parameterAguaProperties.turnosRestantesProperty().get());
        assertEquals(5, parameterAguaProperties.aumentoVidaProperty().get());
        assertEquals(0.7, parameterAguaProperties.probAguaProperty().get());
    }
    @Test
    void testGetOriginal() {
        // Crear una instancia de Agua para usar como original
        Agua originalAgua = new Agua(/* parámetros del constructor */);

        // Crear una instancia de ParameterAguaProperties usando el Agua original
        ParameterAguaProperties aguaProperties = new ParameterAguaProperties(originalAgua);

        // Obtener el original utilizando el método getOriginal()
        Agua obtenido = aguaProperties.getOriginalAgua();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterAguaProperties
        assertEquals(originalAgua, obtenido);
    }
}