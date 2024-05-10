package com.example.demoJavafx.entorno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterBibliotecaPropertiesTest {
    private Biblioteca originalBiblioteca;
    private ParameterBibliotecaProperties parameterBibliotecaProperties;
    @Test
    void testCommit() {
        originalBiblioteca = new Biblioteca();
        parameterBibliotecaProperties = new ParameterBibliotecaProperties(originalBiblioteca);

        parameterBibliotecaProperties.turnosRestantesProperty().set(5);
        parameterBibliotecaProperties.aumentoPorentajeClonProperty().set(0.2);
        parameterBibliotecaProperties.probBibliotecaProperty().set(0.7);

        parameterBibliotecaProperties.commit();

        assertEquals(5, originalBiblioteca.getTurnosRestantes());
        assertEquals(0.2, originalBiblioteca.getAumentoProbClonacion());
        assertEquals(0.7, originalBiblioteca.getProbBiblioteca());
    }

    @Test
    void testRollback() {
        originalBiblioteca = new Biblioteca();
        parameterBibliotecaProperties = new ParameterBibliotecaProperties(originalBiblioteca);

        originalBiblioteca.setTurnosRestantes(3);
        originalBiblioteca.setAumentoProbClonacion(0.5);
        originalBiblioteca.setProbBiblioteca(0.9);

        parameterBibliotecaProperties.rollback();

        assertEquals(3, parameterBibliotecaProperties.turnosRestantesProperty().get());
        assertEquals(0.5, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());
        assertEquals(0.9, parameterBibliotecaProperties.probBibliotecaProperty().get());
    }

    @Test
    void testSetOriginalBiblioteca() {
        originalBiblioteca = new Biblioteca();
        parameterBibliotecaProperties = new ParameterBibliotecaProperties(originalBiblioteca);

        Biblioteca newBiblioteca = new Biblioteca();
        newBiblioteca.setTurnosRestantes(2);
        newBiblioteca.setAumentoProbClonacion(0.3);
        newBiblioteca.setProbBiblioteca(0.6);

        parameterBibliotecaProperties.setOriginal(newBiblioteca);

        assertEquals(2, parameterBibliotecaProperties.turnosRestantesProperty().get());
        assertEquals(0.3, parameterBibliotecaProperties.aumentoPorentajeClonProperty().get());
        assertEquals(0.6, parameterBibliotecaProperties.probBibliotecaProperty().get());
    }
    @Test
    void testGetOriginal() {
        // Crear una instancia de Biblioteca para usar como original
        Biblioteca originalBiblioteca = new Biblioteca(/* parámetros del constructor */);

        // Crear una instancia de ParameterBibliotecaProperties usando la Biblioteca original
        ParameterBibliotecaProperties bibliotecaProperties = new ParameterBibliotecaProperties(originalBiblioteca);

        // Obtener el original utilizando el método getOriginal()
        Biblioteca obtenido = bibliotecaProperties.getOriginal();

        // Verificar si el original obtenido es el mismo que el original utilizado en la instancia de ParameterBibliotecaProperties
        assertEquals(originalBiblioteca, obtenido);
    }
}