package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.DatosJuego;
import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.excepciones.MasDe3Estudiantes;
import com.example.demoJavafx.tablero.Celda;
import com.example.demoJavafx.tablero.Tablero;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudiantePropertiesTest {
    @Test
    public void testRollback() {
        Estudiante estudiante = new Estudiante(1, 0, 5, 0.5, 0.2) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        };
        EstudianteProperties estudianteProperties = new EstudianteProperties(estudiante);

        // Cambiar valores
        estudianteProperties.tiempoDeVidaProperty().set(10);
        estudianteProperties.probReproduccionProperty().set(0.8);
        estudianteProperties.probClonacionProperty().set(0.4);
        estudianteProperties.probMuerteProperty().set(0.1);

        // Realizar rollback
        estudianteProperties.rollback();

        // Verificar que los valores originales se han restaurado
        assertEquals(5, estudiante.getTiempoDeVida());
        assertEquals(0.5, estudiante.getProbReproduccion(), 0.01);
        assertEquals(0.2, estudiante.getProbClonacion(), 0.01);
    }

    @Test
    public void testCommit() {
        Estudiante estudiante = new Estudiante(1, 0, 5, 0.5, 0.2) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        };
        EstudianteProperties estudianteProperties = new EstudianteProperties(estudiante);

        // Cambiar valores
        estudianteProperties.tiempoDeVidaProperty().set(10);
        estudianteProperties.probReproduccionProperty().set(0.8);
        estudianteProperties.probClonacionProperty().set(0.4);

        // Realizar commit
        estudianteProperties.commit();

        // Verificar que los valores del estudiante se han actualizado
        assertEquals(10, estudiante.getTiempoDeVida());
        assertEquals(0.8, estudiante.getProbReproduccion(), 0.01);
        assertEquals(0.4, estudiante.getProbClonacion(), 0.01);
    }
    @Test
    public void testGetOrigen() {
        // Creamos una instancia de EstudianteProperties
        EstudianteProperties estudianteProperties = new EstudianteProperties();

        // Establecemos un valor de origen para la instancia
        Estudiante esperado = new Estudiante(1, 20) {
            @Override
            public Class<?> getTipo() {
                return null;
            }

            @Override
            public void mover(DatosJuego dato, Tablero tablero) throws MasDe3Estudiantes {

            }
        };
        estudianteProperties.setOrigen(esperado);

        // Verificamos si el método getOrigen() devuelve el valor esperado
        assertEquals(esperado, estudianteProperties.getOrigen());
    }
}
