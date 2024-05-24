package com.example.demoJavafx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatosJuegoPropertiesTest {
    private DatosJuegoProperties datosJuegoProperties;
    private DatosJuego mockDatosJuego;

    @BeforeEach
    void setUp() {
        mockDatosJuego = new DatosJuego();  // Suponiendo que DatosJuego tiene un constructor por defecto
        datosJuegoProperties = new DatosJuegoProperties(mockDatosJuego);
    }

    @Test
    void testSetDato() {
        DatosJuego newDatosJuego = new DatosJuego();
        datosJuegoProperties.setDato(newDatosJuego);
        assertEquals(newDatosJuego, datosJuegoProperties.getDato());
    }

    @Test
    void testCommit() {
        datosJuegoProperties.FilasDelTableroProperty().set(10);
        datosJuegoProperties.ColumnasDelTableroProperty().set(20);
        datosJuegoProperties.TurnosVidaInicialesProperty().set(5);
        datosJuegoProperties.ProbReproEstudianteProperty().setValue(0.3);
        datosJuegoProperties.ProbClonEstudianteProperty().setValue(0.4);
        datosJuegoProperties.ProbMejorarANormalProperty().setValue(0.5);
        datosJuegoProperties.ProbMejorarAAvanzadoProperty().setValue(0.6);
        datosJuegoProperties.ProbRecursoProperty().set(0.7);
        datosJuegoProperties.TurnosInicialesRecursoProperty().set(8);
        datosJuegoProperties.ProbAguaProperty().setValue(0.9);
        datosJuegoProperties.ProbComidaProperty().setValue(1.0);
        datosJuegoProperties.ProbMontañaProperty().setValue(1.1);
        datosJuegoProperties.ProbTesoroProperty().setValue(1.2);
        datosJuegoProperties.ProbBibliotecaProperty().setValue(1.3);
        datosJuegoProperties.ProbPozoProperty().setValue(1.4);
        datosJuegoProperties.AumentoTurnosAguaProperty().set(2);
        datosJuegoProperties.AumentoTurnosComidaProperty().set(3);
        datosJuegoProperties.ReduccionTurnosMontañaProperty().set(4);
        datosJuegoProperties.AumentoProbReproProperty().setValue(0.2);
        datosJuegoProperties.AumentoProbClonProperty().setValue(0.3);

        datosJuegoProperties.commit();

        assertEquals(10, mockDatosJuego.getFilasDelTablero());
        assertEquals(20, mockDatosJuego.getColumnasDelTablero());
        assertEquals(5, mockDatosJuego.getTurnosVidaIniciales());
        assertEquals(0.3, mockDatosJuego.getProbReproduccionEstudiante());
        assertEquals(0.4, mockDatosJuego.getProbClonacionEstudiante());
        assertEquals(0.5, mockDatosJuego.getProbMejorarANormal());
        assertEquals(0.6, mockDatosJuego.getProbMejorarAAvanzado());
        assertEquals(0.7, mockDatosJuego.getProbRecurso());
        assertEquals(8, mockDatosJuego.getTurnosIniciales());
        assertEquals(0.9, mockDatosJuego.getProbAgua());
        assertEquals(1.0, mockDatosJuego.getProbComida());
        assertEquals(1.1, mockDatosJuego.getProbMontaña());
        assertEquals(1.2, mockDatosJuego.getProbTesoro());
        assertEquals(1.3, mockDatosJuego.getProbBiblioteca());
        assertEquals(1.4, mockDatosJuego.getProbPozo());
        assertEquals(2, mockDatosJuego.getAumentoVidaAgua());
        assertEquals(3, mockDatosJuego.getAumentoVidaComida());
        assertEquals(4, mockDatosJuego.getReduccionVidaMontaña());
        assertEquals(0.2, mockDatosJuego.getAumentoProbReproduccion());
        assertEquals(0.3, mockDatosJuego.getAumentoProbClonacion());
    }

    @Test
    void testRollback() {
        // Initialize mockDatosJuego with some values
        mockDatosJuego.setFilasDelTablero(10);
        mockDatosJuego.setColumnasDelTablero(20);
        mockDatosJuego.setTurnosVidaIniciales(5);
        mockDatosJuego.setProbReproduccionEstudiante(0.3);
        mockDatosJuego.setProbClonacionEstudiante(0.4);
        mockDatosJuego.setProbMejorarANormal(0.5);
        mockDatosJuego.setProbMejorarAAvanzado(0.6);
        mockDatosJuego.setProbRecurso(0.7);
        mockDatosJuego.setTurnosIniciales(8);
        mockDatosJuego.setProbAgua(0.9);
        mockDatosJuego.setProbComida(1.0);
        mockDatosJuego.setProbMontaña(1.1);
        mockDatosJuego.setProbTesoro(1.2);
        mockDatosJuego.setProbBiblioteca(1.3);
        mockDatosJuego.setProbPozo(1.4);
        mockDatosJuego.setAumentoVidaAgua(2);
        mockDatosJuego.setAumentoVidaComida(3);
        mockDatosJuego.setReduccionVidaMontaña(4);
        mockDatosJuego.setAumentoProbReproduccion(0.2);
        mockDatosJuego.setAumentoProbClonacion(0.3);

        datosJuegoProperties.rollback(null);

        assertEquals(10, datosJuegoProperties.FilasDelTableroProperty().get());
        assertEquals(20, datosJuegoProperties.ColumnasDelTableroProperty().get());
        assertEquals(5, datosJuegoProperties.TurnosVidaInicialesProperty().get());
        assertEquals(0.3, datosJuegoProperties.ProbReproEstudianteProperty().getValue());
        assertEquals(0.4, datosJuegoProperties.ProbClonEstudianteProperty().getValue());
        assertEquals(0.5, datosJuegoProperties.ProbMejorarANormalProperty().getValue());
        assertEquals(0.6, datosJuegoProperties.ProbMejorarAAvanzadoProperty().getValue());
        assertEquals(0.7, datosJuegoProperties.ProbRecursoProperty().get());
        assertEquals(8, datosJuegoProperties.TurnosInicialesRecursoProperty().get());
        assertEquals(0.9, datosJuegoProperties.ProbAguaProperty().getValue());
        assertEquals(1.0, datosJuegoProperties.ProbComidaProperty().getValue());
        assertEquals(1.1, datosJuegoProperties.ProbMontañaProperty().getValue());
        assertEquals(1.2, datosJuegoProperties.ProbTesoroProperty().getValue());
        assertEquals(1.3, datosJuegoProperties.ProbBibliotecaProperty().getValue());
        assertEquals(1.4, datosJuegoProperties.ProbPozoProperty().getValue());
        assertEquals(2, datosJuegoProperties.AumentoTurnosAguaProperty().get());
        assertEquals(3, datosJuegoProperties.AumentoTurnosComidaProperty().get());
        assertEquals(4, datosJuegoProperties.ReduccionTurnosMontañaProperty().get());
        assertEquals(0.2, datosJuegoProperties.AumentoProbReproProperty().getValue());
        assertEquals(0.3, datosJuegoProperties.AumentoProbClonProperty().getValue());
    }
}