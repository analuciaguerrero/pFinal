package com.example.demoJavafx.entorno;

import javafx.beans.property.DoubleProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterTesoroPropertiesTest {
    @Test
    void testCommitAndRollback() {
        // Crear una instancia de Tesoro para usar como original
        Tesoro originalTesoro = new Tesoro(/* parámetros del constructor */);

        // Crear una instancia de ParameterTesoroProperties usando el Tesoro original
        ParameterTesoroProperties tesoroProperties = new ParameterTesoroProperties(originalTesoro);

        // Establecer valores en las propiedades
        tesoroProperties.turnosRestantesProperty().set(5);
        tesoroProperties.aumentoPorcetajeRepProperty().set(0.2);
        tesoroProperties.probTesoroProperty().set(0.8);

        // Realizar commit
        tesoroProperties.commit();

        // Verificar que los valores se hayan establecido correctamente en el objeto original
        assertEquals(5, originalTesoro.getTurnosRestantes());
        assertEquals(0.2, originalTesoro.getAumentoProbReproduccion());
        assertEquals(0.8, originalTesoro.getProbTesoro());

        // Modificar valores en las propiedades
        tesoroProperties.turnosRestantesProperty().set(10);
        tesoroProperties.aumentoPorcetajeRepProperty().set(0.5);
        tesoroProperties.probTesoroProperty().set(0.3);

        // Realizar rollback
        tesoroProperties.rollback();

        // Verificar que los valores se hayan restaurado correctamente en el objeto original
        assertEquals(5, originalTesoro.getTurnosRestantes());
        assertEquals(0.2, originalTesoro.getAumentoProbReproduccion());
        assertEquals(0.8, originalTesoro.getProbTesoro());
    }

    @Test
    void testSetOriginal() {
        // Crear una instancia de Tesoro para usar como original
        Tesoro originalTesoro = new Tesoro(/* parámetros del constructor */);

        // Crear una instancia de ParameterTesoroProperties usando el Tesoro original
        ParameterTesoroProperties tesoroProperties = new ParameterTesoroProperties(originalTesoro);

        // Crear un nuevo objeto Tesoro
        Tesoro nuevoTesoro = new Tesoro(/* parámetros del constructor */);

        // Establecer el nuevo objeto como original
        tesoroProperties.setOriginal(nuevoTesoro);

        // Verificar que el original haya sido actualizado correctamente
        assertEquals(nuevoTesoro, tesoroProperties.getOriginal());
    }
}