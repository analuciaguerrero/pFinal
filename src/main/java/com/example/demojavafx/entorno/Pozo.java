package com.example.demojavafx.entorno;

public class Pozo extends Recursos{
    protected float probPozo;
    public Pozo(int tiempoVida) {
        super(tiempoVida);
    }

    public Pozo(int tiempoVida, float probPozo) {
        super(tiempoVida);
        this.probPozo = probPozo;
    }

    public float getProbPozo() {
        return probPozo;
    }

    public void setProbPozo(float probPozo) {
        this.probPozo = probPozo;
    }
}

