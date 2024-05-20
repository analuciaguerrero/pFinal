package com.example.demoJavafx.entorno;

import javafx.beans.property.*;

public class RecursosProperties {
    protected Recursos original;
    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty posicionN = new SimpleIntegerProperty();
    private IntegerProperty posicionM = new SimpleIntegerProperty();

    public RecursosProperties(Recursos original){
        setOriginal(original);
    }
    public Recursos getOriginal(){
        return original;
    }
    public void setOriginal(Recursos original){
        this.original = original;
        rollback();
    }
    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        posicionN.set(original.getPosicionN());
        posicionM.set(original.getPosicionM());
    }
    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setPosicionN(posicionN.get());
        original.setPosicionM(posicionM.get());
    }
    public Property<Number> turnosRestantesProperty() {
        return turnosRestantes;
    }

    public Property<Number> posicionNProperty() {
        return posicionN;
    }

    public Property<Number> posicionMProperty() {
        return posicionM;
    }
}

