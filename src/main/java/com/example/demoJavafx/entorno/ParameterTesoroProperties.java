package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterTesoroProperties {
    protected Tesoro original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private DoubleProperty aumentoPorcentajeRep = new SimpleDoubleProperty();
    public ParameterTesoroProperties(Tesoro original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setAumentoProbReproduccion(aumentoPorcentajeRep.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        aumentoPorcentajeRep.set(original.getAumentoProbReproduccion());
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

}

