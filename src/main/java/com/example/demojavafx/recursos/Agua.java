package com.example.demojavafx.recursos;

public class Agua extends Recursos {

    private int AumentoDeVida;
    private float probAgua;


    public Agua(int turnosRestantes, int aumentoDeVida) {
        super(turnosRestantes);
        AumentoDeVida = aumentoDeVida;
    }
    public Agua(int tiempoVida, float probAgua) {
        super(tiempoVida);
        this.probAgua = probAgua;
    }

    public int getAumentoDeVida() {
        return AumentoDeVida;
    }

    public void setAumentoDeVida(int aumentoDeVida) {
        AumentoDeVida = aumentoDeVida;
    }

    public float getProbAgua() {
        return probAgua;
    }

    public void setProbAgua(float probAgua) {
        this.probAgua = probAgua;
    }
}

