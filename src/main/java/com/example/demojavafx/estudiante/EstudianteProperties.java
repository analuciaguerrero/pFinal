package com.example.demojavafx.estudiante;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;

public class EstudianteProperties {

    protected Estudiante origen;
    private IntegerProperty tiempoDeVida = new SimpleIntegerProperty();
    private IntegerProperty probReproduccion = new SimpleIntegerProperty();
    private IntegerProperty probClonacion = new SimpleIntegerProperty();
    private IntegerProperty probMuerte = new SimpleIntegerProperty();

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
        probReproduccion.set((int)origen.getProbReproduccion());
        probClonacion.set((int)origen.getProbClonacion());
        probMuerte.set((int)origen.getProbMuerte());
    }

    public void commit() {
        origen.setTiempoDeVida(tiempoDeVida.get());
        origen.setProbReproduccion(probReproduccion.get());
        origen.setProbClonacion(probClonacion.get());
        origen.setProbMuerte(probMuerte.get());
    }

    public Property<Number> tiempoDeVidaProperty() {
        return tiempoDeVida;
    }

    public Property<Number> probReproduccionProperty() {
        return probReproduccion;
    }

    public Property<Number> probClonacionProperty() {
        return probClonacion;
    }

    public Property<Number> probMuerteProperty() {
        return probMuerte;
    }

}
