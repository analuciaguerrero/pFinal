package com.example.demoJavafx.entorno;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.tablero.Celda;
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
    public void testAplicarEfecto_EstudianteBasicoProbabilidadClonacionEntre0_5Y0_7_TipoEstudianteNormal() {
        // Arrange
        Estudiante estudiante = new EstudianteBasico(1, 1, 10, 0.6, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteNormal);
    }

    @Test
    public void testAplicarEfecto_EstudianteBasicoProbabilidadClonacionMayor0_7_TipoEstudianteAvanzado() {
        // Arrange
        Estudiante estudiante = new EstudianteBasico(1, 1, 10, 0.8, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteAvanzado);
    }

    @Test
    public void testAplicarEfecto_EstudianteBasicoProbabilidadClonacionMenor0_5_TipoEstudianteBasico() {
        // Arrange
        Estudiante estudiante = new EstudianteBasico(1, 1, 10, 0.4, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteBasico);
    }

    @Test
    public void testAplicarEfecto_EstudianteNormalProbabilidadClonacionMenor0_5_TipoEstudianteBasico() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.4, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteBasico);
    }

    @Test
    public void testAplicarEfecto_EstudianteNormalProbabilidadClonacionEntre0_5Y0_7_TipoEstudianteNormal() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.6, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteNormal);
    }

    @Test
    public void testAplicarEfecto_EstudianteNormalProbabilidadClonacionMayor0_7_TipoEstudianteAvanzado() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.8, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteAvanzado);
    }

    @Test
    public void testAplicarEfecto_EstudianteAvanzadoProbabilidadClonacionMenor0_5_TipoEstudianteBasico() {
        // Arrange
        Estudiante estudiante = new EstudianteAvanzado(1, 1, 10, 0.4, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteBasico);
    }

    @Test
    public void testAplicarEfecto_EstudianteAvanzadoProbabilidadClonacionEntre0_5Y0_7_TipoEstudianteNormal() {
        // Arrange
        Estudiante estudiante = new EstudianteAvanzado(1, 1, 10, 0.6, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteNormal);
    }

    @Test
    public void testAplicarEfecto_EstudianteAvanzadoProbabilidadClonacionMayor0_7_TipoEstudianteAvanzado() {
        // Arrange
        Estudiante estudiante = new EstudianteAvanzado(1, 1, 10, 0.8, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertTrue(estudiante instanceof EstudianteAvanzado);
    }

    @Test
    public void testAplicarEfecto_EstudianteTipoDesconocido_NoSeModificaTipo() {
        // Arrange
        class EstudianteDesconocido extends Estudiante {
            public EstudianteDesconocido() {
                super(0, 0, 0, 0.0, 0.0, 0.0);
            }
            @Override
            public String getTipo() {
                return "Desconocido";
            }
            @Override
            public void mover(ListaEnlazada<Celda> tablero) {}
            @Override
            public Estudiante reproducirse(Estudiante pareja) {
                return null;
            }
            @Override
            public Estudiante clonar() {
                return null;
            }
        }

        Estudiante estudiante = new EstudianteDesconocido();
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);
        String tipoOriginal = estudiante.getTipo();

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals(tipoOriginal, estudiante.getTipo());
    }
    @Test
    public void testAplicarEfecto_EstudianteProbabilidadBaja_TipoEstudianteBasico() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.1, 0.1, 0.1);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteBasico", estudiante.getTipo());
    }

    @Test
    public void testAplicarEfecto_EstudianteProbabilidadMedia_TipoEstudianteNormal() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.6, 0.6, 0.6);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteNormal", estudiante.getTipo());
    }

    @Test
    public void testAplicarEfecto_EstudianteProbabilidadAlta_TipoEstudianteAvanzado() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.9, 0.9, 0.9);
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteAvanzado", estudiante.getTipo());
    }
    @Test
    public void testAplicarEfecto_EstudianteProbabilidadAlta_TipoEstudianteAvanzado1() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.9, 0.9, 0.9); // Alta probabilidad de clonación
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteAvanzado", estudiante.getTipo());
    }

    @Test
    public void testAplicarEfecto_EstudianteProbabilidadMedia_TipoEstudianteNormal1() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.6, 0.6, 0.6); // Probabilidad media de clonación
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteNormal", estudiante.getTipo());
    }
    @Test
    public void testAplicarEfecto_EstudianteProbabilidadIntermedia_TipoEstudianteNormal() {
        // Arrange
        Estudiante estudiante = new EstudianteNormal(1, 1, 10, 0.6, 0.6, 0.6); // Probabilidad de clonación entre 0.5 y 0.7
        Biblioteca biblioteca = new Biblioteca(0, 0, 0, 0.1, 0.2, 0.3);

        // Act
        biblioteca.aplicarEfecto(estudiante);

        // Assert
        assertEquals("EstudianteNormal", estudiante.getTipo());
    }
}