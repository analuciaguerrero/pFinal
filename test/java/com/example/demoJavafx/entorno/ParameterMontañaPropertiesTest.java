package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterMontañaPropertiesTest {
    private Montaña originalMontaña;
    private ParameterMontañaProperties parameterMontañaProperties;
    @Test
    void testCommit() {
        originalMontaña = new Montaña();
        parameterMontañaProperties = new ParameterMontañaProperties(originalMontaña);

        parameterMontañaProperties.turnosRestantesProperty().set(5);
        parameterMontañaProperties.disminucionVidaProperty().set(10);
        parameterMontañaProperties.probMontañaProperty().set(0.5);

        parameterMontañaProperties.commit();

        assertEquals(5, originalMontaña.getTurnosRestantes());
        assertEquals(10, originalMontaña.getReduccionVida());
        assertEquals(0.5, originalMontaña.getProbMontaña());
    }

    @Test
    void testRollback() {
        originalMontaña = new Montaña();
        parameterMontañaProperties = new ParameterMontañaProperties(originalMontaña);

        originalMontaña.setTurnosRestantes(3);
        originalMontaña.setReduccionVida(8);
        originalMontaña.setProbMontaña(0.3);

        parameterMontañaProperties.rollback();

        assertEquals(3, parameterMontañaProperties.turnosRestantesProperty().get());
        assertEquals(8, parameterMontañaProperties.disminucionVidaProperty().get());
        assertEquals(0.3, parameterMontañaProperties.probMontañaProperty().get());
    }

    @Test
    void testSetOriginalMontaña() {
        originalMontaña = new Montaña();
        parameterMontañaProperties = new ParameterMontañaProperties(originalMontaña);

        Montaña newMontaña = new Montaña();
        newMontaña.setTurnosRestantes(2);
        newMontaña.setReduccionVida(5);
        newMontaña.setProbMontaña(0.7);

        parameterMontañaProperties.setOriginal(newMontaña);

        assertEquals(2, parameterMontañaProperties.turnosRestantesProperty().get());
        assertEquals(5, parameterMontañaProperties.disminucionVidaProperty().get());
        assertEquals(0.7, parameterMontañaProperties.probMontañaProperty().get());
    }
    @Test
    void testGetOriginal() {
        // Crear una instancia de Montaña para usar como original
        Montaña originalMontaña = new Montaña(/* parámetros del constructor */);

        // Crear una instancia de ParameterMontañaProperties usando la Montaña original
        ParameterMontañaProperties montañaProperties = new ParameterMontañaProperties(originalMontaña);

        // Obtener el original utilizando el método getOriginal()
        Montaña obtenido = montañaProperties.getOriginal();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterMontañaProperties
        assertEquals(originalMontaña, obtenido);
    }
}