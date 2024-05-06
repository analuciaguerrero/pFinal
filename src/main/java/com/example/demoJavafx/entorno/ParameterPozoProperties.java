package com.example.demoJavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterPozoProperties {
    protected Pozo original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private FloatProperty probPozo = new SimpleFloatProperty();



    public ParameterPozoProperties(Pozo original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        //original.setProbPozo(probPozo.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        //probPozo.set(original.getProbPozo());
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

    public FloatProperty probPozoProperty() {return probPozo;}

}

