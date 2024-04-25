package com.example.demojavafx.entorno;

public class Tesoro extends Recursos{
    private float AumentoDePorcenRep;
    private float probTesoro;

    public Tesoro(int turnosRestantes, int aumentoDeVida) {
        super(turnosRestantes);
        AumentoDePorcenRep = aumentoDeVida;
    }

    public Tesoro(int tiempoVida, float probTesoro) {
        super(tiempoVida);
        this.probTesoro = probTesoro;
    }

    public float getAumentoDePorcenRep() {
        return AumentoDePorcenRep;
    }

    public void setAumentoDePorcenRep(float aumentoDePorcenRep) {
        AumentoDePorcenRep = aumentoDePorcenRep;
    }

    public float getProbTesoro() {
        return probTesoro;
    }

    public void setProbTesoro(float probTesoro) {
        this.probTesoro = probTesoro;
    }
}
