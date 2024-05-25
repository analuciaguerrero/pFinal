package com.example.demoJavafx;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import com.example.demoJavafx.zombieStudentsLife.ZombieStudentsLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatosJuegoTest {
    private DatosJuego datosJuego;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
    }

    @Test
    void testGetSetTurnosVidaIniciales() {
        datosJuego.setTurnosVidaIniciales(20);
        assertEquals(20, datosJuego.getTurnosVidaIniciales());
    }

    @Test
    void testGetSetTurnosRestantesAgua() {
        datosJuego.setTurnosRestantesAgua(15);
        assertEquals(15, datosJuego.getTurnosRestantesAgua());
    }

    @Test
    void testGetSetTurnosRestantesComida() {
        datosJuego.setTurnosRestantesComida(16);
        assertEquals(16, datosJuego.getTurnosRestantesComida());
    }

    @Test
    void testGetSetTurnosRestantesMontana() {
        datosJuego.setTurnosRestantesMontana(17);
        assertEquals(17, datosJuego.getTurnosRestantesMontana());
    }

    @Test
    void testGetSetTurnosRestantesBiblioteca() {
        datosJuego.setTurnosRestantesBiblioteca(18);
        assertEquals(18, datosJuego.getTurnosRestantesBiblioteca());
    }

    @Test
    void testGetSetTurnosRestantesTesoro() {
        datosJuego.setTurnosRestantesTesoro(19);
        assertEquals(19, datosJuego.getTurnosRestantesTesoro());
    }

    @Test
    void testGetSetTurnosRestantesPozo() {
        datosJuego.setTurnosRestantesPozo(20);
        assertEquals(20, datosJuego.getTurnosRestantesPozo());
    }

    @Test
    void testGetSetProbReproduccionEstudiante() {
        datosJuego.setProbReproduccionEstudiante(0.6);
        assertEquals(0.6, datosJuego.getProbReproduccionEstudiante());
    }

    @Test
    void testGetSetProbClonacionEstudiante() {
        datosJuego.setProbClonacionEstudiante(0.7);
        assertEquals(0.7, datosJuego.getProbClonacionEstudiante());
    }

    @Test
    void testGetSetProbRecurso() {
        datosJuego.setProbRecurso(0.8);
        assertEquals(0.8, datosJuego.getProbRecurso());
    }

    @Test
    void testGetSetNumEstudiantes() {
        datosJuego.setNumEstudiantes(25);
        assertEquals(25, datosJuego.getNumEstudiantes());
    }

    @Test
    void testGetSetNumRecursos() {
        datosJuego.setNumRecursos(30);
        assertEquals(30, datosJuego.getNumRecursos());
    }

    @Test
    void testGetSetProbAgua() {
        datosJuego.setProbAgua(0.9);
        assertEquals(0.9, datosJuego.getProbAgua());
    }

    @Test
    void testGetSetProbComida() {
        datosJuego.setProbComida(1.0);
        assertEquals(1.0, datosJuego.getProbComida());
    }

    @Test
    void testGetSetProbMontaña() {
        datosJuego.setProbMontaña(1.1);
        assertEquals(1.1, datosJuego.getProbMontaña());
    }

    @Test
    void testGetSetProbTesoro() {
        datosJuego.setProbTesoro(1.2);
        assertEquals(1.2, datosJuego.getProbTesoro());
    }

    @Test
    void testGetSetProbBiblioteca() {
        datosJuego.setProbBiblioteca(1.3);
        assertEquals(1.3, datosJuego.getProbBiblioteca());
    }

    @Test
    void testGetSetProbPozo() {
        datosJuego.setProbPozo(1.4);
        assertEquals(1.4, datosJuego.getProbPozo());
    }

    @Test
    void testGetSetAumentoVidaAgua() {
        datosJuego.setAumentoVidaAgua(2.1);
        assertEquals(2.1, datosJuego.getAumentoVidaAgua());
    }

    @Test
    void testGetSetAumentoVidaComida() {
        datosJuego.setAumentoVidaComida(3.1);
        assertEquals(3.1, datosJuego.getAumentoVidaComida());
    }

    @Test
    void testGetSetReduccionVidaMontaña() {
        datosJuego.setReduccionVidaMontaña(4.1);
        assertEquals(4.1, datosJuego.getReduccionVidaMontaña());
    }

    @Test
    void testGetSetAumentoProbReproduccion() {
        datosJuego.setAumentoProbReproduccion(0.5);
        assertEquals(0.5, datosJuego.getAumentoProbReproduccion());
    }

    @Test
    void testGetSetAumentoProbClonacion() {
        datosJuego.setAumentoProbClonacion(0.6);
        assertEquals(0.6, datosJuego.getAumentoProbClonacion());
    }

    @Test
    void testGetSetFilasDelTablero() {
        datosJuego.setFilasDelTablero(10);
        assertEquals(10, datosJuego.getFilasDelTablero());
    }

    @Test
    void testGetSetColumnasDelTablero() {
        datosJuego.setColumnasDelTablero(20);
        assertEquals(20, datosJuego.getColumnasDelTablero());
    }

    @Test
    void testGetSetMaximoEstudiantesPorCelda() {
        datosJuego.setMaximoEstudiantesPorCelda(5);
        assertEquals(5, datosJuego.getMaximoEstudiantesPorCelda());
    }

    @Test
    void testGetSetMaximoRecursosPorCelda() {
        datosJuego.setMaximoRecursosPorCelda(5);
        assertEquals(5, datosJuego.getMaximoRecursosPorCelda());
    }

    @Test
    void testGetSetProbMejorarANormal() {
        datosJuego.setProbMejorarANormal(0.7);
        assertEquals(0.7, datosJuego.getProbMejorarANormal());
    }

    @Test
    void testGetSetProbMejorarAAvanzado() {
        datosJuego.setProbMejorarAAvanzado(0.8);
        assertEquals(0.8, datosJuego.getProbMejorarAAvanzado());
    }

    @Test
    void testGetSetPausado() {
        datosJuego.setPausado(true);
        assertTrue(datosJuego.isPausado());
    }

    @Test
    void testGetSetZombieStudentsLife() {
        ZombieStudentsLife zsl = new ZombieStudentsLife();
        datosJuego.setZombieStudentsLife(zsl);
        assertEquals(zsl, datosJuego.getZombieStudentsLife());
    }

    @Test
    void testGetSetEstudiantes() {
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        datosJuego.setEstudiantes(estudiantes);
        assertEquals(estudiantes, datosJuego.getEstudiantes());
    }

    @Test
    void testGetSetRecursos() {
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
        datosJuego.setRecursos(recursos);
        assertEquals(recursos, datosJuego.getRecursos());
    }

    @Test
    void testGetSetTurnoActual() {
        datosJuego.setTurnoActual(10);
        assertEquals(10, datosJuego.getTurnoActual());
    }

    @Test
    void testGetSetTurnosIniciales() {
        datosJuego.setTurnosIniciales(5);
        assertEquals(5, datosJuego.getTurnosIniciales());
    }

    @Test
    void testGenerarEnteroAleatorio() {
        int min = 1;
        int max = 10;
        for (int i = 0; i < 100; i++) {
            int random = datosJuego.generarEnteroAleatorio(min, max);
            assertTrue(random >= min && random <= max);
        }
    }

    @Test
    void testCeldaAleatoria() {
        int filas = 5;
        int columnas = 5;
        Celda celda = datosJuego.celdaAleatoria(filas, columnas);
        assertNotNull(celda);
        assertTrue(celda.getPosicionN() >= 0 && celda.getPosicionN() < filas);
        assertTrue(celda.getPosicionM() >= 0 && celda.getPosicionM() < columnas);
    }

    @Test
    void testObtenerEstudianteAleatorio() {
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
        estudiantes.add(new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        datosJuego.setEstudiantes(estudiantes);
        Estudiante estudiante = datosJuego.obtenerEstudianteAleatorio();
        assertNotNull(estudiante);
    }

    @Test
    void testObtenerRecursoAleatorio() {
        ListaEnlazada<Recursos> recursos = new ListaEnlazada<>();
        recursos.add(new Recursos() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        });
        datosJuego.setRecursos(recursos);
        Recursos recurso = datosJuego.obtenerRecursoAleatorio();
        assertNotNull(recurso);
    }

    @Test
    void testGetSetSave() {
        datosJuego.setSave(true);
        assertTrue(datosJuego.isSave());
    }

    @Test
    void testGetSetRutaArchivo() {
        datosJuego.setRutaArchivo("ruta");
        assertEquals("ruta", datosJuego.getRutaArchivo());
    }

    @Test
    void testGetSetHistorialEstudiantes() {
        ListaEnlazada<Estudiante> historialEstudiantes = new ListaEnlazada<>();
        datosJuego.setHistorialEstudiantes(historialEstudiantes);
        assertEquals(historialEstudiantes, datosJuego.getHistorialEstudiantes());
    }

    @Test
    void testGetSetHistorialRecursos() {
        ListaEnlazada<Recursos> historialRecursos = new ListaEnlazada<>();
        datosJuego.setHistorialRecursos(historialRecursos);
        assertEquals(historialRecursos, datosJuego.getHistorialRecursos());
    }

    @Test
    void testGuardarYcargarArchivo() {
        datosJuego.setRutaArchivo("testFile");
        datosJuego.guardarArchivo("testFile");
        DatosJuego loadedDatosJuego = DatosJuego.cargarArchivo("testFile");
        assertNotNull(loadedDatosJuego);
    }
}