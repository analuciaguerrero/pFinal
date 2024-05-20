package com.example.demoJavafx.estudiante;
import javafx.beans.property.*;

public class EstudianteProperties {

    protected Estudiante origen;
    private IntegerProperty tiempoDeVida = new SimpleIntegerProperty();
    private DoubleProperty probReproduccion = new SimpleDoubleProperty();
    private DoubleProperty probClonacion = new SimpleDoubleProperty();
    private DoubleProperty probMuerte = new SimpleDoubleProperty();
    private int turno;

    public EstudianteProperties(Estudiante origen) {
        setOrigen(origen);
    }
    public EstudianteProperties(){}

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
        origen.setTiempoDeVida(tiempoDeVida.get(), turno);
        origen.setProbReproduccion(probReproduccion.get(), turno);
        origen.setProbClonacion(probClonacion.get(), turno);
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
    public int getTurno(){return turno;}

}
