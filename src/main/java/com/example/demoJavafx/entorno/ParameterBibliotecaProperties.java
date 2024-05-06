package com.example.demoJavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterBibliotecaProperties {
    protected Biblioteca original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private FloatProperty aumentoPorentajeClon = new SimpleFloatProperty();
    private FloatProperty probBilio= new SimpleFloatProperty();


    public ParameterBibliotecaProperties(Biblioteca original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        //original.setAumentoPorcentajeClon(aumentoPorentajeClon.get());
        //original.setProbBiblio(probBilio.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        //aumentoPorentajeClon.set(original.getAumentoPorcentajeClon());
        //probBilio.set(original.getProbBiblio());
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

    public FloatProperty aumentoPorentajeClonProperty() {
        return aumentoPorentajeClon;
    }

    public FloatProperty probBibliotecaProperty() {return probBilio; }
}

