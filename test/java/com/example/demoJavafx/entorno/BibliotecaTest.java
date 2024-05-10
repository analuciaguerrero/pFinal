package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demoJavafx.estudiante.*;
class BibliotecaTest {
    // Clase de prueba de Estudiante
    private static class MockEstudiante extends Estudiante {
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte, posicionN, posicionM);
        }

        @Override
        public void mover(ListaEnlazada<Celda> tablero) {

        }

        @Override
        public Estudiante reproducirse(Estudiante pareja) {
            return null;
        }

        @Override
        public Estudiante clonar() {
            return null;
        }
    }

    @Test
    public void testConstructorWithAllParameters() {
        Biblioteca biblioteca = new Biblioteca(1, 2, 3, 0.5, 0.2, 0.7);
        assertEquals(1, biblioteca.getPosicionN());
        assertEquals(2, biblioteca.getPosicionM());
        assertEquals(3, biblioteca.getTurnosRestantes());
        assertEquals(0.5, biblioteca.getProbRecurso(), 0.001);
        assertEquals(0.2, biblioteca.getAumentoProbClonacion(), 0.001);
        assertEquals(0.7, biblioteca.getProbBiblioteca(), 0.001);
    }
    @Test
    public void testConstructorWithProbBibliotecaParameter() {
        Biblioteca biblioteca = new Biblioteca(0.8);
        assertEquals(0.8, Biblioteca.getProbBiblioteca(), 0.001);
    }

    @Test
    public void testGettersAndSetters() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setPosicionN(4);
        biblioteca.setPosicionM(5);
        biblioteca.setTurnosRestantes(6);
        biblioteca.setProbRecurso(0.7);
        biblioteca.setAumentoProbClonacion(0.3);
        biblioteca.setProbBiblioteca(0.8);

        assertEquals(4, biblioteca.getPosicionN());
        assertEquals(5, biblioteca.getPosicionM());
        assertEquals(6, biblioteca.getTurnosRestantes());
        assertEquals(0.7, biblioteca.getProbRecurso(), 0.001);
        assertEquals(0.3, biblioteca.getAumentoProbClonacion(), 0.001);
        assertEquals(0.8, biblioteca.getProbBiblioteca(), 0.001);
    }
    @Test
    public void testAplicarEfecto() {
        // Creamos una biblioteca con valores de prueba
        Biblioteca biblioteca = new Biblioteca(1, 2, 3, 0.5, 0.2, 0.7);

        // Creamos un estudiante de prueba con probabilidad de clonación que debería pasar por todos los casos
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.3, 0.3, 0.1, 0, 0);

        // Configuramos el comportamiento esperado del estudiante
        int originalId = estudiante.getId();

        // Aplicamos el efecto de la biblioteca
        biblioteca.aplicarEfecto(estudiante);

        // Verificamos que la probabilidad de clonación se haya incrementado correctamente
        assertEquals(0.5, estudiante.getProbClonacion(), 0.001); // Probabilidad de clonación esperada después del efecto

        // Verificamos que el tipo del estudiante se establezca correctamente
        String tipo = estudiante.getTipo();
        if (estudiante.getProbClonacion() >= 0.3 && estudiante.getProbClonacion() < 0.5) {
            assertEquals("EstudianteBasico", tipo);
        } else if (estudiante.getProbClonacion() >= 0.5 && estudiante.getProbClonacion() < 0.7) {
            assertEquals("EstudianteNormal", tipo);
        } else if (estudiante.getProbClonacion() >= 0.7 && estudiante.getProbClonacion() <= 1.0) {
            assertEquals("EstudianteAvanzado", tipo);
        } else {
            fail("El tipo de estudiante no es correcto");
        }

        // Verificamos que el id del estudiante no haya cambiado
        assertEquals(originalId, estudiante.getId());
    }
}