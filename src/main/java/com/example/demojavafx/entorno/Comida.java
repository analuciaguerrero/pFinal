package com.example.demojavafx.entorno;

public class Comida extends Recursos{
    private int AumentoVida;
    private float probComida;

    public Comida(int tiempoVida, float probComida) {
        super(tiempoVida);
        this.probComida = probComida;
    }

    public Comida(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        AumentoVida = aumentoVida;
    }

    public int getAumentoVida() {
        return AumentoVida;
    }

    public void setAumentoVida(int aumentoVida) {
        AumentoVida = aumentoVida;
    }

    public float getProbComida() {
        return probComida;
    }

    public void setProbComida(float probComida) {
        this.probComida = probComida;
    }
}

