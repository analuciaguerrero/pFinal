package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Montaña;
import com.example.demoJavafx.entorno.Pozo;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.Grafo.Grafo;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteAvanzadoTest {
    @Test
    public void testGetGrafoTab() {
        // Crear un juego con datos y un tablero
        DatosJuego dato = new DatosJuego();
        Tablero tablero = new Tablero(3, 3, dato);

        // Crear un estudiante avanzado
        EstudianteAvanzado estudianteAvanzado = new EstudianteAvanzado(1, 0, 5, 0.5, 0.2);

        // Obtener el grafo del tablero
        Grafo<Celda> grafoTab = estudianteAvanzado.getGrafoTab(dato, tablero);

        // Verificar que el grafo y sus aristas se hayan creado correctamente
        assertNotNull(grafoTab);
        assertEquals(9, grafoTab.getNodos().getNumeroElementos());
    }

    @Test
    public void testCalcularPesoArista() {
        // Crear un estudiante avanzado
        EstudianteAvanzado estudianteAvanzado = new EstudianteAvanzado(1, 0, 5, 0.5, 0.2);

        // Crear dos celdas con recursos
        Celda celda1 = new Celda(0, 0);
        Celda celda2 = new Celda(0, 1);
        celda1.agregarRecurso(new Recursos(1, 0, 0, 0) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda) {

            }
        }, true);
        celda2.agregarRecurso(new Recursos(2, 0, 0, 1) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda) {

            }
        }, true);

        // Calcular el peso de la arista entre las dos celdas
        int peso = estudianteAvanzado.calcularPesoArista(celda1, celda2, 1);

        // Verificar que el peso se haya calculado correctamente
        assertEquals(Integer.MAX_VALUE / 2, peso);
    }

    @Test
    public void testMoverSinRecursos() {
        // Crear un juego con datos y un tablero
        DatosJuego dato = new DatosJuego();
        Tablero tablero = new Tablero(3, 3, dato);

        // Crear un estudiante avanzado en una posición sin recursos
        EstudianteAvanzado estudianteAvanzado = new EstudianteAvanzado(1, 0, 5, 0.5, 0.2);
        estudianteAvanzado.setPosicionN(0);
        estudianteAvanzado.setPosicionM(0);

        // Crear un grafo vacío
        Grafo<Celda> grafoTab = new Grafo<>();

        // Intentar mover al estudiante sin recursos disponibles
        try {
            estudianteAvanzado.mover(dato, tablero);
            fail("Se esperaba que lanzara una excepción RecursosNoUtilizados");
        } catch (RecursosNoUtilizados e) {
            // Verificar que se lance la excepción esperada
            assertTrue(true);
        }
    }
}