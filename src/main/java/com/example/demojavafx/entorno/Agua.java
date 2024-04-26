package com.example.demojavafx.entorno;

public class Agua extends Recursos {

    private int AumentoVida;
    private float probAgua;


    public Agua(int turnosRestantes, int aumentoVida) {
        super(turnosRestantes);
        AumentoVida = aumentoVida;
    }
    public Agua(int tiempoVida, float probAgua) {
        super(tiempoVida);
        this.probAgua = probAgua;
    }

    public int getAumentoVida() {
        return AumentoVida;
    }

    public void setAumentoVida(int aumentoVida) {
        AumentoVida = aumentoVida;
    }

    public float getProbAgua() {
        return probAgua;
    }

    public void setProbAgua(float probAgua) {
        this.probAgua = probAgua;
    }
}

