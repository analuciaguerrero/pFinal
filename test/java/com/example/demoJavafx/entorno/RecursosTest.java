package com.example.demoJavafx.entorno;


import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.IntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class RecursosTest {
    private DatosJuego datosJuego;
    private Agua recurso;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego();
        datosJuego.setTurnosIniciales(5);
        recurso = new Agua(1, 2, 3, datosJuego);
        celda = new Celda(2, 3, datosJuego, new Tablero());
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, recurso.getId());
        assertEquals(2, recurso.getPosicionN());
        assertEquals(3, recurso.getPosicionM());
        assertEquals(5, recurso.getTurnosRestantes());
    }

    @Test
    void testSetters() {
        recurso.setId(10);
        recurso.setPosicionN(20);
        recurso.setPosicionM(30);
        recurso.setTurnosRestantes(15);

        assertEquals(10, recurso.getId());
        assertEquals(20, recurso.getPosicionN());
        assertEquals(30, recurso.getPosicionM());
        assertEquals(15, recurso.getTurnosRestantes());
    }

    @Test
    void testGetPosicion() {
        int[] posicion = recurso.getPosicion();
        assertEquals(2, posicion[0]);
        assertEquals(3, posicion[1]);
    }

    @Test
    void testSetPosicionArray() {
        int[] nuevaPosicion = {5, 10};
        try {
            recurso.setPosicion(nuevaPosicion);
        } catch (TamañoArrayInvalido e) {
            fail("No debería lanzarse excepción");
        }
        assertEquals(5, recurso.getPosicionN());
        assertEquals(10, recurso.getPosicionM());
    }

    @Test
    void testTurnosRestantesProperty() {
        IntegerProperty turnosRestantesProperty = recurso.getTurnosRestantesProperty();
        assertEquals(5, turnosRestantesProperty.get());

        recurso.setTurnosRestantes(10);
        assertEquals(10, turnosRestantesProperty.get());
    }

    @Test
    void testActualizarTurnosRestantesProperty() {
        recurso.setTurnosRestantes(8);
        assertEquals(8, recurso.getTurnosRestantesProperty().get());
    }

    @Test
    void testAdd() {
        recurso.add(datosJuego, celda);

        assertEquals(1, datosJuego.getRecursos().getNumeroElementos());
        assertEquals(1, celda.getListaRecursos().getNumeroElementos());
    }

    @Test
    void testDel() {
        recurso.add(datosJuego, celda);
        recurso.del(datosJuego, celda);

        assertEquals(0, datosJuego.getRecursos().getNumeroElementos());
        assertEquals(0, celda.getListaRecursos().getNumeroElementos());
    }

    @Test
    void testActualizarTurnos() {
        recurso.add(datosJuego, celda);

        assertFalse(recurso.actualizarTurnos(datosJuego, celda));
        assertEquals(4, recurso.getTurnosRestantes());

        recurso.setTurnosRestantes(1);
        assertTrue(recurso.actualizarTurnos(datosJuego, celda));
        assertEquals(0, datosJuego.getRecursos().getNumeroElementos());
        assertEquals(0, celda.getListaRecursos().getNumeroElementos());
    }

    @Test
    void testAplicarEfecto() {
        Estudiante estudiante = new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        };
        recurso.aplicarEfecto(estudiante, celda, 1);

        // Verifica que el efecto se ha aplicado al estudiante (implementación específica depende de la subclase)
    }
}