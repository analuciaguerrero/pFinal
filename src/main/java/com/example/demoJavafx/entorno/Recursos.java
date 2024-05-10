package com.example.demoJavafx.entorno;
import com.example.demoJavafx.estudiante.Estudiante;
public abstract class Recursos {
    protected int posicionN;
    protected int posicionM;
    protected int turnosRestantes;
    protected double probRecurso;

    public Recursos(int posicionN, int posicionM, int turnosRestantes, double probRecurso) {
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.turnosRestantes = turnosRestantes;
        this.probRecurso = probRecurso;
    }
    public Recursos(int posicionN, int posicionM, int turnosRestantes){
        this.posicionN = posicionN;
        this.posicionM = posicionM;
        this.turnosRestantes = turnosRestantes;
    }
    public Recursos(){}
    public int getPosicionN(){
        return posicionN;
    }
    public void setPosicionN(int posicionN){
        this.posicionN = posicionN;
    }
    public int getPosicionM(){
        return posicionM;
    }
    public void setPosicionM(int posicionM){
        this.posicionM = posicionM;
    }
    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    public void actualizarRecursos(){
        turnosRestantes--;
    }

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }
    public double getProbRecurso() {
        return probRecurso;
    }

    public void setProbRecurso(double probRecurso) {
        this.probRecurso = probRecurso;
    }

    public abstract void aplicarEfecto(Estudiante estudiante);

}
