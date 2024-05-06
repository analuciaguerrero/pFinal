package com.example.demoJavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterComidaProperties {
    protected Comida original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty aumentoVida = new SimpleIntegerProperty();
    private FloatProperty probComida = new SimpleFloatProperty();



    public ParameterComidaProperties(Comida original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        //original.setAumentoVida(aumentoVida.get());
        //original.setProbComida(probComida.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        //aumentoVida.set(original.getAumentoVida());
        //probComida.set(original.getProbComida());
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

    public IntegerProperty aumentoVidaProperty() {
        return aumentoVida;
    }

    public FloatProperty probComidaProperty() {return probComida;}
}

