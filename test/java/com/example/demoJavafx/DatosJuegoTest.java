package com.example.demoJavafx;

import com.example.demoJavafx.entorno.Recursos;
import com.example.demoJavafx.estudiante.Estudiante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatosJuegoTest {
    @Test
    public void testConstructor() {
        DatosJuego datosJuego = new DatosJuego(/* Aquí pasas los parámetros necesarios */);

        assertNotNull(datosJuego);
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testGettersAndSetters() {
        DatosJuego datosJuego = new DatosJuego();

        datosJuego.setTurnosVidaIniciales(10);
        assertEquals(10, datosJuego.getTurnosVidaIniciales());

        // Asegúrate de probar todos los getters y setters de manera similar
    }

    @Test
    public void testGenerarEnteroAleatorio() {
        DatosJuego datosJuego = new DatosJuego();

        int enteroAleatorio = datosJuego.generarEnteroAleatorio(0, 10);
        assertTrue(enteroAleatorio >= 0 && enteroAleatorio <= 10);
    }

    @Test
    public void testObtenerEstudianteAleatorio() {
        DatosJuego datosJuego = new DatosJuego();
        // Agrega estudiantes a la lista antes de probar este método
        Estudiante estudiante = datosJuego.obtenerEstudianteAleatorio();
        assertNotNull(estudiante);
        // Asegúrate de probar más casos según sea necesario
    }

    @Test
    public void testObtenerRecursoAleatorio() {
        DatosJuego datosJuego = new DatosJuego();
        // Agrega recursos a la lista antes de probar este método
        Recursos recurso = datosJuego.obtenerRecursoAleatorio();
        assertNotNull(recurso);
        // Asegúrate de probar más casos según sea necesario
    }
}