package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Agua;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {
    private Celda celda;
    private DatosJuego datosJuego;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        datosJuego = new DatosJuego();
        tablero = new Tablero();
        celda = new Celda(1, 1, datosJuego, tablero);
    }

    @Test
    public void testGetSetPosicionN() {
        celda.setPosicionN(2);
        assertEquals(2, celda.getPosicionN());
    }

    @Test
    public void testGetSetPosicionM() {
        celda.setPosicionM(3);
        assertEquals(3, celda.getPosicionM());
    }

    @Test
    public void testGetPosicion() {
        int[] posicion = celda.getPosicion();
        assertArrayEquals(new int[]{1, 1}, posicion);
    }

    @Test
    public void testGetSetListaEstudiantes() {
        ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
        celda.setListaEstudiantes(listaEstudiantes);
        assertEquals(listaEstudiantes, celda.getListaEstudiantes());
    }

    @Test
    public void testGetSetListaRecursos() {
        ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
        celda.setListaRecursos(listaRecursos);
        assertEquals(listaRecursos, celda.getListaRecursos());
    }

    @Test
    public void testGetSetDatos() {
        DatosJuego datos = new DatosJuego();
        celda.setDatos(datos);
        assertEquals(datos, celda.getDatos());
    }

    @Test
    public void testGetSetTablero() {
        Tablero tablero = new Tablero();
        celda.setTablero(tablero);
        assertEquals(tablero, celda.getTablero());
    }

    @Test
    public void testGetBotonCelda() {
        assertNotNull(celda.getBotonCelda());
    }

    @Test
    public void testGetGridElms() {
        assertNotNull(celda.getGridElms());
    }

    @Test
    public void testAgregarEstudiante() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 1, 1.0, 1.0, 1);
        celda.agregarEstudiante(estudiante, true);
        assertTrue(celda.getListaEstudiantes().getNumeroElementos() > 0);
    }

    @Test
    public void testAgregarRecurso() {
        Agua recurso = new Agua(1, datosJuego);
        celda.agregarRecurso(recurso, true);
        assertTrue(celda.getListaRecursos().getNumeroElementos() > 0);
    }

    @Test
    public void testEliminarEstudiante() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 1, 1.0, 1.0, 1);
        celda.getListaEstudiantes().add(estudiante);
        celda.eliminarEstudiante(estudiante);
        assertEquals(0, celda.getListaEstudiantes().getNumeroElementos());
    }

    @Test
    public void testEliminarRecurso() {
        Agua recurso = new Agua(1, datosJuego);
        celda.getListaRecursos().add(recurso);
        celda.eliminarRecurso(recurso);
        assertEquals(0, celda.getListaRecursos().getNumeroElementos());
    }

    @Test
    public void testCrearCeldaAleatoria() {
        datosJuego.setFilasDelTablero(5);
        datosJuego.setColumnasDelTablero(5);
        celda.crearCeldaAleatoria(datosJuego);
        // Verificar que se añadan estudiantes y recursos a la celda aleatoria
        assertTrue(celda.getListaEstudiantes().getNumeroElementos() > 0 || celda.getListaRecursos().getNumeroElementos() > 0);
    }

    @Test
    public void testEliminarEstudianteAleatorio() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 1, 1.0, 1.0, 1);
        celda.getListaEstudiantes().add(estudiante);
        celda.eliminarEstudianteAleatorio();
        assertEquals(0, celda.getListaEstudiantes().getNumeroElementos());
    }

    @Test
    public void testEliminarRecursoAleatorio() {
        Agua recurso = new Agua(1, datosJuego);
        celda.getListaRecursos().add(recurso);
        celda.eliminarRecursoAleatorio();
        assertEquals(0, celda.getListaRecursos().getNumeroElementos());
    }

    @Test
    public void testRestablecerInterfazVisual() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 1, 1.0, 1.0, 1);
        celda.getListaEstudiantes().add(estudiante);
        Agua recurso = new Agua(1, datosJuego);
        celda.getListaRecursos().add(recurso);
        celda.restablecerInterfazVisual();
        assertEquals(2, celda.getGridElms().getChildren().size());
    }
}
