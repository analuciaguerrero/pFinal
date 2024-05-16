package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterMontanaPropertiesTest {
    private Montaña originalMontaña;
    private ParameterMontanaProperties parameterMontanaProperties;
    @Test
    void testCommit() {
        originalMontaña = new Montaña();
        parameterMontanaProperties = new ParameterMontanaProperties(originalMontaña);

        parameterMontanaProperties.turnosRestantesProperty().set(5);
        parameterMontanaProperties.disminucionVidaProperty().set(10);
        parameterMontanaProperties.probMontañaProperty().set(0.5);

        parameterMontanaProperties.commit();

        assertEquals(5, originalMontaña.getTurnosRestantes());
        assertEquals(10, originalMontaña.getReduccionVida());
        assertEquals(0.5, originalMontaña.getProbMontaña());
    }

    @Test
    void testRollback() {
        originalMontaña = new Montaña();
        parameterMontanaProperties = new ParameterMontanaProperties(originalMontaña);

        originalMontaña.setTurnosRestantes(3);
        originalMontaña.setReduccionVida(8);
        originalMontaña.setProbMontaña(0.3);

        parameterMontanaProperties.rollback();

        assertEquals(3, parameterMontanaProperties.turnosRestantesProperty().get());
        assertEquals(8, parameterMontanaProperties.disminucionVidaProperty().get());
        assertEquals(0.3, parameterMontanaProperties.probMontañaProperty().get());
    }

    @Test
    void testSetOriginalMontaña() {
        originalMontaña = new Montaña();
        parameterMontanaProperties = new ParameterMontanaProperties(originalMontaña);

        Montaña newMontaña = new Montaña();
        newMontaña.setTurnosRestantes(2);
        newMontaña.setReduccionVida(5);
        newMontaña.setProbMontaña(0.7);

        parameterMontanaProperties.setOriginal(newMontaña);

        assertEquals(2, parameterMontanaProperties.turnosRestantesProperty().get());
        assertEquals(5, parameterMontanaProperties.disminucionVidaProperty().get());
        assertEquals(0.7, parameterMontanaProperties.probMontañaProperty().get());
    }
    @Test
    void testGetOriginal() {
        // Crear una instancia de Montaña para usar como original
        Montaña originalMontaña = new Montaña(/* parámetros del constructor */);

        // Crear una instancia de ParameterMontañaProperties usando la Montaña original
        ParameterMontanaProperties montañaProperties = new ParameterMontanaProperties(originalMontaña);

        // Obtener el original utilizando el método getOriginal()
        Montaña obtenido = montañaProperties.getOriginal();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterMontañaProperties
        assertEquals(originalMontaña, obtenido);
    }
}