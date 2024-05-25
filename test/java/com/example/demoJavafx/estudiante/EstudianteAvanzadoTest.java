package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Comida;
import com.example.demoJavafx.entorno.Montaña;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Grafo;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteAvanzadoTest {
    private EstudianteAvanzado estudiante;
    private DatosJuego datosJuego;
    private Celda celda;
    private Tablero tablero;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego();
        celda = new Celda();
        tablero = new Tablero();
        estudiante = new EstudianteAvanzado(1, 0, 0, 1, 100, 50, 30, 1);
    }

    @Test
    void testConstructorCopia() {
        EstudianteAvanzado estudianteCopia = new EstudianteAvanzado(estudiante);
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
    void testConstructorConPosicion() {
        EstudianteAvanzado estudianteConPosicion = new EstudianteAvanzado(1, 0, 0, 1, 100, 50, 30, 1);
        assertEquals(1, estudianteConPosicion.getId());
        assertEquals(0, estudianteConPosicion.getPosicionN());
        assertEquals(0, estudianteConPosicion.getPosicionM());
        assertEquals(1, estudianteConPosicion.getGeneracion());
        assertEquals(100, estudianteConPosicion.getTiempoDeVida());
        assertEquals(50, estudianteConPosicion.getProbReproduccion());
        assertEquals(30, estudianteConPosicion.getProbClonacion());
    }

    @Test
    void testConstructorSinPosicion() {
        EstudianteAvanzado estudianteSinPosicion = new EstudianteAvanzado(2, 1, 100, 50, 30, 1);
        assertEquals(2, estudianteSinPosicion.getId());
        assertEquals(1, estudianteSinPosicion.getGeneracion());
        assertEquals(100, estudianteSinPosicion.getTiempoDeVida());
        assertEquals(50, estudianteSinPosicion.getProbReproduccion());
        assertEquals(30, estudianteSinPosicion.getProbClonacion());
    }

    @Test
    void testConstructorVacio() {
        EstudianteAvanzado estudianteVacio = new EstudianteAvanzado();
        assertNotNull(estudianteVacio);
    }

    @Test
    void testGetTipo() {
        assertEquals(EstudianteAvanzado.class, estudiante.getTipo());
    }

    @Test
    void testGetGrafoTab() {
        Grafo<Celda> grafoTab = estudiante.getGrafoTab(datosJuego, tablero);
        assertNotNull(grafoTab);
    }

    @Test
    void testAddPesoObs() {
        Celda vertice = new Celda();
        vertice.getListaRecursos().add(new Montaña(1, 1, 1, datosJuego));
        int peso = estudiante.calcularPesoArista(vertice, vertice, datosJuego.getReduccionVidaMontaña());
        assertTrue(peso > 1);
    }

    @Test
    void testCalcularPesoArista() {
        Celda vertice1 = new Celda();
        Celda vertice2 = new Celda();
        vertice1.getListaRecursos().add(new Montaña(1, 1, 1, datosJuego));
        int peso = estudiante.calcularPesoArista(vertice1, vertice2, datosJuego.getReduccionVidaMontaña());
        assertTrue(peso > 1);
    }

    @Test
    void testMoverSinRecursos() {
        assertThrows(RecursosNoUtilizados.class, () -> estudiante.mover(datosJuego, tablero));
    }

    @Test
    void testMoverConRecursos() {
        Recursos recurso = new Comida(1, 1, 1, datosJuego);
        datosJuego.getRecursos().add(recurso);
        estudiante.mover(datosJuego, tablero);
        assertNotEquals(0, estudiante.getPosicionN());
        assertNotEquals(0, estudiante.getPosicionM());
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
        Estudiante padre1 = new EstudianteAvanzado(2, 1, 0, 1, 100, 50, 30, 1);
        Estudiante padre2 = new EstudianteAvanzado(3, 1, 0, 1, 100, 50, 30, 1);
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
        Estudiante pareja = new EstudianteAvanzado(2, 1, 0, 1, 100, 50, 30, 1);
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
}