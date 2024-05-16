package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaDoblementeEnlazada.ElementoLDE;
import com.example.demoJavafx.excepciones.RecursosNoUtilizados;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteNormalTest {
    @Test
    public void testMoverConRecursosVacios() {
        EstudianteNormal estudiante = new EstudianteNormal(1, 0, 5, 50, 20);

        // Crear un objeto DatosJuego válido
        DatosJuego dato = new DatosJuego();

        // Crear un tablero de 5x5
        Tablero tablero = new Tablero(5, 5, dato);

        // Colocar al estudiante en la celda (1, 1)
        Celda celda = tablero.getCelda(1, 1);
        celda.agregarEstudiante(estudiante, false);

        // Establecer la posición inicial del estudiante
        estudiante.setPosicionN(1);
        estudiante.setPosicionM(1);

        // Vaciar los recursos en dato
        dato.getRecursos().vaciar();

        // Mover al estudiante
        try {
            estudiante.mover(dato, tablero);
        } catch (RecursosNoUtilizados e) {
            fail("Se lanzó una excepción RecursosNoUtilizados inesperadamente.");
        }

        // Obtener la nueva posición del estudiante
        int posN = estudiante.getPosicionN();
        int posM = estudiante.getPosicionM();

        // Verificar que la nueva posición del estudiante es diferente a la inicial
        assertTrue((posN != 1 || posM != 1));
        // Verificar que la nueva posición del estudiante está dentro del tablero
        assertNotNull(tablero.getCelda(posN, posM));
    }

    @Test
    public void testMoverConRecursos() {
        EstudianteNormal estudiante = new EstudianteNormal(1, 0, 5, 50, 20);

        // Crear un objeto DatosJuego válido
        DatosJuego dato = new DatosJuego();

        // Crear un tablero de 5x5
        Tablero tablero = new Tablero(5, 5, dato);

        // Colocar al estudiante en la celda (2, 2)
        Celda celda = tablero.getCelda(2, 2);
        celda.agregarEstudiante(estudiante, false);

        // Establecer la posición inicial del estudiante
        estudiante.setPosicionN(2);
        estudiante.setPosicionM(2);

        // Agregar un recurso en (3, 3)
        Recursos recurso = new Recursos() {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void aplicarEfecto(Estudiante estudiante, Celda celda) {

            }
        };
        recurso.setPosicionN(3);
        recurso.setPosicionM(3);
        dato.getRecursos().add(new ElementoLDE<>(recurso));

        // Mover al estudiante
        try {
            estudiante.mover(dato, tablero);
        } catch (RecursosNoUtilizados e) {
            fail("Se lanzó una excepción RecursosNoUtilizados inesperadamente.");
        }

        // Obtener la nueva posición del estudiante
        int posN = estudiante.getPosicionN();
        int posM = estudiante.getPosicionM();

        // Verificar que la nueva posición del estudiante está más cerca del recurso
        assertTrue((posN == 3 && posM == 2) || (posN == 2 && posM == 3));
        // Verificar que la nueva posición del estudiante está dentro del tablero
        assertNotNull(tablero.getCelda(posN, posM));
    }
}