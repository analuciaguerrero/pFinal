package com.example.demoJavafx.bucleDeControl;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Agua;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BucleDeControlTest {
    private BucleDeControl bucleDeControl;
    private DatosJuego datosJuego;
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego();
        tablero = new Tablero(10, 10, datosJuego);
        bucleDeControl = new BucleDeControl(tablero, datosJuego);
    }

    @Test
    void testGetCelda() {
        assertNull(bucleDeControl.getCelda());
    }

    @Test
    void testSetCelda() {
        Celda celda = new Celda(0, 0);
        bucleDeControl.setCelda(celda);
        assertEquals(celda, bucleDeControl.getCelda());
    }

    @Test
    void testGetDato() {
        assertEquals(datosJuego, bucleDeControl.getDato());
    }

    @Test
    void testSetDato() {
        DatosJuego newDatosJuego = new DatosJuego();
        bucleDeControl.setDato(newDatosJuego);
        assertEquals(newDatosJuego, bucleDeControl.getDato());
    }

    @Test
    void testGetTurnoProperty() {
        IntegerProperty turnoProperty = new SimpleIntegerProperty();
        assertEquals(turnoProperty.get(), bucleDeControl.getTurnoProperty().get());
    }

    @Test
    void testSetTurnoProperty() {
        bucleDeControl.setTurnoProperty(5);
        assertEquals(5, bucleDeControl.getTurnoProperty().get());
    }

    @Test
    void testGetTablero() {
        assertEquals(tablero, bucleDeControl.getTablero());
    }

    @Test
    void testSetTablero() {
        Tablero newTablero = new Tablero(10, 10, datosJuego);
        bucleDeControl.setTablero(newTablero);
        assertEquals(newTablero, bucleDeControl.getTablero());
    }

    @Test
    void testGetEstudiantes() {
        assertNotNull(bucleDeControl.getEstudiantes());
    }

    @Test
    void testSetEstudiantes() {
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        bucleDeControl.setEstudiantes(estudiantes);
        assertEquals(estudiantes, bucleDeControl.getEstudiantes());
    }

    @Test
    void testGetRecursos() {
        assertNotNull(bucleDeControl.getRecursos());
    }

    @Test
    void testSetRecursos() {
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
        bucleDeControl.setRecursos(recursos);
        assertEquals(recursos, bucleDeControl.getRecursos());
    }

    @Test
    void testIsTurno() {
        assertFalse(bucleDeControl.isTurno());
    }

    @Test
    void testSetTurno() {
        bucleDeControl.setTurno(true);
        assertTrue(bucleDeControl.isTurno());
    }

    @Test
    void testActualizarTurnoProperty() {
        datosJuego.setTurnoActual(5);
        bucleDeControl.actualizarTurnoProperty();
        assertEquals(5, bucleDeControl.getTurnoProperty().get());
    }

    @Test
    void testEvaluarMejoras() {
        // Test with no students and no resources
        bucleDeControl.evaluarMejoras();
        // Shouldn't throw any exceptions and nothing to assert

        // Add student and resource and test
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante);
        Recursos recurso = new Agua(1, datosJuego);
        tablero.getCelda(0, 0).getListaRecursos().add(recurso);

        bucleDeControl.evaluarMejoras();
        // Should process without throwing exceptions
    }

    @Test
    void testActualizarTiempoDeVidaEstudiante() {
        // Test with no students
        bucleDeControl.actualizarTiempoDeVidaEstudiante();
        // Shouldn't throw any exceptions and nothing to assert

        // Add student and test
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante);

        bucleDeControl.actualizarTiempoDeVidaEstudiante();
        // Should process without throwing exceptions
    }

    @Test
    void testActualizarTiempoDeAparicionDeRecursos() {
        // Test with no resources
        bucleDeControl.actualizarTiempoDeAparicionDeRecursos();
        // Shouldn't throw any exceptions and nothing to assert

        // Add resource and test
        Recursos recurso = new Agua(1, datosJuego);
        datosJuego.getRecursos().add(recurso);

        bucleDeControl.actualizarTiempoDeAparicionDeRecursos();
        // Should process without throwing exceptions
    }

    @Test
    void testMoverEstudiantes() throws RecursosNoUtilizados {
        // Test with no students
        bucleDeControl.moverEstudiantes();
        // Shouldn't throw any exceptions and nothing to assert

        // Add student and test
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante);

        bucleDeControl.moverEstudiantes();
        // Should process without throwing exceptions
    }

    @Test
    void testEvaluarReproduccion() {
        // Test with no students
        bucleDeControl.evaluarReproduccion();
        // Shouldn't throw any exceptions and nothing to assert

        // Add students and test
        Estudiante estudiante1 = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        Estudiante estudiante2 = new EstudianteBasico(2, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante1);
        datosJuego.getEstudiantes().add(estudiante2);

        bucleDeControl.evaluarReproduccion();
        // Should process without throwing exceptions
    }

    @Test
    void testEvaluarClonacion() {
        // Test with no students
        bucleDeControl.evaluarClonacion();
        // Shouldn't throw any exceptions and nothing to assert

        // Add student and test
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante);

        bucleDeControl.evaluarClonacion();
        // Should process without throwing exceptions
    }

    @Test
    void testEvaluarDesaparicionEstudiantes() {
        // Test with no students
        bucleDeControl.evaluarDesaparicionEstudiantes();
        // Shouldn't throw any exceptions and nothing to assert

        // Add student and test
        Estudiante estudiante = new EstudianteBasico(1, 0, 0, 1, 10, 50, 50, 1);
        datosJuego.getEstudiantes().add(estudiante);

        bucleDeControl.evaluarDesaparicionEstudiantes();
        // Should process without throwing exceptions
    }

    @Test
    void testEvaluarAparicionRecursos() {
        bucleDeControl.evaluarAparicionRecursos();
        // Should process without throwing exceptions
    }

    @Test
    void testEjecuteBucle() {
        assertDoesNotThrow(() -> bucleDeControl.ejecuteBucle());
    }

    @Test
    void testRun() {
        assertDoesNotThrow(() -> {
            Thread thread = new Thread(bucleDeControl);
            thread.start();
            thread.join();
        });
    }
}