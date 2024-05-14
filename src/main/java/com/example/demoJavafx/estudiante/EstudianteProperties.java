package com.example.demoJavafx.estudiante;
import javafx.beans.property.*;

public class EstudianteProperties {

    protected Estudiante origen;
    private IntegerProperty tiempoDeVida = new SimpleIntegerProperty();
    private DoubleProperty probReproduccion = new SimpleDoubleProperty();
    private DoubleProperty probClonacion = new SimpleDoubleProperty();
    private DoubleProperty probMuerte = new SimpleDoubleProperty();

    public EstudianteProperties(Estudiante origen) {
        setOrigen(origen);
    }

    public Estudiante getOrigen() {
        return origen;
    }

    public void setOrigen(Estudiante origen) {
        this.origen = origen;
        rollback();
    }

    public void rollback() {
        tiempoDeVida.set(origen.getTiempoDeVida());
        probReproduccion.set(origen.getProbReproduccion());
        probClonacion.set(origen.getProbClonacion());
        probMuerte.set(origen.getProbMuerte());
    }

    public void commit() {
        origen.setTiempoDeVida(tiempoDeVida.get());
        origen.setProbReproduccion(probReproduccion.get());
        origen.setProbClonacion(probClonacion.get());
    }
    public IntegerProperty tiempoDeVidaProperty() {
        return tiempoDeVida;
    }
    public DoubleProperty probReproduccionProperty() {
        return probReproduccion;
    }
    public DoubleProperty probClonacionProperty() {
        return probClonacion;
    }
    public DoubleProperty probMuerteProperty() {
        return probMuerte;
    }

}
