package com.example.demojavafx.recursos;

import javafx.beans.property.*;

public class ParameterAguaProperties {
    protected Agua originalAgua;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty aumentoDeVida = new SimpleIntegerProperty();
    private FloatProperty probAgua = new SimpleFloatProperty();



    public ParameterAguaProperties(Agua original) {
        setOriginalAgua(original);
    }

    public void commit(){
        originalAgua.setTurnosRestantes(turnosRestantes.get());
        originalAgua.setAumentoDeVida(aumentoDeVida.get());
        originalAgua.setProbAgua(probAgua.get());
    }

    public void rollback(){
        turnosRestantes.set(originalAgua.getTurnosRestantes());
        aumentoDeVida.set(originalAgua.getAumentoDeVida());
        probAgua.set(originalAgua.getProbAgua());
    }

    public Agua getOriginalAgua() {
        return originalAgua;
    }

    public void setOriginalAgua(Agua originalAgua) {
        this.originalAgua = originalAgua;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }

    public IntegerProperty aumentoDeVidaProperty() {
        return aumentoDeVida;
    }

    public FloatProperty probAguaProperty() {
        return probAgua;
    }
}

