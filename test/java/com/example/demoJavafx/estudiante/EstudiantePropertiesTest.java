package com.example.demoJavafx.estudiante;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EstudiantePropertiesTest {
    private EstudianteProperties estudianteProperties;
    private EstudianteBasico estudiante;
    private int turno;

    @BeforeEach
    void setUp() {
        estudiante = new EstudianteBasico(1, 0, 0, 1, 100, 50, 30, 1);
        estudianteProperties = new EstudianteProperties(estudiante);
        turno = 1;
    }

    @Test
    void testConstructorConOrigen() {
        assertEquals(estudiante, estudianteProperties.getOrigen());
    }

    @Test
    void testConstructorSinOrigen() {
        EstudianteProperties estudianteProperties2 = new EstudianteProperties();
        assertNull(estudianteProperties2.getOrigen());
    }

    @Test
    void testSetOrigen() {
        EstudianteBasico nuevoEstudiante = new EstudianteBasico(2, 0, 0, 1, 80, 60, 40, 1);
        estudianteProperties.setOrigen(nuevoEstudiante);
        assertEquals(nuevoEstudiante, estudianteProperties.getOrigen());
    }

    @Test
    void testRollback() {
        estudiante.setTiempoDeVida(90, turno);
        estudiante.setProbReproduccion(60, turno);
        estudiante.setProbClonacion(40, turno);
        estudianteProperties.rollback();
        assertEquals(90, estudianteProperties.tiempoDeVidaProperty().get());
        assertEquals(60, estudianteProperties.probReproduccionProperty().get(), 0.001);
        assertEquals(40, estudianteProperties.probClonacionProperty().get(), 0.001);
        assertEquals(50, estudianteProperties.probMuerteProperty().get(), 0.001);
    }

    @Test
    void testCommit() {
        estudianteProperties.tiempoDeVidaProperty().set(80);
        estudianteProperties.probReproduccionProperty().set(70);
        estudianteProperties.probClonacionProperty().set(50);
        estudianteProperties.commit();
        assertEquals(80, estudiante.getTiempoDeVida());
        assertEquals(70, estudiante.getProbReproduccion(), 0.001);
        assertEquals(50, estudiante.getProbClonacion(), 0.001);
    }

    @Test
    void testTiempoDeVidaProperty() {
        IntegerProperty tiempoDeVida = new SimpleIntegerProperty(100);
        estudianteProperties.tiempoDeVidaProperty().set(100);
        assertEquals(100, estudianteProperties.tiempoDeVidaProperty().get());
    }

    @Test
    void testProbReproduccionProperty() {
        DoubleProperty probReproduccion = new SimpleDoubleProperty(60.0);
        estudianteProperties.probReproduccionProperty().set(60.0);
        assertEquals(60.0, estudianteProperties.probReproduccionProperty().get(), 0.001);
    }

    @Test
    void testProbClonacionProperty() {
        DoubleProperty probClonacion = new SimpleDoubleProperty(40.0);
        estudianteProperties.probClonacionProperty().set(40.0);
        assertEquals(40.0, estudianteProperties.probClonacionProperty().get(), 0.001);
    }

    @Test
    void testProbMuerteProperty() {
        DoubleProperty probMuerte = new SimpleDoubleProperty(50.0);
        estudianteProperties.probMuerteProperty().set(50.0);
        assertEquals(50.0, estudianteProperties.probMuerteProperty().get(), 0.001);
    }

    @Test
    void testGetTurno() {
        assertEquals(turno, estudianteProperties.getTurno());
    }
}
