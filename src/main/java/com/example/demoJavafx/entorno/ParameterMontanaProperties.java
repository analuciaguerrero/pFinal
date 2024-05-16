package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class ParameterMontanaProperties {
    protected Montaña original;

    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty disminucionVida = new SimpleIntegerProperty();
    private DoubleProperty probMontaña = new SimpleDoubleProperty();



    public ParameterMontanaProperties(Montaña original) {
        setOriginal(original);
    }

    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setReduccionVida(disminucionVida.get());
        original.setProbMontaña(probMontaña.get());
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        disminucionVida.set(original.getReduccionVida());
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

    public DoubleProperty probMontañaProperty() {return probMontaña;}
}

