package com.example.demoJavafx.estudiante;
import com.example.demoJavafx.estructurasDeDatos.ArbolDeBusqueda.BST;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
    // Clase de prueba de Estudiante
    private static class MockEstudiante extends Estudiante {
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte, int posicionN, int posicionM) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte, posicionN, posicionM);
        }
        public MockEstudiante(int id, int generacion, int tiempoDeVida, double probReproduccion, double probClonacion, double probMuerte) {
            super(id, generacion, tiempoDeVida, probReproduccion, probClonacion, probMuerte);
        }
        public MockEstudiante(int id){
            super(id);
        }
        public MockEstudiante(int id, int tiempoDeVida){
            super(id, tiempoDeVida);
        }
        public MockEstudiante(){}

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
    // Pruebas para el constructor con todos los parámetros
    @Test
    public void testConstructorWithAllParameters() {
        MockEstudiante estudiante = new MockEstudiante(1, 2, 50, 0.5, 0.3, 0.1, 3, 4);
        assertEquals(1, estudiante.getId());
        assertEquals(2, estudiante.getGeneracion());
        assertEquals(50, estudiante.getTiempoDeVida());
        assertEquals(0.5, estudiante.getProbReproduccion(), 0.001);
        assertEquals(0.3, estudiante.getProbClonacion(), 0.001);
        assertEquals(0.1, estudiante.getProbMuerte(), 0.001);
        assertEquals(3, estudiante.getPosicionN());
        assertEquals(4, estudiante.getPosicionM());
    }
    @Test
    public void testConstructorPorDefecto() {
        MockEstudiante estudiante = new MockEstudiante();

        // Verificar que los valores iniciales son los esperados
        assertEquals(0, estudiante.getId());
        assertEquals(0, estudiante.getGeneracion());
        assertEquals(0, estudiante.getTiempoDeVida(), 0);
        assertEquals(0.0, estudiante.getProbReproduccion(), 0);
        assertEquals(0.0, estudiante.getProbClonacion(), 0);
        assertEquals(0.0, estudiante.getProbMuerte(), 0);
    }

    // Prueba para el constructor con solo el ID
    @Test
    public void testConstructorWithIdOnly() {
        MockEstudiante estudiante = new MockEstudiante(1);
        assertEquals(1, estudiante.getId());
        assertEquals(0, estudiante.getTiempoDeVida());
        // Añade más pruebas para otros valores predeterminados
    }

    // Pruebas para el método actualizar
    @Test
    public void testActualizar() {
        MockEstudiante estudiante = new MockEstudiante(1, 50);
        estudiante.actualizar();
        assertEquals(49, estudiante.getTiempoDeVida());
        // Añade más pruebas para otros cambios esperados en las probabilidades y el tiempo de vida
    }
    // Prueba para el setter de posicionN
    @Test
    public void testSetPosicionN() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setPosicionN(5);
        assertEquals(5, estudiante.getPosicionN());
    }

    // Prueba para el setter de posicionM
    @Test
    public void testSetPosicionM() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setPosicionM(8);
        assertEquals(8, estudiante.getPosicionM());
    }

    // Prueba para el setter de generacion
    @Test
    public void testSetGeneracion() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setGeneracion(3);
        assertEquals(3, estudiante.getGeneracion());
    }

    // Prueba para el setter de tiempoDeVida
    @Test
    public void testSetTiempoDeVida() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setTiempoDeVida(50);
        assertEquals(50, estudiante.getTiempoDeVida());
    }

    // Prueba para el setter de probReproduccion
    @Test
    public void testSetProbReproduccion() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setProbReproduccion(0.5);
        assertEquals(0.5, estudiante.getProbReproduccion(), 0.001);
    }

    // Prueba para el setter de probClonacion
    @Test
    public void testSetProbClonacion() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setProbClonacion(0.3);
        assertEquals(0.3, estudiante.getProbClonacion(), 0.001);
    }

    // Prueba para el setter de probMuerte
    @Test
    public void testSetProbMuerte() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setProbMuerte(0.1);
        assertEquals(0.1, estudiante.getProbMuerte(), 0.001);
    }
    @Test
    public void testGetPosicion() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setPosicionN(5);
        estudiante.setPosicionM(8);

        int[] expectedPosicion = {5, 8};
        assertArrayEquals(expectedPosicion, estudiante.getPosicion());
    }

    // Prueba para el método setVivo
    @Test
    public void testSetVivo() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setVivo(false);
        assertFalse(estudiante.isVivo());
    }
    @Test
    public void testSetId() {
        MockEstudiante estudiante = new MockEstudiante(1);
        estudiante.setId(2);
        assertEquals(2, estudiante.getId());
    }
    @Test
    public void testGetSetTipo() {
        MockEstudiante estudiante = new MockEstudiante(1);

        // Establecer el tipo y comprobar si se obtiene correctamente
        estudiante.setTipo("Basico");
        assertEquals("Basico", estudiante.getTipo());

        // Establecer un nuevo tipo y comprobar si se actualiza correctamente
        estudiante.setTipo("Normal");
        assertEquals("Normal", estudiante.getTipo());

        // Establecer otro tipo y comprobar si se actualiza correctamente
        estudiante.setTipo("Avanzado");
        assertEquals("Avanzado", estudiante.getTipo());
    }
    // Prueba para el método puedeReproducirse cuando el tiempo de vida y la probabilidad de reproducción son mayores que 0
    @Test
    public void testPuedeReproducirseTrue() {
        MockEstudiante estudiante = new MockEstudiante(1, 0, 1, 0.5, 0.2, 0.7);
        assertTrue(estudiante.puedeReproducirse());
    }

    // Prueba para el método puedeReproducirse cuando el tiempo de vida es 0
    @Test
    public void testPuedeReproducirseTiempoDeVidaCero() {
        MockEstudiante estudiante = new MockEstudiante(1, 0, 0, 0.5, 0.2, 0.7);
        assertFalse(estudiante.puedeReproducirse());
    }

    // Prueba para el método puedeReproducirse cuando la probabilidad de reproducción es 0
    @Test
    public void testPuedeReproducirseProbReproduccionCero() {
        MockEstudiante estudiante = new MockEstudiante(1, 0, 1, 0.0, 0.2, 0.7);
        assertFalse(estudiante.puedeReproducirse());
    }
    // Prueba para el método sobrevive cuando rand.nextDouble() >= probMuerte
    @Test
    public void testSobreviveTrue() {
        MockEstudiante estudiante = new MockEstudiante(1, 0, 1, 0.5, 0.2, 0.7);
        assertFalse(estudiante.sobrevive());
    }

    // Prueba para el método sobrevive cuando rand.nextDouble() < probMuerte
    @Test
    public void testSobreviveFalse() {
        MockEstudiante estudiante = new MockEstudiante(1, 0, 1, 0.5, 0.2, 0.3);
        assertTrue(estudiante.sobrevive());
    }



    // Prueba para el método getArbolGenealogico
    @Test
    public void testGetArbolGenealogico() {
        MockEstudiante estudiante = new MockEstudiante(1);
        BST<Estudiante> arbol = new BST<>();
        arbol.add(estudiante);
        estudiante.setArbolGenealogico(arbol);
        assertEquals(arbol, estudiante.getArbolGenealogico());
    }

    // Prueba para el método setArbolGenealogico
    @Test
    public void testSetArbolGenealogico() {
        MockEstudiante estudiante = new MockEstudiante(1);
        BST<Estudiante> arbol = new BST<>();
        arbol.add(estudiante);
        estudiante.setArbolGenealogico(arbol);
        assertEquals(arbol, estudiante.getArbolGenealogico());
    }
}