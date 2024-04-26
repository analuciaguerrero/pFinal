package com.example.demojavafx.entorno;

public class Recursos {
    protected int turnosRestantes;
    protected int posN;
    protected int posM;
    protected float probNuevoRecurso;

    public Recursos(int turnosRestantes, int posN, int posM, float probNuevoRecurso) {
        this.turnosRestantes = turnosRestantes;
        this.posN = posN;
        this.posM = posM;
        this.probNuevoRecurso = probNuevoRecurso;
    }

    public Recursos(int turnosRestantes, int posN, int posM) {
        this.turnosRestantes = turnosRestantes;
        this.posN = posN;
        this.posM = posM;
    }

    public Recursos(int tiempoVida) {
        this.turnosRestantes = tiempoVida;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }

    public int getPosN() {
        return posN;
    }

    public void setPosN(int posN) {
        this.posN = posN;
    }

    public int getPosM() {
        return posM;
    }

    public void setPosM(int posM) {
        this.posM = posM;
    }

    public int[] getPosicion () {
        int[] posicion = new int[2];
        posicion[0] = posN;
        posicion[1] = posM;
        return posicion;
    }

    public void setPosicion (int[] posicion) {
        posN = posicion[0];
        posM = posicion[1];
    }

    public float getProbNuevoRecurso() {
        return probNuevoRecurso;
    }

    public void setProbNuevoRecurso(float probNuevoRecurso) {
        this.probNuevoRecurso = probNuevoRecurso;
    }

    @Override
    public String toString() {
        return "Recursos{" +
                "turnosRestantes=" + turnosRestantes +
                ", posN=" + posN +
                ", posM=" + posM +
                '}';
    }
}