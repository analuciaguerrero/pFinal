package com.example.demojavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterComidaProperties {
    protected Comida original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty aumentoDeVida = new SimpleIntegerProperty();
    private FloatProperty probComida = new SimpleFloatProperty();



    public ParameterComidaProperties(Comida original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoDeVida(aumentoDeVida.get());
        original.setProbComida(probComida.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoDeVida.set(original.getAumentoDeVida());
        probComida.set(original.getProbComida());
    }

    public Comida getOriginal() {
        return original;
    }

    public void setOriginal(Comida original) {
        this.original = original;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }

    public IntegerProperty aumentoDeVidaProperty() {
        return aumentoDeVida;
    }

    public FloatProperty probComidaProperty() {return probComida;}
}

