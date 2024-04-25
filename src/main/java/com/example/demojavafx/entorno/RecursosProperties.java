package com.example.demojavafx.entorno;

import javafx.beans.property.*;

public class RecursosProperties {
    protected Recursos original;
    private IntegerProperty turnosRestantes = new SimpleIntegerProperty();
    private IntegerProperty posN = new SimpleIntegerProperty();
    private IntegerProperty posM = new SimpleIntegerProperty();
    private FloatProperty probNuevoRecurso = new SimpleFloatProperty();

    public RecursosProperties(Recursos original){
        setOriginal(original);
    }
    public void setOriginal(Recursos original){
        this.original = original;
        rollback();
    }
    public Recursos getOriginal(){
        return original;
    }

    public void rollback(){
        turnosRestantes.set(original.getTurnosRestantes());
        posN.set(original.getPosN());
        posM.set(original.getPosM());
        probNuevoRecurso.set(original.getProbNuevoRecurso());
    }
    public void commit(){
        original.setTurnosRestantes(turnosRestantes.get());
        original.setPosN(posN.get());
        original.setPosM(posM.get());
        original.setProbNuevoRecurso(probNuevoRecurso.get());

    }
    public Property<Number> turnosRestantesProperty() {
        return turnosRestantes;
    }

    public Property<Number> posNProperty() {
        return posN;
    }

    public Property<Number> posMProperty() {
        return posM;
    }

    public Property<Number> probNuevoRecursoProperty() {
        return probNuevoRecurso;
    }
}

