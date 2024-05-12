package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.entorno.Comida;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteAvanzadoTest {
    // Prueba para el método mover con dirección hacia arriba y recurso disponible
    @Test
    void testGetTipo() {
        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 1, 10, 0.5, 0.3, 0.2);
        assertEquals("EstudianteAvanzado", estudiante.getTipo());
    }

    @Test
    void testMover() {
        // Crear un tablero pequeño para las pruebas
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        for (int i = 0; i < 3; i++) {
            tablero.add(new Celda());
        }

        // Crear un estudiante en la posición (0, 0)
        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 1, 10, 0.5, 0.3, 0.2);
        estudiante.setPosicionN(0);
        estudiante.setPosicionM(0);

        // Obtener la cantidad de recursos en la celda actual del estudiante
        int recursosAntesDeMover = tablero.getElemento(0).getSiguiente().getData().getListaRecursos().getNumeroElementos();

        // Simular el movimiento del estudiante
        estudiante.mover(tablero);

        // Obtener la cantidad de recursos después de mover al estudiante
        int recursosDespuesDeMover = tablero.getElemento(0).getSiguiente().getData().getListaRecursos().getNumeroElementos();

        // Verificar que la cantidad de recursos en la celda actual haya disminuido
        assertTrue(recursosDespuesDeMover < recursosAntesDeMover);
    }
    @Test
    void testReproducirse() {
        // Crear dos estudiantes para la reproducción
        EstudianteAvanzado estudiante1 = new EstudianteAvanzado(1, 1, 10, 0.5, 0.3, 0.2);
        EstudianteAvanzado estudiante2 = new EstudianteAvanzado(2, 1, 10, 0.5, 0.3, 0.2);

        // Reproducir los estudiantes
        EstudianteAvanzado nuevoEstudiante = (EstudianteAvanzado) estudiante1.reproducirse(estudiante2);

        // Comprobar que el nuevo estudiante tiene valores promedio
        assertEquals((estudiante1.getGeneracion() + estudiante2.getGeneracion()) / 2, nuevoEstudiante.getGeneracion());
        assertEquals((estudiante1.getTiempoDeVida() + estudiante2.getTiempoDeVida()) / 2, nuevoEstudiante.getTiempoDeVida());
        assertEquals((estudiante1.getProbReproduccion() + estudiante2.getProbReproduccion()) / 2, nuevoEstudiante.getProbReproduccion());
        assertEquals((estudiante1.getProbClonacion() + estudiante2.getProbClonacion()) / 2, nuevoEstudiante.getProbClonacion());
        assertEquals((estudiante1.getProbMuerte() + estudiante2.getProbMuerte()) / 2, nuevoEstudiante.getProbMuerte());
    }

    @Test
    void testClonar() {
        // Crear un estudiante para clonar
        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 1, 10, 0.5, 0.3, 0.2);

        // Clonar el estudiante
        EstudianteAvanzado nuevoEstudiante = (EstudianteAvanzado) estudiante.clonar();

        // Comprobar que el nuevo estudiante tiene los mismos valores que el original
        assertEquals(estudiante.getGeneracion(), nuevoEstudiante.getGeneracion());
        assertEquals(estudiante.getTiempoDeVida(), nuevoEstudiante.getTiempoDeVida());
        assertEquals(estudiante.getProbReproduccion(), nuevoEstudiante.getProbReproduccion());
        assertEquals(estudiante.getProbClonacion(), nuevoEstudiante.getProbClonacion());
        assertEquals(estudiante.getProbMuerte(), nuevoEstudiante.getProbMuerte());
    }
}