package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterTesoroProperties {
    protected Tesoro original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private DoubleProperty aumentoPorcentajeRep = new SimpleDoubleProperty();
    private DoubleProperty probTesoro = new SimpleDoubleProperty();


    public ParameterTesoroProperties(Tesoro original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoProbReproduccion(aumentoPorcentajeRep.get());
        original.setProbTesoro(probTesoro.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoPorcentajeRep.set(original.getAumentoProbReproduccion());
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

    public DoubleProperty aumentoPorcetajeRepProperty() {
        return aumentoPorcentajeRep;
    }

    public DoubleProperty probTesoroProperty() {return probTesoro;}
}

