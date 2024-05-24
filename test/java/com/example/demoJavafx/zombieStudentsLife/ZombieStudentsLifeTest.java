package com.example.demoJavafx.zombieStudentsLife;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZombieStudentsLifeTest {
    private DatosJuego datos;
    private Tablero tablero;
    private ZombieStudentsLife zombieStudentsLife;

    @BeforeEach
    public void setUp() {
        // Configuración de datos iniciales para las pruebas
        datos = new DatosJuego();
        datos.setFilasDelTablero(5);
        datos.setColumnasDelTablero(5);
        datos.setTurnosVidaIniciales(10);
        datos.setProbReproduccionEstudiante(0.5);
        datos.setProbClonacionEstudiante(0.1);
        datos.setTurnoActual(1);

        tablero = new Tablero(datos.getFilasDelTablero(), datos.getColumnasDelTablero(), datos);

        zombieStudentsLife = new ZombieStudentsLife(datos, tablero);
    }

    @Test
    public void testZombieStudentsLifeInitialization() {
        assertNotNull(zombieStudentsLife.getDato());
        assertNotNull(zombieStudentsLife.getTablero());
        assertNotNull(zombieStudentsLife.getBucle());
    }

    @Test
    public void testStartWithTurnoTrue() {
        zombieStudentsLife.start(true);
        assertTrue(zombieStudentsLife.getBucle().isTurno());
    }

    @Test
    public void testStartWithTurnoFalse() {
        zombieStudentsLife.start(false);
        assertFalse(zombieStudentsLife.getBucle().isTurno());
    }

    @Test
    public void testCrearArbolGenealogico() {
        datos.getEstudiantes().add(new Estudiante(1, 1, 10, 0.5, 0.1, 1) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        zombieStudentsLife.informacion();
        assertFalse(zombieStudentsLife.getArbolGenealogico().isVacio());
    }

    @Test
    public void testCrearGrafoDeOperaciones() {
        datos.getEstudiantes().add(new Estudiante(1, 1, 10, 0.5, 0.1, 1) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        Estudiante estudiante = datos.getEstudiantes().getElemento(0).getData();
        estudiante.getColaDeOperaciones().add("Nacer turno:1");
        zombieStudentsLife.informacion();
        assertNotNull(zombieStudentsLife.getGrafoDeOperaciones().getNodoGrafo("Nacer"));
    }

    @Test
    public void testFinalizarPartida() {
        datos.getEstudiantes().add(new Estudiante(1, 1, 10, 0.5, 0.1, 1) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        zombieStudentsLife.finalizarPartida();
        assertFalse(zombieStudentsLife.getArbolGenealogico().isVacio());
        assertNotNull(zombieStudentsLife.getGrafoDeOperaciones().getNodoGrafo("Nacer"));
    }
}