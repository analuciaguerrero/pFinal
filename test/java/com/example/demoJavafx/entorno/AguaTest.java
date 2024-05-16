package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {
    @Test
    public void testConstructor1() {
        Agua agua = new Agua(1, 0, 0, 3, 0.5, 10, 0.7);
        assertEquals(10, agua.getAumentoVida());
        assertEquals(0.7, Agua.getProbAgua(), 0.001);
    }

    @Test
    public void testConstructor2() {
        Agua agua2 = new Agua(2, 1, 1, new DatosJuego());
        assertEquals(0, agua2.getAumentoVida());
        assertEquals(0.0, Agua.getProbAgua(), 0.001);
    }

    @Test
    public void testSetAumentoVida() {
        Agua agua = new Agua();
        try {
            agua.setAumentoVida(20);
            assertEquals(20, agua.getAumentoVida());
        } catch (IncrementoNoValido e) {
            fail("No debería lanzar una excepción aquí");
        }
    }
    @Test
    public void testConstructor() {
        double probAgua = 0.5; // Definir el valor esperado para probAgua
        Agua agua = new Agua(probAgua); // Crear una instancia de Agua con el constructor

        // Verificar si el valor de probAgua se asignó correctamente
        assertEquals(probAgua, Agua.getProbAgua(), 0.001); // Utilizar un delta pequeño para la comparación de valores dobles
    }
    @Test
    public void testSetAumentoVidaNegativo() {
        Agua agua = new Agua();
        try {
            agua.setAumentoVida(-5);
            fail("Debería lanzar una excepción IncrementoNoValido");
        } catch (IncrementoNoValido e) {
            // Excepción esperada, no hacer nada
        }
    }

    @Test
    public void testSetProbAgua() {
        Agua agua = new Agua();
        agua.setProbAgua(0.8);
        assertEquals(0.8, Agua.getProbAgua(), 0.001);
    }

    @Test
    public void testGetTipo() {
        Agua agua = new Agua();
        assertEquals(Agua.class, agua.getTipo());
    }
}