package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterMontañaProperties {
    protected Montaña original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty disminucionVida = new SimpleIntegerProperty();
    public ParameterMontañaProperties(Montaña original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setReduccionVida(disminucionVida.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        disminucionVida.set((int) original.getReduccionVida());
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
}

