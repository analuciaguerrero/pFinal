package com.example.demojavafx.recursos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterBibliotecaProperties {
    protected Biblioteca original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private FloatProperty aumentoDePorenClon = new SimpleFloatProperty();
    private FloatProperty probBilio= new SimpleFloatProperty();


    public ParameterBibliotecaProperties(Biblioteca original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoDePorcenClon(aumentoDePorenClon.get());
        original.setProbBiblioteca(probBilio.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoDePorenClon.set(original.getAumentoDePorcenClon());
        probBilio.set(original.getProbBiblioteca());
    }

    public Biblioteca getOriginal() {
        return original;
    }

    public void setOriginal(Biblioteca original) {
        this.original = original;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }

    public FloatProperty aumentoDePorenClonProperty() {
        return aumentoDePorenClon;
    }

    public FloatProperty probBibliotecaProperty() {return probBilio; }
}

