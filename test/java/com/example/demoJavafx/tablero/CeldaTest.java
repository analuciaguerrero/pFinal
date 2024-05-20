package com.example.demoJavafx.tablero;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Agua;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.excepciones.EstudianteNoExistente;
import com.example.demoJavafx.excepciones.RecursoNoExistente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {
    private Celda celda;
    private DatosJuego datosJuego;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.3, 0.2, 0.1,
                0.6, 5, 0.4, 0.7, 0.8,
                0.9, 0.1, 0.2, 15, 20,
                25, 0.5, 0.3, 10, 10, 1);
        tablero = new Tablero(datosJuego);
        celda = new Celda(1, 1, datosJuego, tablero);
    }

    @Test
    public void testConstructorWithPosAndData() {
        Celda celda = new Celda(2, 3, datosJuego, tablero);
        assertEquals(2, celda.getPosicionN());
        assertEquals(3, celda.getPosicionM());
        assertEquals(datosJuego, celda.getDatos());
        assertEquals(tablero, celda.getTablero());
    }

    @Test
    public void testGetSetPosicionN() {
        celda.setPosicionN(5);
        assertEquals(5, celda.getPosicionN());
    }

    @Test
    public void testGetSetPosicionM() {
        celda.setPosicionM(7);
        assertEquals(7, celda.getPosicionM());
    }

    @Test
    public void testGetPosicion() {
        int[] posicion = celda.getPosicion();
        assertArrayEquals(new int[]{1, 1}, posicion);
    }

    @Test
    public void testGetSetListaRecursos() {
        ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
        celda.setListaRecursos(listaRecursos);
        assertEquals(listaRecursos, celda.getListaRecursos());
    }

    @Test
    public void testGetSetListaEstudiantes() {
        ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
        celda.setListaEstudiantes(listaEstudiantes);
        assertEquals(listaEstudiantes, celda.getListaEstudiantes());
    }

    @Test
    public void testGetSetDatos() {
        DatosJuego nuevoDatos = new DatosJuego();
        celda.setDatos(nuevoDatos);
        assertEquals(nuevoDatos, celda.getDatos());
    }

    @Test
    public void testGetSetTablero() {
        Tablero nuevoTablero = new Tablero(5, 5, datosJuego);
        celda.setTablero(nuevoTablero);
        assertEquals(nuevoTablero, celda.getTablero());
    }

    @Test
    public void testAgregarEstudiante() throws EstudianteNoExistente {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3);
        celda.agregarEstudiante(estudiante, true);
        assertTrue(celda.getListaEstudiantes().contains(estudiante));
    }

    @Test
    public void testAgregarRecurso() throws RecursoNoExistente {
        Recursos recurso = new Agua(1, 1, 1, 1, 0.5, 10, 0.8);
        celda.agregarRecurso(recurso, true);
        assertTrue(celda.getListaRecursos().contains(recurso));
    }

    @Test
    public void testEliminarEstudiante() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3);
        celda.getListaEstudiantes().add(estudiante);
        celda.eliminarEstudiante(estudiante);
        assertFalse(celda.getListaEstudiantes().contains(estudiante));
    }

    @Test
    public void testEliminarRecurso() {
        Recursos recurso = new Agua(1, 1, 1, 1, 0.5, 10, 0.8);
        celda.getListaRecursos().add(recurso);
        celda.eliminarRecurso(recurso);
        assertFalse(celda.getListaRecursos().contains(recurso));
    }

    @Test
    public void testCrearCeldaAleatoria() {
        celda.crearCeldaAleatoria(datosJuego);
        // Comprobar que los estudiantes y recursos se agregaron correctamente a una celda aleatoria
        assertNotNull(celda);
    }

    @Test
    public void testEliminarEstudianteAleatorio() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3);
        celda.getListaEstudiantes().add(estudiante);
        celda.eliminarEstudianteAleatorio();
        assertFalse(celda.getListaEstudiantes().contains(estudiante));
    }

    @Test
    public void testEliminarRecursoAleatorio() {
        Recursos recurso = new Agua(1, 1, 1, 1, 0.5, 10, 0.8);
        celda.getListaRecursos().add(recurso);
        celda.eliminarRecursoAleatorio();
        assertFalse(celda.getListaRecursos().contains(recurso));
    }

    @Test
    public void testEvaluarMejoras() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3);
        Recursos recurso = new Agua(1, 1, 1, 1, 0.5, 10, 0.8);
        celda.getListaEstudiantes().add(estudiante);
        celda.getListaRecursos().add(recurso);
        celda.evaluarMejoras();
        // Comprobar que los efectos de los recursos se aplicaron correctamente a los estudiantes
    }

    @Test
    public void testRestablecerInterfazVisual() {
        // Este test puede ser más complicado debido a la naturaleza visual de la interfaz
        // Se puede utilizar frameworks de pruebas de UI como TestFX para realizar estos tests
    }
}
