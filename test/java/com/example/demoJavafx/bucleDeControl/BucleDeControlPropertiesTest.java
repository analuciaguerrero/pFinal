package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucleDeControlPropertiesTest {
    private static final Logger log = LogManager.getLogger();
    private BucleDeControlProperties bucleDeControlProperties;
    private BucleDeControl bucleDeControl;
    private DatosJuego datosJuego;
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego();
        tablero = new Tablero(10, 10, datosJuego);
        bucleDeControl = new BucleDeControl(tablero, datosJuego);
        bucleDeControlProperties = new BucleDeControlProperties(bucleDeControl);
    }

    @Test
    void testCommit() {
        assertDoesNotThrow(() -> bucleDeControlProperties.commit());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");
        log.info("Fin del método de arranque de la aplicación para elegir los parámetros");
    }

    @Test
    void testRollback() {
        assertDoesNotThrow(() -> bucleDeControlProperties.rollback());
        log.trace("Enviando una traza de ejecución");
        log.debug("Enviado un debug");
        log.info("Enviando un info");
        log.warn("Enviando un aviso");
        log.error("Enviando un error");
        log.fatal("Enviando una explosión fatal");
        log.info("Fin del método de arranque de la aplicación para elegir los parámetros");
    }

    @Test
    void testGetOriginal() {
        assertEquals(bucleDeControl, bucleDeControlProperties.getOriginal());
    }

    @Test
    void testSetOriginal() {
        BucleDeControl newBucleDeControl = new BucleDeControl(tablero, datosJuego);
        bucleDeControlProperties.setOriginal(newBucleDeControl);
        assertEquals(newBucleDeControl, bucleDeControlProperties.getOriginal());
    }

    @Test
    void testTableroColumnasProperty() {
        IntegerProperty property = bucleDeControlProperties.tableroColumnasProperty();
        assertNotNull(property);
        assertEquals(property.get(), bucleDeControlProperties.tableroColumnasProperty().get());
    }

    @Test
    void testTableroFilasProperty() {
        IntegerProperty property = bucleDeControlProperties.tableroFilasProperty();
        assertNotNull(property);
        assertEquals(property.get(), bucleDeControlProperties.tableroFilasProperty().get());
    }

    @Test
    void testGetTurnoProperty() {
        IntegerProperty turnoProperty = bucleDeControlProperties.getTurnoProperty();
        assertNotNull(turnoProperty);
        assertEquals(turnoProperty.get(), bucleDeControlProperties.getTurnoProperty().get());
    }

    @Test
    void testSetTurnoProperty() {
        IntegerProperty newTurnoProperty = new SimpleIntegerProperty(5);
        bucleDeControlProperties.setTurnoProperty(newTurnoProperty);
        assertEquals(newTurnoProperty, bucleDeControlProperties.getTurnoProperty());
    }

    @Test
    void testGetFilas() {
        datosJuego.setFilasDelTablero(10);
        assertEquals(10, bucleDeControlProperties.getFilas());
    }

    @Test
    void testGetColumnas() {
        datosJuego.setColumnasDelTablero(10);
        assertEquals(10, bucleDeControlProperties.getColumnas());
    }

    @Test
    void testGetCelda() {
        Celda celda = new Celda(0, 0);
        bucleDeControl.setCelda(celda);
        assertEquals(celda, bucleDeControlProperties.getCelda());
    }
}