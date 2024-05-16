package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demoJavafx.estudiante.*;
class BibliotecaTest {
    @Test
    void getIncrementoProbClonacion() {
        Biblioteca b = new Biblioteca();
        b.setAumentoProbClonacion(3);
        assertDoesNotThrow(()->b.getAumentoProbClonacion());
        assertEquals(3, b.getAumentoProbClonacion(), "El incremento no es correcto");
    }

    @Test
    void setIncrementoProbClonacion() {
        Biblioteca b = new Biblioteca();
        assertDoesNotThrow(()->b.setAumentoProbClonacion(3));
        assertEquals(3, b.getAumentoProbClonacion(), "El incremento no es correcto");
        assertThrows(ProbabilidadNoValida.class, ()->b.setAumentoProbClonacion(-1));
        assertThrows(ProbabilidadNoValida.class, ()->b.setAumentoProbClonacion(101));
    }

    @Test
    void aplicarMejora() {
        EstudianteBasico i = new EstudianteBasico(2,2,2,2,85);
        Biblioteca b = new Biblioteca();
        b.setAumentoProbClonacion(10);
        DatosJuego dato = new DatosJuego(10, 50, 10, 50,25, 5,15,20,20,20, 10,10,10,3,5, 7, 25, 10, 10, 10, 0);
        Tablero tablero = new Tablero(dato.getFilasDelTablero(), dato.getColumnasDelTablero(), dato);

        Celda celda = new Celda(i.getPosicionN(), i.getPosicionM(), dato, tablero);

        assertDoesNotThrow(()->b.aplicarEfecto(i, celda));
        assertEquals(95, i.getProbClonacion(), "El incremento no es correcto");
        assertEquals("EstudianteBasico", i.getTipo(), "El tipo no es correcto");
        assertDoesNotThrow(()->b.aplicarEfecto(i, celda));
        assertEquals(100, i.getProbClonacion(), "El incremento no es correcto");
        assertEquals("EstudianteBasico", i.getTipo(), "EL tipo no es correcto");
    }
}