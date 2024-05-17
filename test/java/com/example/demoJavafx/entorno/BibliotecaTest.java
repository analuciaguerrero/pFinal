package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demoJavafx.estudiante.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BibliotecaTest{
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
    public void testAplicarEfecto(){
        EstudianteBasico i = new EstudianteBasico(2,2,2,2,85);
        Biblioteca b = new Biblioteca();
        b.setAumentoProbClonacion(10);
        DatosJuego datosJuego = new DatosJuego(10, 50, 10, 50,25, 5,15,20,20,20, 10,10,10,3,5, 7, 25, 10, 10, 10, 0);
        Tablero tablero = new Tablero(datosJuego.getFilasDelTablero(), datosJuego.getColumnasDelTablero(), datosJuego);

        Celda celda = new Celda(i.getPosicionN(), i.getPosicionM(), datosJuego, tablero);

        assertDoesNotThrow(()->b.aplicarEfecto(i, celda));
        assertEquals(95, i.getProbClonacion(), "El incremento no es correcto");
        assertEquals("EstudianteBasico", i.getTipo(), "El tipo no es correcto");
        assertDoesNotThrow(()->b.aplicarEfecto(i, celda));
        assertEquals(100, i.getProbClonacion(), "El incremento no es correcto");
        assertEquals("EstudianteBasico", i.getTipo(), "EL tipo no es correcto");    }
}