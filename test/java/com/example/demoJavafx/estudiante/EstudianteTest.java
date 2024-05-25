package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    private EstudianteBasico estudiante;
    private DatosJuego datosJuego;
    private Celda celda;
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego();
        celda = new Celda();
        tablero = new Tablero();
        estudiante = new EstudianteBasico(1, 0, 0, 1, 100, 50, 30, 1);
    }

    @Test
    void testConstructorConPosicion() {
        assertEquals(1, estudiante.getId());
        assertEquals(0, estudiante.getPosicionN());
        assertEquals(0, estudiante.getPosicionM());
        assertEquals(1, estudiante.getGeneracion());
        assertEquals(100, estudiante.getTiempoDeVida());
        assertEquals(50, estudiante.getProbReproduccion());
        assertEquals(30, estudiante.getProbClonacion());
    }

    @Test
    void testConstructorSinPosicion() {
        EstudianteBasico estudiante2 = new EstudianteBasico(2, 1, 100, 50, 30, 1);
        assertEquals(2, estudiante2.getId());
        assertEquals(1, estudiante2.getGeneracion());
        assertEquals(100, estudiante2.getTiempoDeVida());
        assertEquals(50, estudiante2.getProbReproduccion());
        assertEquals(30, estudiante2.getProbClonacion());
    }

    @Test
    void testConstructorCopia() {
        EstudianteBasico estudianteCopia = new EstudianteBasico(estudiante);
        assertEquals(estudiante.getId(), estudianteCopia.getId());
        assertEquals(estudiante.getPosicionN(), estudianteCopia.getPosicionN());
        assertEquals(estudiante.getPosicionM(), estudianteCopia.getPosicionM());
        assertEquals(estudiante.getGeneracion(), estudianteCopia.getGeneracion());
        assertEquals(estudiante.getTiempoDeVida(), estudianteCopia.getTiempoDeVida());
        assertEquals(estudiante.getProbReproduccion(), estudianteCopia.getProbReproduccion());
        assertEquals(estudiante.getProbClonacion(), estudianteCopia.getProbClonacion());
        assertEquals(estudiante.isVivo(), estudianteCopia.isVivo());
        assertEquals(estudiante.getColaDeOperaciones(), estudianteCopia.getColaDeOperaciones());
    }

    @Test
    void testSetPosicionArrayInvalido() {
        int[] nuevaPosicion = {5};
        assertThrows(TamañoArrayInvalido.class, () -> estudiante.setPosicion(nuevaPosicion));
    }

    @Test
    void testSetPosicion() {
        int[] nuevaPosicion = {5, 10};
        estudiante.setPosicion(nuevaPosicion);
        assertEquals(5, estudiante.getPosicionN());
        assertEquals(10, estudiante.getPosicionM());
    }

    @Test
    void testSetId() {
        estudiante.setId(2);
        assertEquals(2, estudiante.getId());
    }

    @Test
    void testSetGeneracion() {
        estudiante.setGeneracion(2);
        assertEquals(2, estudiante.getGeneracion());
    }

    @Test
    void testSetTiempoDeVida() {
        estudiante.setTiempoDeVida(90, 1);
        assertEquals(90, estudiante.getTiempoDeVida());
    }

    @Test
    void testSetProbReproduccion() {
        estudiante.setProbReproduccion(60, 1);
        assertEquals(60, estudiante.getProbReproduccion());
    }

    @Test
    void testSetProbClonacion() {
        estudiante.setProbClonacion(40, 1);
        assertEquals(40, estudiante.getProbClonacion());
    }

    @Test
    void testSetTiempoDeVidaProperty() {
        IntegerProperty nuevaPropiedad = new SimpleIntegerProperty();
        estudiante.setTiempoDeVidaProperty(nuevaPropiedad);
        assertEquals(nuevaPropiedad, estudiante.getTiempoDeVidaProperty());
    }

    @Test
    void testAddOperacion() {
        estudiante.addOperacion("test");
        assertEquals("test", estudiante.getColaDeOperaciones().peek());
    }

    @Test
    void testSetPadres() {
        EstudianteBasico padre1 = new EstudianteBasico(2, 1, 0, 1, 100, 50, 30, 1);
        EstudianteBasico padre2 = new EstudianteBasico(3, 1, 0, 1, 100, 50, 30, 1);
        estudiante.setPadres(padre1, padre2);
        assertEquals(padre1, estudiante.getPadres().getElemento(0).getData());
        assertEquals(padre2, estudiante.getPadres().getElemento(1).getData());
    }

    @Test
    void testActualizarTiempoDeVidaProperty() {
        estudiante.setTiempoDeVida(90, 1);
        assertEquals(90, estudiante.getTiempoDeVidaProperty().get());
    }

    @Test
    void testAdd() {
        estudiante.add(datosJuego, celda);
        assertTrue(celda.getListaEstudiantes().getNumeroElementos() > 0);
        assertTrue(datosJuego.getEstudiantes().getNumeroElementos() > 0);
    }

    @Test
    void testMorir() {
        estudiante.morir(datosJuego, celda);
        assertFalse(estudiante.isVivo());
    }

    @Test
    void testMoverseAleatorio() {
        estudiante.moverseAleatorio(tablero, 1);
        assertNotEquals(0, estudiante.getPosicionN());
        assertNotEquals(0, estudiante.getPosicionM());
    }

    @Test
    void testReproducirse() {
        EstudianteBasico pareja = new EstudianteBasico(2, 1, 0, 1, 100, 50, 30, 1);
        boolean result = estudiante.reproducirse(pareja, datosJuego, celda, 1);
        assertFalse(result);
    }

    @Test
    void testClonar() {
        estudiante.clonar(datosJuego, celda);
        assertTrue(datosJuego.getEstudiantes().getNumeroElementos() > 1);
    }

    @Test
    void testActualizarTiempoDeVida() {
        boolean result = estudiante.actualizarTiempoDeVida(celda, 1);
        assertFalse(result);
    }

    @Test
    void testGetPosicion() {
        int[] posicion = estudiante.getPosicion();
        assertArrayEquals(new int[]{0, 0}, posicion);
    }

    @Test
    void testGetProbMuerte() {
        assertEquals(50, estudiante.getProbMuerte());
    }

    @Test
    void testIsVivo() {
        assertTrue(estudiante.isVivo());
    }

    @Test
    void testGetNumTipo() {
        assertEquals(0, estudiante.getNumTipo());
    }

    @Test
    void testGetTipo() {
        assertEquals(EstudianteBasico.class, estudiante.getTipo());
    }
}