package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterPozoProperties {
    protected Pozo original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    public ParameterPozoProperties(Pozo original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
    }

    public Pozo getOriginal() {
        return original;
    }

    public void setOriginal(Pozo original) {
        this.original = original;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }
}

