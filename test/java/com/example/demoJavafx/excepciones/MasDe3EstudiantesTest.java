package com.example.demoJavafx.excepciones;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MasDe3EstudiantesTest {
    // Prueba para el constructor de MasDe3Estudiantes
    @Test
    public void testMasDe3EstudiantesConstructor() {
        // Configuración del escenario
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ListaEnlazada<Estudiante> listaEstudiantes = new ListaEnlazada<>();
        Estudiante estudiante1 = new EstudianteBasico(1, 1, 5, 0.5, 0.2, 0.7);
        Estudiante estudiante2 = new EstudianteBasico(2, 1, 8, 0.4, 0.3, 0.6);
        Estudiante estudiante3 = new EstudianteBasico(3, 1, 6, 0.6, 0.4, 0.8);
        listaEstudiantes.add(new ElementoLE<>(estudiante1));
        listaEstudiantes.add(new ElementoLE<>(estudiante2));
        listaEstudiantes.add(new ElementoLE<>(estudiante3));

        // Ejecución del método
        new MasDe3Estudiantes(listaEstudiantes);

        // Verificación de la salida estándar
        String expectedOutput = "\nERROR. Se intentó agregar un recurso a una celda que ya tiene 3 estudiantes:" + listaEstudiantes.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}