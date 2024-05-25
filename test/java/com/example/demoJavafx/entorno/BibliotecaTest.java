package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.OtrasEstructuras.Cola;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteAvanzado;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.estudiante.EstudianteNormal;
import com.example.demoJavafx.excepciones.ProbabilidadNoValida;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest{
    private DatosJuego datosJuego;
    private Biblioteca biblioteca;
    private Celda celda;

    @BeforeEach
    void setUp() {
        datosJuego = new DatosJuego(10, 0.5, 0.4, 0.3, 0.2, 0.1, 5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 2, 3, 4, 0.3, 0.4, 5, 5, 1);
        biblioteca = new Biblioteca(1, datosJuego);
        celda = new Celda(0, 0);
    }

    @Test
    void testConstructorWithFullParams() {
        Biblioteca newBiblioteca = new Biblioteca(1, 2, 3, datosJuego);
        assertEquals(1, newBiblioteca.getId());
        assertEquals(2, newBiblioteca.getPosicionN());
        assertEquals(3, newBiblioteca.getPosicionM());
        assertEquals(datosJuego.getAumentoProbClonacion(), newBiblioteca.getAumentoProbClonacion());
    }

    @Test
    void testConstructorWithIdAndDatos() {
        Biblioteca newBiblioteca = new Biblioteca(1, datosJuego);
        assertEquals(1, newBiblioteca.getId());
        assertEquals(datosJuego.getAumentoProbClonacion(), newBiblioteca.getAumentoProbClonacion());
    }

    @Test
    void testGetAumentoProbClonacion() {
        assertEquals(datosJuego.getAumentoProbClonacion(), biblioteca.getAumentoProbClonacion());
    }

    @Test
    void testSetAumentoProbClonacion() {
        try {
            biblioteca.setAumentoProbClonacion(50);
            assertEquals(50, biblioteca.getAumentoProbClonacion());
        } catch (ProbabilidadNoValida e) {
            fail("Exception should not be thrown for valid probability");
        }
    }

    @Test
    void testSetAumentoProbClonacionThrowsException() {
        assertThrows(ProbabilidadNoValida.class, () -> {
            biblioteca.setAumentoProbClonacion(-1);
        });
        assertThrows(ProbabilidadNoValida.class, () -> {
            biblioteca.setAumentoProbClonacion(101);
        });
    }

    @Test
    void testGetTipo() {
        assertEquals(Biblioteca.class, biblioteca.getTipo());
    }

    @Test
    void testAplicarEfecto() {
        EstudianteBasico estudianteBasico = new EstudianteBasico();
        celda.agregarEstudiante(estudianteBasico, true);
        int turno = 1;

        double initialProbClonacion = estudianteBasico.getProbClonacion();
        biblioteca.aplicarEfecto(estudianteBasico, celda, turno);

        // Verificar que la operación se ha añadido a la cola del estudiante
        Cola<String> colaOperaciones = estudianteBasico.getColaDeOperaciones();
        assertFalse(colaOperaciones.isVacia());
        assertEquals("Acción: efecto Biblioteca, turno: " + turno, colaOperaciones.peek());

        // Verificar que la probabilidad de clonación del estudiante ha aumentado correctamente
        if (initialProbClonacion + biblioteca.getAumentoProbClonacion() > 100) {
            assertEquals(100, estudianteBasico.getProbClonacion());
        } else {
            assertEquals(initialProbClonacion + biblioteca.getAumentoProbClonacion(), estudianteBasico.getProbClonacion());
        }

        // Verificar la evolución del estudiante
        assertTrue(celda.getListaEstudiantes().getPosicion(new EstudianteNormal(estudianteBasico)) != null);
        assertFalse(celda.getListaEstudiantes().getPosicion(estudianteBasico) != null);
        assertEquals("Acción: evolucionar estudiante, turno: " + turno, colaOperaciones.peek());
    }

    @Test
    void testAplicarEfectoEstudianteNormal() {
        EstudianteNormal estudianteNormal = new EstudianteNormal(new Estudiante() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) {

            }
        });
        celda.agregarEstudiante(estudianteNormal, true);
        int turno = 1;

        biblioteca.aplicarEfecto(estudianteNormal, celda, turno);

        // Verificar la evolución del estudiante a EstudianteAvanzado
        assertTrue(celda.getListaEstudiantes().getPosicion(new EstudianteAvanzado(estudianteNormal)) != null);
        assertFalse(celda.getListaEstudiantes().getPosicion(estudianteNormal) != null);
        assertEquals("Acción: efecto Biblioteca, turno: " + turno, estudianteNormal.getColaDeOperaciones().peek());
    }
}