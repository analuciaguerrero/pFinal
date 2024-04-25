package com.example.demojavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterMontañaProperties {
    protected Montaña original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty disminucionDeVida = new SimpleIntegerProperty();
    private FloatProperty probMontana = new SimpleFloatProperty();



    public ParameterMontañaProperties(Montaña original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setDisminucionDeVida(disminucionDeVida.get());
        original.setProbMontaña(probMontana.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        disminucionDeVida.set(original.getDisminucionDeVida());
        probMontana.set(original.getProbMontaña());
    }

    public Montaña getOriginal() {
        return original;
    }

    public void setOriginal(Montaña original) {
        this.original = original;
        rollback();
    }

    public IntegerProperty turnosRestantesProperty() {
        return turnosRestantes;
    }

    public IntegerProperty disminucionDeVidaProperty() {
        return disminucionDeVida;
    }

    public FloatProperty probMontanaProperty() {return probMontana;}
}

