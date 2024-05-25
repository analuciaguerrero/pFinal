package com.example.demoJavafx;

import com.example.demoJavafx.excepciones.VentanaNoEsperada;
import com.example.demoJavafx.tablero.Tablero;
import javafx.scene.control.Tab;
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
    void testGetDato() {
        assertEquals(mockDatosJuego, datosJuegoProperties.getDato());
    }

    @Test
    void testSetDato() {
        DatosJuego newDatosJuego = new DatosJuego();
        datosJuegoProperties.setDato(newDatosJuego);
        assertEquals(newDatosJuego, datosJuegoProperties.getDato());
    }

    @Test
    void testCommit() {
        datosJuegoProperties.FilasDelTableroProperty().setValue(10);
        datosJuegoProperties.ColumnasDelTableroProperty().setValue(20);
        datosJuegoProperties.TurnosVidaInicialesProperty().set(5);
        datosJuegoProperties.TurnosRestantesAguaProperty().set(6);
        datosJuegoProperties.TurnosRestantesComidaProperty().set(7);
        datosJuegoProperties.TurnosRestantesMontanaProperty().set(8);
        datosJuegoProperties.TurnosRestantesBibliotecaProperty().set(9);
        datosJuegoProperties.TurnosRestantesTesoroProperty().set(10);
        datosJuegoProperties.TurnosRestantesPozoProperty().set(11);
        datosJuegoProperties.ProbReproEstudianteProperty().setValue(0.3);
        datosJuegoProperties.ProbClonEstudianteProperty().setValue(0.4);
        datosJuegoProperties.ProbMejorarANormalProperty().setValue(0.5);
        datosJuegoProperties.ProbMejorarAAvanzadoProperty().setValue(0.6);
        datosJuegoProperties.ProbAguaProperty().setValue(0.7);
        datosJuegoProperties.ProbComidaProperty().setValue(0.8);
        datosJuegoProperties.ProbMontañaProperty().setValue(0.9);
        datosJuegoProperties.ProbTesoroProperty().setValue(1.0);
        datosJuegoProperties.ProbBibliotecaProperty().setValue(1.1);
        datosJuegoProperties.ProbPozoProperty().setValue(1.2);
        datosJuegoProperties.AumentoTurnosAguaProperty().setValue(2.1);
        datosJuegoProperties.AumentoTurnosComidaProperty().setValue(3.1);
        datosJuegoProperties.ReduccionTurnosMontañaProperty().setValue(4.1);
        datosJuegoProperties.AumentoProbReproProperty().setValue(0.2);
        datosJuegoProperties.AumentoProbClonProperty().setValue(0.3);

        datosJuegoProperties.commit();

        assertEquals(10, mockDatosJuego.getFilasDelTablero());
        assertEquals(20, mockDatosJuego.getColumnasDelTablero());
        assertEquals(5, mockDatosJuego.getTurnosVidaIniciales());
        assertEquals(6, mockDatosJuego.getTurnosRestantesAgua());
        assertEquals(7, mockDatosJuego.getTurnosRestantesComida());
        assertEquals(8, mockDatosJuego.getTurnosRestantesMontana());
        assertEquals(9, mockDatosJuego.getTurnosRestantesBiblioteca());
        assertEquals(10, mockDatosJuego.getTurnosRestantesTesoro());
        assertEquals(11, mockDatosJuego.getTurnosRestantesPozo());
        assertEquals(0.3, mockDatosJuego.getProbReproduccionEstudiante());
        assertEquals(0.4, mockDatosJuego.getProbClonacionEstudiante());
        assertEquals(0.5, mockDatosJuego.getProbMejorarANormal());
        assertEquals(0.6, mockDatosJuego.getProbMejorarAAvanzado());
        assertEquals(0.7, mockDatosJuego.getProbAgua());
        assertEquals(0.8, mockDatosJuego.getProbComida());
        assertEquals(0.9, mockDatosJuego.getProbMontaña());
        assertEquals(1.0, mockDatosJuego.getProbTesoro());
        assertEquals(1.1, mockDatosJuego.getProbBiblioteca());
        assertEquals(1.2, mockDatosJuego.getProbPozo());
        assertEquals(2.1, mockDatosJuego.getAumentoVidaAgua());
        assertEquals(3.1, mockDatosJuego.getAumentoVidaComida());
        assertEquals(4.1, mockDatosJuego.getReduccionVidaMontaña());
        assertEquals(0.2, mockDatosJuego.getAumentoProbReproduccion());
        assertEquals(0.3, mockDatosJuego.getAumentoProbClonacion());
    }

    @Test
    void testRollback() {
        mockDatosJuego.setFilasDelTablero(10);
        mockDatosJuego.setColumnasDelTablero(20);
        mockDatosJuego.setTurnosVidaIniciales(5);
        mockDatosJuego.setTurnosRestantesAgua(6);
        mockDatosJuego.setTurnosRestantesComida(7);
        mockDatosJuego.setTurnosRestantesMontana(8);
        mockDatosJuego.setTurnosRestantesBiblioteca(9);
        mockDatosJuego.setTurnosRestantesTesoro(10);
        mockDatosJuego.setTurnosRestantesPozo(11);
        mockDatosJuego.setProbReproduccionEstudiante(0.3);
        mockDatosJuego.setProbClonacionEstudiante(0.4);
        mockDatosJuego.setProbMejorarANormal(0.5);
        mockDatosJuego.setProbMejorarAAvanzado(0.6);
        mockDatosJuego.setProbAgua(0.7);
        mockDatosJuego.setProbComida(0.8);
        mockDatosJuego.setProbMontaña(0.9);
        mockDatosJuego.setProbTesoro(1.0);
        mockDatosJuego.setProbBiblioteca(1.1);
        mockDatosJuego.setProbPozo(1.2);
        mockDatosJuego.setAumentoVidaAgua(2.1);
        mockDatosJuego.setAumentoVidaComida(3.1);
        mockDatosJuego.setReduccionVidaMontaña(4.1);
        mockDatosJuego.setAumentoProbReproduccion(0.2);
        mockDatosJuego.setAumentoProbClonacion(0.3);

        datosJuegoProperties.rollback(null);

        assertEquals(10, datosJuegoProperties.FilasDelTableroProperty().getValue());
        assertEquals(20, datosJuegoProperties.ColumnasDelTableroProperty().getValue());
        assertEquals(5, datosJuegoProperties.TurnosVidaInicialesProperty().get());
        assertEquals(6, datosJuegoProperties.TurnosRestantesAguaProperty().get());
        assertEquals(7, datosJuegoProperties.TurnosRestantesComidaProperty().get());
        assertEquals(8, datosJuegoProperties.TurnosRestantesMontanaProperty().get());
        assertEquals(9, datosJuegoProperties.TurnosRestantesBibliotecaProperty().get());
        assertEquals(10, datosJuegoProperties.TurnosRestantesTesoroProperty().get());
        assertEquals(11, datosJuegoProperties.TurnosRestantesPozoProperty().get());
        assertEquals(0.3, datosJuegoProperties.ProbReproEstudianteProperty().getValue());
        assertEquals(0.4, datosJuegoProperties.ProbClonEstudianteProperty().getValue());
        assertEquals(0.5, datosJuegoProperties.ProbMejorarANormalProperty().getValue());
        assertEquals(0.6, datosJuegoProperties.ProbMejorarAAvanzadoProperty().getValue());
        assertEquals(0.7, datosJuegoProperties.ProbAguaProperty().getValue());
        assertEquals(0.8, datosJuegoProperties.ProbComidaProperty().getValue());
        assertEquals(0.9, datosJuegoProperties.ProbMontañaProperty().getValue());
        assertEquals(1.0, datosJuegoProperties.ProbTesoroProperty().getValue());
        assertEquals(1.1, datosJuegoProperties.ProbBibliotecaProperty().getValue());
        assertEquals(1.2, datosJuegoProperties.ProbPozoProperty().getValue());
        assertEquals(2.1, datosJuegoProperties.AumentoTurnosAguaProperty().getValue());
        assertEquals(3.1, datosJuegoProperties.AumentoTurnosComidaProperty().getValue());
        assertEquals(4.1, datosJuegoProperties.ReduccionTurnosMontañaProperty().getValue());
        assertEquals(0.2, datosJuegoProperties.AumentoProbReproProperty().getValue());
        assertEquals(0.3, datosJuegoProperties.AumentoProbClonProperty().getValue());
    }

    @Test
    void testProperties() {
        assertNotNull(datosJuegoProperties.TurnosVidaInicialesProperty());
        assertNotNull(datosJuegoProperties.ProbReproEstudianteProperty());
        assertNotNull(datosJuegoProperties.ProbClonEstudianteProperty());
        assertNotNull(datosJuegoProperties.ProbMejorarANormalProperty());
        assertNotNull(datosJuegoProperties.ProbMejorarAAvanzadoProperty());
        assertNotNull(datosJuegoProperties.ProbAguaProperty());
        assertNotNull(datosJuegoProperties.ProbComidaProperty());
        assertNotNull(datosJuegoProperties.ProbMontañaProperty());
        assertNotNull(datosJuegoProperties.ProbTesoroProperty());
        assertNotNull(datosJuegoProperties.ProbBibliotecaProperty());
        assertNotNull(datosJuegoProperties.ProbPozoProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesAguaProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesComidaProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesMontanaProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesBibliotecaProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesTesoroProperty());
        assertNotNull(datosJuegoProperties.TurnosRestantesPozoProperty());
        assertNotNull(datosJuegoProperties.AumentoTurnosAguaProperty());
        assertNotNull(datosJuegoProperties.AumentoTurnosComidaProperty());
        assertNotNull(datosJuegoProperties.ReduccionTurnosMontañaProperty());
        assertNotNull(datosJuegoProperties.AumentoProbReproProperty());
        assertNotNull(datosJuegoProperties.AumentoProbClonProperty());
        assertNotNull(datosJuegoProperties.FilasDelTableroProperty());
        assertNotNull(datosJuegoProperties.ColumnasDelTableroProperty());
    }

    @Test
    void testRollbackEstudiantes() {
        mockDatosJuego.setTurnosVidaIniciales(5);
        mockDatosJuego.setProbReproduccionEstudiante(0.3);
        mockDatosJuego.setProbClonacionEstudiante(0.4);
        mockDatosJuego.setProbMejorarANormal(0.5);
        mockDatosJuego.setProbMejorarAAvanzado(0.6);

        datosJuegoProperties.rollback(null);

        assertEquals(5, datosJuegoProperties.TurnosVidaInicialesProperty().get());
        assertEquals(0.3, datosJuegoProperties.ProbReproEstudianteProperty().getValue());
        assertEquals(0.4, datosJuegoProperties.ProbClonEstudianteProperty().getValue());
        assertEquals(0.5, datosJuegoProperties.ProbMejorarANormalProperty().getValue());
        assertEquals(0.6, datosJuegoProperties.ProbMejorarAAvanzadoProperty().getValue());
    }

    @Test
    void testRollbackRecursos() {
        mockDatosJuego.setProbAgua(0.7);
        mockDatosJuego.setProbComida(0.8);
        mockDatosJuego.setProbMontaña(0.9);
        mockDatosJuego.setProbTesoro(1.0);
        mockDatosJuego.setProbBiblioteca(1.1);
        mockDatosJuego.setProbPozo(1.2);
        mockDatosJuego.setAumentoVidaAgua(2.1);
        mockDatosJuego.setAumentoVidaComida(3.1);
        mockDatosJuego.setReduccionVidaMontaña(4.1);
        mockDatosJuego.setAumentoProbReproduccion(0.2);
        mockDatosJuego.setAumentoProbClonacion(0.3);
        mockDatosJuego.setTurnosRestantesAgua(6);
        mockDatosJuego.setTurnosRestantesComida(7);
        mockDatosJuego.setTurnosRestantesMontana(8);
        mockDatosJuego.setTurnosRestantesBiblioteca(9);
        mockDatosJuego.setTurnosRestantesTesoro(10);
        mockDatosJuego.setTurnosRestantesPozo(11);

        datosJuegoProperties.rollback(null);

        assertEquals(0.7, datosJuegoProperties.ProbAguaProperty().getValue());
        assertEquals(0.8, datosJuegoProperties.ProbComidaProperty().getValue());
        assertEquals(0.9, datosJuegoProperties.ProbMontañaProperty().getValue());
        assertEquals(1.0, datosJuegoProperties.ProbTesoroProperty().getValue());
        assertEquals(1.1, datosJuegoProperties.ProbBibliotecaProperty().getValue());
        assertEquals(1.2, datosJuegoProperties.ProbPozoProperty().getValue());
        assertEquals(2.1, datosJuegoProperties.AumentoTurnosAguaProperty().getValue());
        assertEquals(3.1, datosJuegoProperties.AumentoTurnosComidaProperty().getValue());
        assertEquals(4.1, datosJuegoProperties.ReduccionTurnosMontañaProperty().getValue());
        assertEquals(0.2, datosJuegoProperties.AumentoProbReproProperty().getValue());
        assertEquals(0.3, datosJuegoProperties.AumentoProbClonProperty().getValue());
        assertEquals(6, datosJuegoProperties.TurnosRestantesAguaProperty().get());
        assertEquals(7, datosJuegoProperties.TurnosRestantesComidaProperty().get());
        assertEquals(8, datosJuegoProperties.TurnosRestantesMontanaProperty().get());
        assertEquals(9, datosJuegoProperties.TurnosRestantesBibliotecaProperty().get());
        assertEquals(10, datosJuegoProperties.TurnosRestantesTesoroProperty().get());
        assertEquals(11, datosJuegoProperties.TurnosRestantesPozoProperty().get());
    }

    @Test
    void testRollbackTablero() {
        mockDatosJuego.setFilasDelTablero(10);
        mockDatosJuego.setColumnasDelTablero(20);

        datosJuegoProperties.rollback(null);

        assertEquals(10, datosJuegoProperties.FilasDelTableroProperty().getValue());
        assertEquals(20, datosJuegoProperties.ColumnasDelTableroProperty().getValue());
    }

    @Test
    void testRollbackWithTab() {
        mockDatosJuego.setFilasDelTablero(10);
        mockDatosJuego.setColumnasDelTablero(20);
        mockDatosJuego.setTurnosVidaIniciales(5);
        mockDatosJuego.setProbReproduccionEstudiante(0.3);
        mockDatosJuego.setProbClonacionEstudiante(0.4);
        mockDatosJuego.setProbMejorarANormal(0.5);
        mockDatosJuego.setProbMejorarAAvanzado(0.6);
        mockDatosJuego.setProbAgua(0.7);
        mockDatosJuego.setProbComida(0.8);
        mockDatosJuego.setProbMontaña(0.9);
        mockDatosJuego.setProbTesoro(1.0);
        mockDatosJuego.setProbBiblioteca(1.1);
        mockDatosJuego.setProbPozo(1.2);
        mockDatosJuego.setAumentoVidaAgua(2.1);
        mockDatosJuego.setAumentoVidaComida(3.1);
        mockDatosJuego.setReduccionVidaMontaña(4.1);
        mockDatosJuego.setAumentoProbReproduccion(0.2);
        mockDatosJuego.setAumentoProbClonacion(0.3);
        mockDatosJuego.setTurnosRestantesAgua(6);
        mockDatosJuego.setTurnosRestantesComida(7);
        mockDatosJuego.setTurnosRestantesMontana(8);
        mockDatosJuego.setTurnosRestantesBiblioteca(9);
        mockDatosJuego.setTurnosRestantesTesoro(10);
        mockDatosJuego.setTurnosRestantesPozo(11);

        Tab estudiantesTab = new Tab("Estudiantes");
        datosJuegoProperties.rollback(estudiantesTab);
        assertEquals(5, datosJuegoProperties.TurnosVidaInicialesProperty().get());
        assertEquals(0.3, datosJuegoProperties.ProbReproEstudianteProperty().getValue());
        assertEquals(0.4, datosJuegoProperties.ProbClonEstudianteProperty().getValue());
        assertEquals(0.5, datosJuegoProperties.ProbMejorarANormalProperty().getValue());
        assertEquals(0.6, datosJuegoProperties.ProbMejorarAAvanzadoProperty().getValue());

        Tab recursosTab = new Tab("Recursos");
        datosJuegoProperties.rollback(recursosTab);
        assertEquals(0.7, datosJuegoProperties.ProbAguaProperty().getValue());
        assertEquals(0.8, datosJuegoProperties.ProbComidaProperty().getValue());
        assertEquals(0.9, datosJuegoProperties.ProbMontañaProperty().getValue());
        assertEquals(1.0, datosJuegoProperties.ProbTesoroProperty().getValue());
        assertEquals(1.1, datosJuegoProperties.ProbBibliotecaProperty().getValue());
        assertEquals(1.2, datosJuegoProperties.ProbPozoProperty().getValue());
        assertEquals(2.1, datosJuegoProperties.AumentoTurnosAguaProperty().getValue());
        assertEquals(3.1, datosJuegoProperties.AumentoTurnosComidaProperty().getValue());
        assertEquals(4.1, datosJuegoProperties.ReduccionTurnosMontañaProperty().getValue());
        assertEquals(0.2, datosJuegoProperties.AumentoProbReproProperty().getValue());
        assertEquals(0.3, datosJuegoProperties.AumentoProbClonProperty().getValue());
        assertEquals(6, datosJuegoProperties.TurnosRestantesAguaProperty().get());
        assertEquals(7, datosJuegoProperties.TurnosRestantesComidaProperty().get());
        assertEquals(8, datosJuegoProperties.TurnosRestantesMontanaProperty().get());
        assertEquals(9, datosJuegoProperties.TurnosRestantesBibliotecaProperty().get());
        assertEquals(10, datosJuegoProperties.TurnosRestantesTesoroProperty().get());
        assertEquals(11, datosJuegoProperties.TurnosRestantesPozoProperty().get());

        Tab tableroTab = new Tab("Tablero");
        datosJuegoProperties.rollback(tableroTab);
        assertEquals(10, datosJuegoProperties.FilasDelTableroProperty().getValue());
        assertEquals(20, datosJuegoProperties.ColumnasDelTableroProperty().getValue());

        Tab invalidTab = new Tab("Invalid");
        assertThrows(VentanaNoEsperada.class, () -> datosJuegoProperties.rollback(invalidTab));
    }
}