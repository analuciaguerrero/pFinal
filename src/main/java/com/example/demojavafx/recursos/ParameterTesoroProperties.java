package com.example.demojavafx.recursos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterTesoroProperties {
    protected Tesoro original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private FloatProperty aumentoDePorcenRep = new SimpleFloatProperty();
    private FloatProperty probTesoro = new SimpleFloatProperty();


    public ParameterTesoroProperties(Tesoro original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoDePorcenRep(aumentoDePorcenRep.get());
        original.setProbTesoro(probTesoro.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoDePorcenRep.set(original.getAumentoDePorcenRep());
        probTesoro.set(original.getProbTesoro());
    }

    public Tesoro getOriginal() {
        return original;
    }

    public void setOriginal(Tesoro original) {
        this.original = original;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }

    public FloatProperty aumentoDePorenRepProperty() {
        return aumentoDePorcenRep;
    }

    public FloatProperty probTesoroProperty() {return probTesoro;}
}

