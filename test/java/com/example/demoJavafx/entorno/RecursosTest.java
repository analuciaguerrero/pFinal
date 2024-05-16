package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.TamañoArrayInvalido;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursosTest {
    @Test
    public void testConstructor1() {
        Recursos recurso = new RecursosMock(1, 0, 0, 3, 0.5);
        assertEquals(1, recurso.getId());
        assertEquals(0, recurso.getPosicionN());
        assertEquals(0, recurso.getPosicionM());
        assertEquals(3, recurso.getTurnosRestantes());
        assertEquals(0.5, recurso.getProbRecurso(), 0.001);
    }

    @Test
    public void testConstructor2() {
        Recursos recurso = new RecursosMock(2, 1, 1, 3);
        assertEquals(2, recurso.getId());
        assertEquals(1, recurso.getPosicionN());
        assertEquals(1, recurso.getPosicionM());
        assertEquals(3, recurso.getTurnosRestantes());
        assertEquals(0.0, recurso.getProbRecurso(), 0.001);
    }

    @Test
    public void testConstructor3() {
        DatosJuego dato = new DatosJuego();
        dato.setTurnosIniciales(5);
        Recursos recurso = new RecursosMock(3, 2, 2, dato);
        assertEquals(3, recurso.getId());
        assertEquals(2, recurso.getPosicionN());
        assertEquals(2, recurso.getPosicionM());
        assertEquals(5, recurso.getTurnosRestantes());
    }

    @Test
    public void testSetPosicion() {
        Recursos recurso = new RecursosMock();
        int[] posicion = {3, 4};
        try {
            recurso.setPosicion(posicion);
            assertEquals(3, recurso.getPosicionN());
            assertEquals(4, recurso.getPosicionM());
        } catch (TamañoArrayInvalido e) {
            fail("No debería lanzar una excepción aquí");
        }
    }

    @Test
    public void testSetPosicionInvalidArray() {
        Recursos recurso = new RecursosMock();
        int[] posicion = {3};
        try {
            recurso.setPosicion(posicion);
            fail("Debería lanzar una excepción TamañoArrayInvalido");
        } catch (TamañoArrayInvalido e) {
            // Excepción esperada, no hacer nada
        }
    }

    // Crear una clase Mock para Recursos con métodos adicionales que necesitemos para las pruebas
    class RecursosMock extends Recursos {
        public RecursosMock(int id, int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
            super(id, posicionN, posicionM, turnosRestantes, probRecurso);
        }
        public RecursosMock(int id, int posicionN, int posicionM, int turnosRestantes) {
            super(id, posicionN, posicionM, turnosRestantes);
        }
        public RecursosMock(int id, int posicionN, int posicionM, DatosJuego dato) {
            super(id, posicionN, posicionM, dato);
        }
        public RecursosMock() {}
        @Override
        public Class<?> getTipo() {
            return null;
        }
        @Override
        public void aplicarEfecto(Estudiante estudiante, Celda celda) {
            // Implementación ficticia del método para las pruebas
        }
    }
}