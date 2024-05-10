package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.entorno.Comida;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.zombieStudentsLife.Celda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteAvanzadoTest {
    // Prueba para el método mover con dirección hacia arriba y recurso disponible
    @Test
    public void testMoverArribaConRecurso() {
        // Configuración del escenario
        int filaInicial = 1;
        int columnaInicial = 1;
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        Celda celda = new Celda();
        Recursos recurso = new Comida(0, 0, 0, 0, 5, 0.5);
        celda.getListaRecursos().add(new ElementoLE<>(recurso));
        tablero.add(new ElementoLE<>(celda));

        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 0, 1, 0.5, 0.2, 0.7);
        estudiante.setPosicionN(filaInicial);
        estudiante.setPosicionM(columnaInicial);

        // Ejecución del método
        estudiante.mover(tablero);

        // Comprobación de la nueva posición
        assertEquals(0, estudiante.getPosicionN());
        assertEquals(1, estudiante.getPosicionM());

        // Comprobación de los efectos aplicados por el recurso
        assertEquals(6, estudiante.getTiempoDeVida());
    }

    // Prueba para el método mover con dirección hacia arriba y sin recurso disponible
    @Test
    public void testMoverArribaSinRecurso() {
        // Configuración del escenario
        int filaInicial = 1;
        int columnaInicial = 1;
        ListaEnlazada<Celda> tablero = new ListaEnlazada<>();
        Celda celda = new Celda();
        tablero.add(new ElementoLE<>(celda));

        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 0, 1, 0.5, 0.2, 0.7);
        estudiante.setPosicionN(filaInicial);
        estudiante.setPosicionM(columnaInicial);

        // Ejecución del método
        estudiante.mover(tablero);

        // Comprobación de que la posición no cambió
        assertEquals(filaInicial, estudiante.getPosicionN());
        assertEquals(columnaInicial, estudiante.getPosicionM());
    }

    // Prueba para el método reproducirse
    @Test
    public void testReproducirse() {
        // Configuración del escenario
        EstudianteAvanzado estudiante1 = new EstudianteAvanzado(1, 0, 2, 0.5, 0.2, 0.7);
        EstudianteAvanzado estudiante2 = new EstudianteAvanzado(2, 1, 3, 0.6, 0.3, 0.8);

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
        EstudianteAvanzado estudiante = new EstudianteAvanzado(1, 0, 2, 0.5, 0.2, 0.7);

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