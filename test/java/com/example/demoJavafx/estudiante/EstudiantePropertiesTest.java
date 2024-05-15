package com.example.demoJavafx.estudiante;

import com.example.demoJavafx.estructurasDeDatos.ListaEnlazada.ListaEnlazada;
import com.example.demoJavafx.tablero.Celda;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudiantePropertiesTest {
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
    public void testConstructor() {
        // Arrange
        MockEstudiante estudiante = new MockEstudiante(1, 1, 10, 0.5, 0.5, 0.5);

        // Act
        EstudianteProperties estudianteProperties = new EstudianteProperties(estudiante);

        // Assert
        assertNotNull(estudianteProperties);
        assertEquals(estudiante, estudianteProperties.getOrigen());
    }

    @Test
    public void testRollback() {
        MockEstudiante estudiante = new MockEstudiante(1, 1, 10, 0.5, 0.2, 0.1);
        EstudianteProperties properties = new EstudianteProperties(estudiante);

        // Modificar las propiedades
        properties.tiempoDeVidaProperty().setValue(20);
        properties.probReproduccionProperty().setValue(0.8);
        properties.probClonacionProperty().setValue(0.3);
        properties.probMuerteProperty().setValue(0.2);

        // Hacer rollback
        properties.rollback();

        // Verificar que las propiedades han sido restauradas
        assertEquals(estudiante.getTiempoDeVida(), properties.tiempoDeVidaProperty().getValue().intValue());
        assertEquals((int) estudiante.getProbReproduccion(), properties.probReproduccionProperty().getValue().intValue());
        assertEquals((int) estudiante.getProbClonacion(), properties.probClonacionProperty().getValue().intValue());
        assertEquals((int) estudiante.getProbMuerte(), properties.probMuerteProperty().getValue().intValue());
    }
    @Test
    public void testCommit() {
        MockEstudiante estudiante = new MockEstudiante(1, 1, 10, 0.5, 0.2, 0.1);
        EstudianteProperties properties = new EstudianteProperties(estudiante);

        // Modificar las propiedades
        properties.tiempoDeVidaProperty().setValue(20);
        properties.probReproduccionProperty().setValue(0.8);
        properties.probClonacionProperty().setValue(0.3);
        properties.probMuerteProperty().setValue(0.2);

        // Hacer commit
        properties.commit();

        // Verificar que las propiedades de origen han sido actualizadas
        assertEquals(properties.tiempoDeVidaProperty().getValue().intValue(), estudiante.getTiempoDeVida());
        assertEquals(properties.probReproduccionProperty().getValue().doubleValue(), estudiante.getProbReproduccion(), 0);
        assertEquals(properties.probClonacionProperty().getValue().doubleValue(), estudiante.getProbClonacion(), 0);
        assertEquals(properties.probMuerteProperty().getValue().doubleValue(), estudiante.getProbMuerte(), 0);
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        MockEstudiante estudiante = new MockEstudiante(1, 1, 10, 0, 0, 0);
        EstudianteProperties estudianteProperties = new EstudianteProperties(estudiante);
        SimpleIntegerProperty tiempoDeVidaProperty = new SimpleIntegerProperty(10);
        SimpleIntegerProperty probReproduccionProperty = new SimpleIntegerProperty(0);
        SimpleIntegerProperty probClonacionProperty = new SimpleIntegerProperty(0);
        SimpleIntegerProperty probMuerteProperty = new SimpleIntegerProperty(0);

        // Act
        estudianteProperties.setOrigen(estudiante);
        Property<Number> tiempoDeVidaPropertyFromEstudiante = estudianteProperties.tiempoDeVidaProperty();
        Property<Number> probReproduccionPropertyFromEstudiante = estudianteProperties.probReproduccionProperty();
        Property<Number> probClonacionPropertyFromEstudiante = estudianteProperties.probClonacionProperty();
        Property<Number> probMuertePropertyFromEstudiante = estudianteProperties.probMuerteProperty();

        // Assert
        assertEquals(tiempoDeVidaProperty.getValue(), tiempoDeVidaPropertyFromEstudiante.getValue());
        assertEquals(probReproduccionProperty.getValue(), probReproduccionPropertyFromEstudiante.getValue());
        assertEquals(probClonacionProperty.getValue(), probClonacionPropertyFromEstudiante.getValue());
        assertEquals(probMuerteProperty.getValue(), probMuertePropertyFromEstudiante.getValue());

        // Act
        tiempoDeVidaPropertyFromEstudiante.setValue(25);
        probReproduccionPropertyFromEstudiante.setValue(70);
        probClonacionPropertyFromEstudiante.setValue(60);
        probMuertePropertyFromEstudiante.setValue(40);

        // Assert
        assertEquals(25, estudianteProperties.tiempoDeVidaProperty().getValue());
        assertEquals(70, estudianteProperties.probReproduccionProperty().getValue());
        assertEquals(60, estudianteProperties.probClonacionProperty().getValue());
        assertEquals(40, estudianteProperties.probMuerteProperty().getValue());
    }

    @Test
    public void testProperties() {
        // Arrange
        MockEstudiante estudiante = new MockEstudiante(1, 1, 10, 0.5, 0.5, 0.5);
        EstudianteProperties estudianteProperties = new EstudianteProperties(estudiante);

        // Act
        Property<Number> tiempoDeVidaProperty = estudianteProperties.tiempoDeVidaProperty();
        Property<Number> probReproduccionProperty = estudianteProperties.probReproduccionProperty();
        Property<Number> probClonacionProperty = estudianteProperties.probClonacionProperty();
        Property<Number> probMuerteProperty = estudianteProperties.probMuerteProperty();

        // Assert
        assertNotNull(tiempoDeVidaProperty);
        assertNotNull(probReproduccionProperty);
        assertNotNull(probClonacionProperty);
        assertNotNull(probMuerteProperty);
    }
}
