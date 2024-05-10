package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteBasicoTest {
    // Prueba para el método mover cuando la nueva posición es válida
    @Test
    public void testMoverNuevaPosicionValida() {
        // Configuración del escenario
        int filaInicial = 1;
        int columnaInicial = 1;
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        for (int i = 0; i < 3; i++) {
            Celda celda = new Celda();
            tablero.add(new ElementoLE<>(celda));
        }
        EstudianteBasico estudiante = new EstudianteBasico(1, 0, 1, 0.5, 0.2, 0.7);
        estudiante.setPosicionN(filaInicial);
        estudiante.setPosicionM(columnaInicial);

        // Ejecución del método
        estudiante.mover(tablero);

        // Comprobación de la nueva posición
        assertEquals(filaInicial + 1, estudiante.getPosicionN());
        assertEquals(columnaInicial, estudiante.getPosicionM());
    }

    // Prueba para el método mover cuando la nueva posición no es válida
    @Test
    public void testMoverNuevaPosicionNoValida() {
        // Configuración del escenario
        int filaInicial = 2;
        int columnaInicial = 1;
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        for (int i = 0; i < 3; i++) {
            Celda celda = new Celda();
            tablero.add(new ElementoLE<>(celda));
        }
        EstudianteBasico estudiante = new EstudianteBasico(1, 0, 1, 0.5, 0.2, 0.7);
        estudiante.setPosicionN(filaInicial);
        estudiante.setPosicionM(columnaInicial);

        // Ejecución del método
        estudiante.mover(tablero);

        // Comprobación de que la posición no ha cambiado
        assertEquals(filaInicial, estudiante.getPosicionN());
        assertEquals(columnaInicial, estudiante.getPosicionM());
    }

    // Prueba para el método reproducirse
    @Test
    public void testReproducirse() {
        // Configuración del escenario
        EstudianteBasico estudiante1 = new EstudianteBasico(1, 0, 2, 0.5, 0.2, 0.7);
        EstudianteBasico estudiante2 = new EstudianteBasico(2, 1, 3, 0.6, 0.3, 0.8);

        // Ejecución del método
        Estudiante nuevoEstudiante = estudiante1.reproducirse(estudiante2);

        // Comprobación de los valores del nuevo estudiante
        assertEquals(0, nuevoEstudiante.getId()); // El id se establece aleatoriamente, no se puede predecir
        assertEquals(1, nuevoEstudiante.getGeneracion());
        assertEquals((2 + 3) / 2, nuevoEstudiante.getTiempoDeVida());
        assertEquals((0.5 + 0.6) / 2, nuevoEstudiante.getProbReproduccion(), 0.001);
        assertEquals((0.2 + 0.3) / 2, nuevoEstudiante.getProbClonacion(), 0.001);
        assertEquals((0.7 + 0.8) / 2, nuevoEstudiante.getProbMuerte(), 0.001);
    }

    // Prueba para el método clonar
    @Test
    public void testClonar() {
        // Configuración del escenario
        EstudianteBasico estudiante = new EstudianteBasico(1, 0, 2, 0.5, 0.2, 0.7);

        // Ejecución del método
        Estudiante nuevoEstudiante = estudiante.clonar();

        // Comprobación de los valores del nuevo estudiante
        assertEquals(0, nuevoEstudiante.getId()); // El id se establece aleatoriamente, no se puede predecir
        assertEquals(0, nuevoEstudiante.getGeneracion());
        assertEquals(2, nuevoEstudiante.getTiempoDeVida());
        assertEquals(0.5, nuevoEstudiante.getProbReproduccion(), 0.001);
        assertEquals(0.2, nuevoEstudiante.getProbClonacion(), 0.001);
        assertEquals(0.7, nuevoEstudiante.getProbMuerte(), 0.001);
    }
}