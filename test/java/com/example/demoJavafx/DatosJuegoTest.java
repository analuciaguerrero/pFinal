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
    private ListaEnlazada<Estudiante> listaEstudiantes;
    private ListaEnlazada<Recursos> listaRecursos;

    @BeforeEach
    public void setUp() {
        datosJuego = new DatosJuego(
                10, 0.5, 0.3, 0.2, 0.1,
                0.4, 5, 0.1, 0.2, 0.3,
                0.4, 0.5, 0.6, 2, 3,
                1, 0.3, 0.4, 5, 5, 0);
        listaEstudiantes = new ListaEnlazada<>();
        listaRecursos = new ListaEnlazada<>();
        listaEstudiantes.add(new Estudiante(1, 2, 3, 4, 5, 6) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        listaEstudiantes.add(new Estudiante(3, 4, 5, 6, 7, 8) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        listaRecursos.add(new Recursos(1, 2, 3, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        });
        listaRecursos.add(new Recursos(2, 3, 4, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        });
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(10, datosJuego.getTurnosVidaIniciales());
        assertEquals(0.5, datosJuego.getProbReproduccionEstudiante());
        assertEquals(0.3, datosJuego.getProbClonacionEstudiante());
        assertEquals(0.2, datosJuego.getProbMejorarANormal());
        assertEquals(0.1, datosJuego.getProbMejorarAAvanzado());
        assertEquals(0.4, datosJuego.getProbRecurso());
        assertEquals(5, datosJuego.getTurnosIniciales());
        assertEquals(0.1, datosJuego.getProbAgua());
        assertEquals(0.2, datosJuego.getProbComida());
        assertEquals(0.3, datosJuego.getProbMontaña());
        assertEquals(0.4, datosJuego.getProbTesoro());
        assertEquals(0.5, datosJuego.getProbBiblioteca());
        assertEquals(0.6, datosJuego.getProbPozo());
        assertEquals(2, datosJuego.getAumentoVidaAgua());
        assertEquals(3, datosJuego.getAumentoVidaComida());
        assertEquals(1, datosJuego.getReduccionVidaMontaña());
        assertEquals(0.3, datosJuego.getAumentoProbReproduccion());
        assertEquals(0.4, datosJuego.getAumentoProbClonacion());
        assertEquals(5, datosJuego.getFilasDelTablero());
        assertEquals(5, datosJuego.getColumnasDelTablero());
        assertEquals(0, datosJuego.getTurnoActual());
    }

    @Test
    public void testSetters() {
        datosJuego.setTurnosVidaIniciales(20);
        datosJuego.setProbReproduccionEstudiante(0.6);
        datosJuego.setProbClonacionEstudiante(0.7);
        datosJuego.setProbMejorarANormal(0.8);
        datosJuego.setProbMejorarAAvanzado(0.9);
        datosJuego.setProbRecurso(0.5);
        datosJuego.setTurnosIniciales(10);
        datosJuego.setProbAgua(0.2);
        datosJuego.setProbComida(0.3);
        datosJuego.setProbMontaña(0.4);
        datosJuego.setProbTesoro(0.5);
        datosJuego.setProbBiblioteca(0.6);
        datosJuego.setProbPozo(0.7);
        datosJuego.setAumentoVidaAgua(4);
        datosJuego.setAumentoVidaComida(5);
        datosJuego.setReduccionVidaMontaña(2);
        datosJuego.setAumentoProbReproduccion(0.6);
        datosJuego.setAumentoProbClonacion(0.7);
        datosJuego.setFilasDelTablero(10);
        datosJuego.setColumnasDelTablero(10);
        datosJuego.setTurnoActual(1);

        assertEquals(20, datosJuego.getTurnosVidaIniciales());
        assertEquals(0.6, datosJuego.getProbReproduccionEstudiante());
        assertEquals(0.7, datosJuego.getProbClonacionEstudiante());
        assertEquals(0.8, datosJuego.getProbMejorarANormal());
        assertEquals(0.9, datosJuego.getProbMejorarAAvanzado());
        assertEquals(0.5, datosJuego.getProbRecurso());
        assertEquals(10, datosJuego.getTurnosIniciales());
        assertEquals(0.2, datosJuego.getProbAgua());
        assertEquals(0.3, datosJuego.getProbComida());
        assertEquals(0.4, datosJuego.getProbMontaña());
        assertEquals(0.5, datosJuego.getProbTesoro());
        assertEquals(0.6, datosJuego.getProbBiblioteca());
        assertEquals(0.7, datosJuego.getProbPozo());
        assertEquals(4, datosJuego.getAumentoVidaAgua());
        assertEquals(5, datosJuego.getAumentoVidaComida());
        assertEquals(2, datosJuego.getReduccionVidaMontaña());
        assertEquals(0.6, datosJuego.getAumentoProbReproduccion());
        assertEquals(0.7, datosJuego.getAumentoProbClonacion());
        assertEquals(10, datosJuego.getFilasDelTablero());
        assertEquals(10, datosJuego.getColumnasDelTablero());
        assertEquals(1, datosJuego.getTurnoActual());
    }
    @Test
    public void testSetEstudiantes() {
        datosJuego.setEstudiantes(listaEstudiantes);
        assertEquals(listaEstudiantes, datosJuego.getEstudiantes());

        ListaEnlazada<Estudiante> nuevaListaEstudiantes = new ListaEnlazada<>();
        nuevaListaEstudiantes.add(new Estudiante(8,9,0,7,6,5) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });

        datosJuego.setEstudiantes(nuevaListaEstudiantes);
        assertEquals(nuevaListaEstudiantes, datosJuego.getEstudiantes());
    }
    @Test
    public void testSetRecursos() {
        datosJuego.setRecursos(listaRecursos);
        assertEquals(listaRecursos, datosJuego.getRecursos());

        ListaEnlazada<Recursos> nuevaListaRecursos = new ListaEnlazada<>();
        nuevaListaRecursos.add(new Recursos(3, 4, 5, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        });

        datosJuego.setRecursos(nuevaListaRecursos);
        assertEquals(nuevaListaRecursos, datosJuego.getRecursos());
    }
    @Test
    public void testGetNumEstudiantes() {
        assertEquals(0, datosJuego.getNumEstudiantes());  // Verificar valor inicial
    }

    @Test
    public void testSetNumEstudiantes() {
        datosJuego.setNumEstudiantes(5);
        assertEquals(5, datosJuego.getNumEstudiantes());

        datosJuego.setNumEstudiantes(10);
        assertEquals(10, datosJuego.getNumEstudiantes());
    }

    @Test
    public void testGetNumRecursos() {
        assertEquals(0, datosJuego.getNumRecursos());  // Verificar valor inicial
    }

    @Test
    public void testSetNumRecursos() {
        datosJuego.setNumRecursos(8);
        assertEquals(8, datosJuego.getNumRecursos());

        datosJuego.setNumRecursos(15);
        assertEquals(15, datosJuego.getNumRecursos());
    }

    @Test
    public void testSetSaveFlag() {
        datosJuego.setNumEstudiantes(5);
        assertFalse(datosJuego.isSave());

        datosJuego.setNumRecursos(8);
        assertFalse(datosJuego.isSave());

        datosJuego.setMaximoEstudiantesPorCelda(4);
        assertFalse(datosJuego.isSave());

        datosJuego.setMaximoRecursosPorCelda(5);
        assertFalse(datosJuego.isSave());

        datosJuego.setPausado(true);
        assertFalse(datosJuego.isSave());

        datosJuego.setEstudiantes(listaEstudiantes);
        assertFalse(datosJuego.isSave());

        datosJuego.setRecursos(listaRecursos);
        assertFalse(datosJuego.isSave());
    }

    @Test
    public void testGenerarEnteroAleatorio() {
        int min = 1, max = 10;
        for (int i = 0; i < 100; i++) {
            int rand = datosJuego.generarEnteroAleatorio(min, max);
            assertTrue(rand >= min && rand <= max);
        }
    }

    @Test
    public void testCeldaAleatoria() {
        int filas = 5, columnas = 5;
        Celda celda = datosJuego.celdaAleatoria(filas, columnas);
        assertTrue(celda.getPosicion()[0] >= 0 && celda.getPosicion()[0] < filas);
        assertTrue(celda.getPosicion()[1] >= 0 && celda.getPosicion()[1] < columnas);
    }

    @Test
    public void testObtenerEstudianteAleatorio() {
        Estudiante e1 = new Estudiante(2,3,4,5,9,9) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        Estudiante e2 = new Estudiante(7,6,4,5,3,2) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        datosJuego.getEstudiantes().add(e1);
        datosJuego.getEstudiantes().add(e2);

        Estudiante estudianteAleatorio = datosJuego.obtenerEstudianteAleatorio();
        assertNotNull(estudianteAleatorio);
        assertTrue(estudianteAleatorio.equals(e1) || estudianteAleatorio.equals(e2));
    }
    @Test
    public void testGetMaximoEstudiantesPorCelda() {
        assertEquals(3, datosJuego.getMaximoEstudiantesPorCelda());  // Verificar valor inicial
    }

    @Test
    public void testSetMaximoEstudiantesPorCelda() {
        datosJuego.setMaximoEstudiantesPorCelda(4);
        assertEquals(4, datosJuego.getMaximoEstudiantesPorCelda());

        datosJuego.setMaximoEstudiantesPorCelda(2);
        assertEquals(2, datosJuego.getMaximoEstudiantesPorCelda());
    }

    @Test
    public void testGetMaximoRecursosPorCelda() {
        assertEquals(3, datosJuego.getMaximoRecursosPorCelda());  // Verificar valor inicial
    }

    @Test
    public void testSetMaximoRecursosPorCelda() {
        datosJuego.setMaximoRecursosPorCelda(5);
        assertEquals(5, datosJuego.getMaximoRecursosPorCelda());

        datosJuego.setMaximoRecursosPorCelda(1);
        assertEquals(1, datosJuego.getMaximoRecursosPorCelda());
    }
    @Test
    public void testIsPausado() {
        assertFalse(datosJuego.isPausado());  // Verificar valor inicial (asumiendo que es false)
    }

    @Test
    public void testSetPausado() {
        datosJuego.setPausado(true);
        assertTrue(datosJuego.isPausado());

        datosJuego.setPausado(false);
        assertFalse(datosJuego.isPausado());
    }

    @Test
    public void testObtenerRecursoAleatorio() {
        Recursos r1 = new Recursos(1, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        };
        Recursos r2 = new Recursos(2, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        };
        datosJuego.getRecursos().add(r1);
        datosJuego.getRecursos().add(r2);

        Recursos recursoAleatorio = datosJuego.obtenerRecursoAleatorio();
        assertNotNull(recursoAleatorio);
        assertTrue(recursoAleatorio.equals(r1) || recursoAleatorio.equals(r2));
    }
    @Test
    public void testGetZombieStudentsLife() {
        assertNull(datosJuego.getZombieStudentsLife());  // Verificar valor inicial
    }

    @Test
    public void testSetZombieStudentsLife() {
        Tablero tablero = new Tablero(datosJuego.getFilasDelTablero(), datosJuego.getColumnasDelTablero(), datosJuego);
        ZombieStudentsLife zsl = new ZombieStudentsLife(datosJuego, tablero);
        datosJuego.setZombieStudentsLife(zsl);
        assertEquals(zsl, datosJuego.getZombieStudentsLife());
    }
    @Test
    public void testIsSave() {
        assertTrue(datosJuego.isSave());  // Verificar valor inicial
    }

    @Test
    public void testGetRutaArchivo() {
        assertNull(datosJuego.getRutaArchivo());  // Verificar valor inicial
    }

    @Test
    public void testSetRutaArchivo() {
        String ruta = "ruta/test";
        datosJuego.setRutaArchivo(ruta);
        assertEquals(ruta, datosJuego.getRutaArchivo());
    }

    @Test
    public void testGetHistorialEstudiantes() {
        assertNotNull(datosJuego.getHistorialEstudiantes());  // Verificar valor inicial
        assertTrue(datosJuego.getHistorialEstudiantes().isVacia());
    }

    @Test
    public void testSetHistorialEstudiantes() {
        ListaEnlazada<Estudiante> historialEstudiantes = new ListaEnlazada<>();
        historialEstudiantes.add(new Estudiante(1, 2, 3, 4, 5, 6) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });

        datosJuego.setHistorialEstudiantes(historialEstudiantes);
        assertEquals(historialEstudiantes, datosJuego.getHistorialEstudiantes());
        assertFalse(datosJuego.isSave());
    }

    @Test
    public void testGetHistorialRecursos() {
        assertNotNull(datosJuego.getHistorialRecursos());  // Verificar valor inicial
        assertTrue(datosJuego.getHistorialRecursos().isVacia());
    }

    @Test
    public void testSetHistorialRecursos() {
        ListaEnlazada<Recursos> historialRecursos = new ListaEnlazada<>();
        historialRecursos.add(new Recursos(1, datosJuego) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda, int turno) {

            }
        });

        datosJuego.setHistorialRecursos(historialRecursos);
        assertEquals(historialRecursos, datosJuego.getHistorialRecursos());
        assertFalse(datosJuego.isSave());
    }

    @Test
    public void testGuardarYcargarArchivo() {
        String rutaArchivo = "testDatosJuego";
        datosJuego.guardarArchivo(rutaArchivo);
        DatosJuego datosCargados = DatosJuego.cargarArchivo(rutaArchivo);

        assertNotNull(datosCargados);
        assertEquals(datosJuego.getTurnosVidaIniciales(), datosCargados.getTurnosVidaIniciales());
        assertEquals(datosJuego.getProbReproduccionEstudiante(), datosCargados.getProbReproduccionEstudiante());
        // Repite para todos los atributos necesarios
    }
}