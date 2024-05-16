package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteBasicoTest {
    @Test
    public void testMover() {
        EstudianteBasico estudiante = new EstudianteBasico(1, 0, 5, 50, 20);
        DatosJuego dato = new DatosJuego();
        Tablero tablero = new Tablero(5, 5, dato);
        Celda celda = new Celda(1, 1);
        tablero.getCelda(1, 1).agregarEstudiante(estudiante, false);
        estudiante.mover(dato, tablero);
        assertNotNull(tablero.getCelda(estudiante.getPosicionN(), estudiante.getPosicionM()));
    }
}