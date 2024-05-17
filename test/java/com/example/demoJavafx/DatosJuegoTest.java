package com.example.demoJavafx;

import com.example.demoJavafx.entorno.Agua;
import com.example.demoJavafx.entorno.Comida;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatosJuegoTest {
    private DatosJuego datosJuego;

    @BeforeEach
    public void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.3, 0.2, 0.1,
                0.6, 5, 0.4, 0.7, 0.8,
                0.9, 0.1, 0.2, 15, 20,
                25, 0.5, 0.3, 10, 10, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, datosJuego.getTurnosVidaIniciales());
        assertEquals(0.5, datosJuego.getProbReproduccionEstudiante());
        assertEquals(0.3, datosJuego.getProbClonacionEstudiante());
        assertEquals(0.2, datosJuego.getProbMejorarANormal());
        assertEquals(0.1, datosJuego.getProbMejorarAAvanzado());
        assertEquals(0.6, datosJuego.getProbRecurso());
        assertEquals(5, datosJuego.getTurnosIniciales());
        assertEquals(0.4, datosJuego.getProbAgua());
        assertEquals(0.7, datosJuego.getProbComida());
        assertEquals(0.8, datosJuego.getProbMontaña());
        assertEquals(0.9, datosJuego.getProbTesoro());
        assertEquals(0.1, datosJuego.getProbBiblioteca());
        assertEquals(0.2, datosJuego.getProbPozo());
        assertEquals(15, datosJuego.getAumentoVidaAgua());
        assertEquals(20, datosJuego.getAumentoVidaComida());
        assertEquals(25, datosJuego.getReduccionVidaMontaña());
        assertEquals(0.5, datosJuego.getAumentoProbReproduccion());
        assertEquals(0.3, datosJuego.getAumentoProbClonacion());
        assertEquals(10, datosJuego.getFilasDelTablero());
        assertEquals(10, datosJuego.getColumnasDelTablero());
        assertEquals(1, datosJuego.getTurnoActual());
    }

    @Test
    public void testGetSetTurnosVidaIniciales() {
        datosJuego.setTurnosVidaIniciales(20);
        assertEquals(20, datosJuego.getTurnosVidaIniciales());
    }

    @Test
    public void testGetSetProbReproduccionEstudiante() {
        datosJuego.setProbReproduccionEstudiante(0.4);
        assertEquals(0.4, datosJuego.getProbReproduccionEstudiante());
    }

    @Test
    public void testGetSetProbClonacionEstudiante() {
        datosJuego.setProbClonacionEstudiante(0.5);
        assertEquals(0.5, datosJuego.getProbClonacionEstudiante());
    }

    @Test
    public void testGetSetProbRecurso() {
        datosJuego.setProbRecurso(0.9);
        assertEquals(0.9, datosJuego.getProbRecurso());
    }

    @Test
    public void testGetSetNumEstudiantes() {
        datosJuego.setNumEstudiantes(50);
        assertEquals(50, datosJuego.getNumEstudiantes());
    }

    @Test
    public void testGetSetNumRecursos() {
        datosJuego.setNumRecursos(30);
        assertEquals(30, datosJuego.getNumRecursos());
    }

    @Test
    public void testGetSetProbAgua() {
        datosJuego.setProbAgua(0.8);
        assertEquals(0.8, datosJuego.getProbAgua());
    }

    @Test
    public void testGetSetProbComida() {
        datosJuego.setProbComida(0.6);
        assertEquals(0.6, datosJuego.getProbComida());
    }

    @Test
    public void testGetSetProbMontaña() {
        datosJuego.setProbMontaña(0.7);
        assertEquals(0.7, datosJuego.getProbMontaña());
    }

    @Test
    public void testGetSetProbTesoro() {
        datosJuego.setProbTesoro(0.5);
        assertEquals(0.5, datosJuego.getProbTesoro());
    }

    @Test
    public void testGetSetProbBiblioteca() {
        datosJuego.setProbBiblioteca(0.4);
        assertEquals(0.4, datosJuego.getProbBiblioteca());
    }

    @Test
    public void testGetSetProbPozo() {
        datosJuego.setProbPozo(0.2);
        assertEquals(0.2, datosJuego.getProbPozo());
    }

    @Test
    public void testGetSetAumentoVidaAgua() {
        datosJuego.setAumentoVidaAgua(10);
        assertEquals(10, datosJuego.getAumentoVidaAgua());
    }

    @Test
    public void testGetSetAumentoVidaComida() {
        datosJuego.setAumentoVidaComida(15);
        assertEquals(15, datosJuego.getAumentoVidaComida());
    }

    @Test
    public void testGetSetReduccionVidaMontaña() {
        datosJuego.setReduccionVidaMontaña(5);
        assertEquals(5, datosJuego.getReduccionVidaMontaña());
    }

    @Test
    public void testGetSetAumentoProbReproduccion() {
        datosJuego.setAumentoProbReproduccion(0.9);
        assertEquals(0.9, datosJuego.getAumentoProbReproduccion());
    }

    @Test
    public void testGetSetAumentoProbClonacion() {
        datosJuego.setAumentoProbClonacion(0.8);
        assertEquals(0.8, datosJuego.getAumentoProbClonacion());
    }

    @Test
    public void testGetSetFilasDelTablero() {
        datosJuego.setFilasDelTablero(20);
        assertEquals(20, datosJuego.getFilasDelTablero());
    }

    @Test
    public void testGetSetColumnasDelTablero() {
        datosJuego.setColumnasDelTablero(30);
        assertEquals(30, datosJuego.getColumnasDelTablero());
    }

    @Test
    public void testGetSetMaximoEstudiantesPorCelda() {
        datosJuego.setMaximoEstudiantesPorCelda(5);
        assertEquals(5, datosJuego.getMaximoEstudiantesPorCelda());
    }

    @Test
    public void testGetSetMaximoRecursosPorCelda() {
        datosJuego.setMaximoRecursosPorCelda(4);
        assertEquals(4, datosJuego.getMaximoRecursosPorCelda());
    }

    @Test
    public void testGetSetProbMejorarANormal() {
        datosJuego.setProbMejorarANormal(0.5);
        assertEquals(0.5, datosJuego.getProbMejorarANormal());
    }

    @Test
    public void testGetSetProbMejorarAAvanzado() {
        datosJuego.setProbMejorarAAvanzado(0.6);
        assertEquals(0.6, datosJuego.getProbMejorarAAvanzado());
    }

    @Test
    public void testGetSetPausado() {
        datosJuego.setPausado(true);
        assertTrue(datosJuego.isPausado());
    }

    @Test
    public void testGetSetTurnoActual() {
        datosJuego.setTurnoActual(5);
        assertEquals(5, datosJuego.getTurnoActual());
    }

    @Test
    public void testGenerarEnteroAleatorio() {
        int min = 1;
        int max = 10;
        int randomValue = datosJuego.generarEnteroAleatorio(min, max);
        assertTrue(randomValue >= min && randomValue <= max);
    }

    @Test
    public void testCeldaAleatoria() {
        int filas = 10;
        int columnas = 10;
        Celda celda = datosJuego.celdaAleatoria(filas, columnas);
        assertNotNull(celda);
        assertTrue(celda.getPosicionN() >= 0 && celda.getPosicionN()<  filas);
        assertTrue(celda.getPosicionM() >= 0 && celda.getPosicionM() < columnas);
    }

    @Test
    public void testObtenerEstudianteAleatorio() {
        EstudianteBasico estudiante1 = new EstudianteBasico(1, 1, 10, 0.5, 0.3);
        EstudianteBasico estudiante2 = new EstudianteBasico(2, 1, 10, 0.5, 0.3);
        datosJuego.getEstudiantes().add(estudiante1);
        datosJuego.getEstudiantes().add(estudiante2);

        Estudiante estudianteAleatorio = datosJuego.obtenerEstudianteAleatorio();
        assertNotNull(estudianteAleatorio);
    }

    @Test
    public void testObtenerRecursoAleatorio() {
        Recursos recurso1 = new Agua(1, 1, 1, 1, 0.5, 10, 0.8);
        Recursos recurso2 = new Comida(2, 1, 1, 1, 0.5, 10, 0.8);
        datosJuego.getRecursos().add(recurso1);
        datosJuego.getRecursos().add(recurso2);

        Recursos recursoAleatorio = datosJuego.obtenerRecursoAleatorio();
        assertNotNull(recursoAleatorio);
    }

    @Test
    public void testGuardarArchivo() {
        // Implementación del test para guardarArchivo
        // Puede ser necesario utilizar un archivo temporal o mock para esto
    }

    @Test
    public void testCargarArchivo() {
        // Implementación del test para cargarArchivo
        // Puede ser necesario utilizar un archivo temporal o mock para esto
    }
}