package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteBasicoTest {
    @Test
    void testGetTipo() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3, 0.2);
        assertEquals("EstudianteBasico", estudiante.getTipo());
    }

    @Test
    void testMover() {
        // Crear un tablero pequeño para las pruebas
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        for (int i = 0; i < 3; i++) {
            tablero.add(new Celda());
        }

        // Crear un estudiante en la posición (0, 0)
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3, 0.2);

        // Mover el estudiante
        estudiante.mover(tablero);

        // Comprobar que el estudiante se ha movido correctamente
        assertEquals(1, estudiante.getPosicionN());
        assertEquals(0, estudiante.getPosicionM());
    }

    @Test
    void testReproducirse() {
        // Crear dos estudiantes para la reproducción
        EstudianteBasico estudiante1 = new EstudianteBasico(1, 1, 10, 0.5, 0.3, 0.2);
        EstudianteBasico estudiante2 = new EstudianteBasico(2, 1, 10, 0.5, 0.3, 0.2);

        // Reproducir los estudiantes
        EstudianteBasico nuevoEstudiante = (EstudianteBasico) estudiante1.reproducirse(estudiante2);

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
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3, 0.2);

        // Clonar el estudiante
        EstudianteBasico nuevoEstudiante = (EstudianteBasico) estudiante.clonar();

        // Comprobar que el nuevo estudiante tiene los mismos valores que el original
        assertEquals(estudiante.getGeneracion(), nuevoEstudiante.getGeneracion());
        assertEquals(estudiante.getTiempoDeVida(), nuevoEstudiante.getTiempoDeVida());
        assertEquals(estudiante.getProbReproduccion(), nuevoEstudiante.getProbReproduccion());
        assertEquals(estudiante.getProbClonacion(), nuevoEstudiante.getProbClonacion());
        assertEquals(estudiante.getProbMuerte(), nuevoEstudiante.getProbMuerte());
    }
}