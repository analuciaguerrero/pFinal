package com.example.demojavafx.entorno;

import javafx.beans.property.*;

public class ParameterAguaProperties {
    protected Agua originalAgua;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty aumentoVida = new SimpleIntegerProperty();
    private FloatProperty probAgua = new SimpleFloatProperty();



    public ParameterAguaProperties(Agua original) {
        setOriginalAgua(original);
    }

    public void commit(){
        originalAgua.setTurnosRestantes(turnosRestantes.get());
        //originalAgua.setAumentoVida(aumentoVida.get());
        //originalAgua.setProbAgua(probAgua.get());
    }

    public void rollback(){
        turnosRestantes.set(originalAgua.getTurnosRestantes());
        //aumentoVida.set(originalAgua.getAumentoVida());
        //probAgua.set(originalAgua.getProbAgua());
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

    public IntegerProperty aumentoVidaProperty() {
        return aumentoVida;
    }

    public FloatProperty probAguaProperty() {
        return probAgua;
    }
}

