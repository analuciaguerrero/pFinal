package com.example.demojavafx.entorno;

public class Comida extends Recursos{
    private int AumentoDeVida;
    private float probComida;

    public Comida(int tiempoVida, float probComida) {
        super(tiempoVida);
        this.probComida = probComida;
    }

    public Comida(int turnosRestantes, int aumentoDeVida) {
        super(turnosRestantes);
        AumentoDeVida = aumentoDeVida;
    }

    public int getAumentoDeVida() {
        return AumentoDeVida;
    }

    public void setAumentoDeVida(int aumentoDeVida) {
        AumentoDeVida = aumentoDeVida;
    }

    public float getProbComida() {
        return probComida;
    }

    public void setProbComida(float probComida) {
        this.probComida = probComida;
    }
}

