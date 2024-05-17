package com.example.demoJavafx.entorno;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estudiante.Estudiante;
import com.example.demoJavafx.estudiante.EstudianteBasico;
import com.example.demoJavafx.excepciones.IncrementoNoValido;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {
    private Agua agua;
    @BeforeAll
    public static void initJFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("Timeout waiting for JavaFX to start");
        }
    }
    @BeforeEach
    public void setUp() {
        agua = new Agua(1, 2, 3, 4, 0.5, 10, 0.8);
    }

    @Test
    public void testConstructorWithAllParams() {
        Agua agua = new Agua(1, 2, 3, 4, 0.5, 10, 0.8);
        assertEquals(10, agua.getAumentoVida());
        assertEquals(0.8, Agua.getProbAgua());
    }

    @Test
    public void testConstructorWithDatosJuego() {
        DatosJuego datosJuego = new DatosJuego(10, 0.5, 0.3, 0.2, 0.1, 0.6, 5, 0.4, 0.7, 0.8, 0.9, 0.1, 0.2, 15, 20, 25, 0.5, 0.3, 10, 10, 1);
        Agua agua = new Agua(1, 2, 3, datosJuego);
        assertEquals(datosJuego.getAumentoVidaAgua(), agua.getAumentoVida());
    }

    @Test
    public void testDefaultConstructor() {
        Agua agua = new Agua();
        assertNotNull(agua);
    }

    @Test
    public void testConstructorWithProbAgua() {
        Agua agua = new Agua(0.8);
        assertEquals(0.8, Agua.getProbAgua());
    }

    @Test
    public void testGetAumentoVida() {
        assertEquals(10, agua.getAumentoVida());
    }

    @Test
    public void testSetAumentoVida() {
        agua.setAumentoVida(20);
        assertEquals(20, agua.getAumentoVida());
    }

    @Test
    public void testSetAumentoVidaThrowsException() {
        assertThrows(IncrementoNoValido.class, () -> {
            agua.setAumentoVida(-1);
        });
    }

    @Test
    public void testGetProbAgua() {
        assertEquals(0.8, Agua.getProbAgua());
    }

    @Test
    public void testSetProbAgua() {
        agua.setProbAgua(0.9);
        assertEquals(0.9, Agua.getProbAgua());
    }

    @Test
    public void testGetTipo() {
        assertEquals(Agua.class, agua.getTipo());
    }

    @Test
    public void testAplicarEfecto() {
        // Crear instancias necesarias
        DatosJuego datosJuego = new DatosJuego();
        Tablero tablero = new Tablero(datosJuego);
        Celda celda = new Celda(1, 1, datosJuego, tablero);
        EstudianteBasico estudiante = new EstudianteBasico(1, 1, 10, 0.5, 0.3);

        // Verificar el tiempo de vida inicial del estudiante
        int tiempoDeVidaInicial = estudiante.getTiempoDeVida();

        // Aplicar el efecto de agua
        agua.aplicarEfecto(estudiante, celda);

        // Verificar que el efecto se haya aplicado correctamente
        assertEquals(tiempoDeVidaInicial + agua.getAumentoVida(), estudiante.getTiempoDeVida());
    }
}