package com.example.demojavafx.entorno;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParameterMontañaProperties {
    protected Montaña original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty disminucionVida = new SimpleIntegerProperty();
    private FloatProperty probMontaña = new SimpleFloatProperty();



    public ParameterMontañaProperties(Montaña original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setDisminucionVida(disminucionVida.get());
        original.setProbMontaña(probMontaña.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        disminucionVida.set(original.getDisminucionVida());
        probMontaña.set(original.getProbMontaña());
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

    public IntegerProperty disminucionVidaProperty() {
        return disminucionVida;
    }

    public FloatProperty probMontañaProperty() {return probMontaña;}
}

