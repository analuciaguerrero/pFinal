package com.example.demoJavafx.excepciones;

import com.example.demoJavafx.entorno.Comida;
import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ElementoLE;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MasDe3RecursosTest {
    // Prueba para el constructor de MasDe3Recursos
    @Test
    public void testMasDe3RecursosConstructor() {
        // Configuración del escenario
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ListaEnlazada<Recursos> listaRecursos = new ListaEnlazada<>();
        Recursos recurso1 = new Comida(0, 0, 5, 0.5, 2, 0.7);
        Recursos recurso2 = new Comida(0, 0, 8, 0.4, 3, 0.6);
        Recursos recurso3 = new Comida(0, 0, 6, 0.6, 4, 0.8);
        listaRecursos.add(new ElementoLE<>(recurso1));
        listaRecursos.add(new ElementoLE<>(recurso2));
        listaRecursos.add(new ElementoLE<>(recurso3));

        // Ejecución del método
        new MasDe3Recursos(listaRecursos);

        // Verificación de la salida estándar
        String expectedOutput = "\nERROR. Se intentó agregar un recurso a una celda que ya tiene 3 recursos:" + listaRecursos.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}