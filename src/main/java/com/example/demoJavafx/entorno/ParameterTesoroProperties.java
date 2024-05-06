package com.example.demoJavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterTesoroProperties {
    protected Tesoro original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private FloatProperty aumentoPorcentajeRep = new SimpleFloatProperty();
    private FloatProperty probTesoro = new SimpleFloatProperty();


    public ParameterTesoroProperties(Tesoro original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        //original.setAumentoPorcentajeRep(aumentoPorcentajeRep.get());
        //original.setProbTesoro(probTesoro.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        //aumentoPorcentajeRep.set(original.getAumentoPorcentajeRep());
        //probTesoro.set(original.getProbTesoro());
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

    public FloatProperty aumentoPorcetajeRepProperty() {
        return aumentoPorcentajeRep;
    }

    public FloatProperty probTesoroProperty() {return probTesoro;}
}

