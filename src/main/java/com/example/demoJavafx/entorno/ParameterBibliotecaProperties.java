package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterBibliotecaProperties {
    protected Biblioteca original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private DoubleProperty aumentoPorentajeClon = new SimpleDoubleProperty();


    public ParameterBibliotecaProperties(Biblioteca original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoProbClonacion(aumentoPorentajeClon.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoPorentajeClon.set(original.getAumentoProbClonacion());
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

    public DoubleProperty aumentoPorentajeClonProperty() {
        return aumentoPorentajeClon;
    }

}

